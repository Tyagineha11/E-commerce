package com.example.e_commerce_website.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.OrderItemDTO;
import com.example.e_commerce_website.dto.OrderItemResponseDTO;
import com.example.e_commerce_website.dto.OrderResponseDTO;
import com.example.e_commerce_website.dto.PlaceOrderDTO;
import com.example.e_commerce_website.entity.Order;
import com.example.e_commerce_website.entity.OrderItem;
import com.example.e_commerce_website.entity.OrderStatus;
import com.example.e_commerce_website.entity.PaymentStatus;
import com.example.e_commerce_website.entity.Product;
import com.example.e_commerce_website.repository.OrderRepository;
import com.example.e_commerce_website.repository.ProductRepository;
import com.example.e_commerce_website.serviceInterface.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // =========================
    // PLACE ORDER
    // =========================
    @Override
    public ApiResponse placeOrder(PlaceOrderDTO dto) {

        Order order = new Order();

        // BASIC DETAILS
        order.setUserId(dto.getUserId());

        order.setAddressId(dto.getAddressId());

        order.setTotalAmount(dto.getTotalAmount());

        // SHIPPING
        double shipping =
                dto.getShippingCharge() == null
                ? 20.0
                : dto.getShippingCharge();

        order.setShippingCharge(shipping);

        order.setFinalAmount(
                dto.getTotalAmount() + shipping
        );

        // ORDER STATUS
        order.setOrderStatus(OrderStatus.PENDING);

        // PAYMENT STATUS
        if(dto.getPaymentStatus() != null
                && !dto.getPaymentStatus().isEmpty()) {

            try {

                order.setPaymentStatus(
                        PaymentStatus.valueOf(
                                dto.getPaymentStatus()
                                   .toUpperCase()
                        )
                );

            } catch (Exception e) {

                order.setPaymentStatus(
                        PaymentStatus.PENDING
                );
            }

        } else {

            order.setPaymentStatus(
                    PaymentStatus.PENDING
            );
        }

        // PAYMENT DETAILS
        order.setPaymentMethod(
                dto.getPaymentMethod()
        );

        order.setPaymentId(
                dto.getPaymentId()
        );

        // ORDER NUMBER
        order.setOrderNumber(
                "ORD-" + System.currentTimeMillis()
        );

        // ITEMS
        List<OrderItem> itemList =
                new ArrayList<>();

        for(OrderItemDTO itemDto : dto.getItems()) {

            Product product =
                    productRepository
                    .findById(itemDto.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Product not found"
                            )
                    );

            OrderItem item = new OrderItem();

            item.setProduct(product);

            item.setQuantity(
                    itemDto.getQuantity()
            );

            item.setPrice(
                    itemDto.getPrice()
            );

            item.setSubtotal(
                    itemDto.getSubtotal()
            );

            item.setStatus(
                    itemDto.getStatus()
            );

            // SELLER ID
            item.setSellerId(

                    product.getSeller() != null

                    ? product.getSeller().getId()

                    : null
            );

            // LINK ORDER
            item.setOrder(order);

            itemList.add(item);
        }

        order.setItems(itemList);

        // SAVE
        Order saved =
                orderRepository.save(order);

        return new ApiResponse(
                200,
                "Order placed successfully",
                mapToDTO(saved)
        );
    }

    // =========================
    // GET ALL ORDERS
    // =========================
    @Override
    public ApiResponse getAllOrders() {

        List<Order> orders =
                orderRepository.findAll();

        List<OrderResponseDTO> list =
                new ArrayList<>();

        for(Order order : orders) {

            list.add(mapToDTO(order));
        }

        return new ApiResponse(
                200,
                "All orders fetched",
                list
        );
    }

    // =========================
    // GET ORDER BY ID
    // =========================
    @Override
    public ApiResponse getOrderById(Long id) {

        Order order =
                orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found"
                        )
                );

        return new ApiResponse(
                200,
                "Order fetched",
                mapToDTO(order)
        );
    }

    // =========================
    // GET ORDERS BY USER
    // =========================
    @Override
    public ApiResponse getOrdersByUserId(Long userId) {

        List<Order> orders =
                orderRepository.findByUserId(userId);

        List<OrderResponseDTO> list =
                new ArrayList<>();

        for(Order order : orders) {

            list.add(mapToDTO(order));
        }

        return new ApiResponse(
                200,
                "User orders fetched",
                list
        );
    }

    // =========================
    // CANCEL ORDER
    // =========================
    @Override
    public ApiResponse cancelOrder(Long id) {

        Order order =
                orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found"
                        )
                );

        if(order.getOrderStatus()
                == OrderStatus.DELIVERED) {

            return new ApiResponse(
                    400,
                    "Delivered order cannot be cancelled",
                    null
            );
        }

        order.setOrderStatus(
                OrderStatus.CANCELLED
        );

        order.setUpdatedAt(
                LocalDateTime.now()
        );

        Order saved =
                orderRepository.save(order);

        return new ApiResponse(
                200,
                "Order cancelled successfully",
                mapToDTO(saved)
        );
    }

    // =========================
    // UPDATE ORDER STATUS
    // =========================
    @Override
    public ApiResponse updateOrderStatus(
            Long id,
            String status
    ) {

        Order order =
                orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found"
                        )
                );

        try {

            OrderStatus newStatus =
                    OrderStatus.valueOf(
                            status.toUpperCase()
                    );

            order.setOrderStatus(newStatus);

            order.setUpdatedAt(
                    LocalDateTime.now()
            );

            Order saved =
                    orderRepository.save(order);

            return new ApiResponse(
                    200,
                    "Order status updated",
                    mapToDTO(saved)
            );

        } catch (IllegalArgumentException e) {

            return new ApiResponse(
                    400,
                    "Invalid order status",
                    null
            );
        }
    }

    // =========================
    // DTO MAPPER
    // =========================
    private OrderResponseDTO mapToDTO(Order order) {

        OrderResponseDTO dto =
                new OrderResponseDTO();

        dto.setOrderId(order.getId());

        dto.setOrderNumber(
                order.getOrderNumber()
        );

        dto.setUserId(order.getUserId());

        dto.setAddressId(order.getAddressId());

        dto.setTotalAmount(
                order.getTotalAmount()
        );

        dto.setShippingCharge(
                order.getShippingCharge()
        );

        dto.setFinalAmount(
                order.getFinalAmount()
        );

        dto.setOrderStatus(
                order.getOrderStatus().name()
        );

        dto.setPaymentStatus(

                order.getPaymentStatus() != null

                ? order.getPaymentStatus().name()

                : null
        );

        dto.setCreatedAt(
                order.getCreatedAt()
        );

        dto.setUpdatedAt(
                order.getUpdatedAt()
        );

        List<OrderItemResponseDTO> items =
                new ArrayList<>();

        if(order.getItems() != null) {

            for(OrderItem item : order.getItems()) {

                OrderItemResponseDTO dtoItem =
                        new OrderItemResponseDTO();

                dtoItem.setProductId(

                        item.getProduct() != null

                        ? item.getProduct().getId()

                        : null
                );

                dtoItem.setSellerId(
                        item.getSellerId()
                );

                dtoItem.setQuantity(
                        item.getQuantity()
                );

                dtoItem.setPrice(
                        item.getPrice()
                );

                dtoItem.setSubtotal(
                        item.getSubtotal()
                );

                dtoItem.setStatus(
                        item.getStatus()
                );

                items.add(dtoItem);
            }
        }

        dto.setItems(items);

        return dto;
    }
}