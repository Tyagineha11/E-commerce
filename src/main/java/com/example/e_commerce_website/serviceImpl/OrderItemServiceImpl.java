package com.example.e_commerce_website.serviceImpl;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.OrderItemResponseDTO;
import com.example.e_commerce_website.entity.OrderItem;
import com.example.e_commerce_website.repository.OrderItemRepository;
import com.example.e_commerce_website.serviceInterface.OrderItemService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // =========================
    // GET ITEMS BY ORDER ID
    // =========================
    @Override
    public ApiResponse getItemsByOrderId(Long orderId) {

        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        List<OrderItemResponseDTO> responseList = new ArrayList<>();

        for (OrderItem item : items) {

            OrderItemResponseDTO dto = new OrderItemResponseDTO();

            dto.setOrderId(
                    item.getOrder() != null ? item.getOrder().getId() : null
            );

            dto.setProductId(
                    item.getProduct() != null ? item.getProduct().getId() : null
            );

            dto.setSellerId(item.getSellerId());

            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getPrice());
            dto.setSubtotal(item.getSubtotal());
            dto.setStatus(item.getStatus());

            responseList.add(dto);
        }

        return new ApiResponse(200, "Order items fetched", responseList);
    }

    // =========================
    // GET ITEMS BY SELLER ID
    // =========================
    @Override
    public ApiResponse getItemsBySellerId(String sellerId) {

        List<OrderItem> items = orderItemRepository.findBySellerId(sellerId);

        List<OrderItemResponseDTO> responseList = new ArrayList<>();

        for (OrderItem item : items) {

            OrderItemResponseDTO dto = new OrderItemResponseDTO();

            dto.setOrderId(
                    item.getOrder() != null ? item.getOrder().getId() : null
            );

            dto.setProductId(
                    item.getProduct() != null ? item.getProduct().getId() : null
            );

            dto.setSellerId(item.getSellerId());

            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getPrice());
            dto.setSubtotal(item.getSubtotal());
            dto.setStatus(item.getStatus());

            responseList.add(dto);
        }

        return new ApiResponse(200, "Seller items fetched", responseList);
    }
    
    
    @Override
    public ApiResponse updateOrderItemStatus(Long orderItemId, String status) {

        OrderItem item = orderItemRepository.findById(orderItemId)
                .orElse(null);

        if (item == null) {
            return new ApiResponse(404, "Order item not found", null);
        }

        item.setStatus(status);

        orderItemRepository.save(item);

        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setOrderId(item.getOrder() != null ? item.getOrder().getId() : null);
        dto.setProductId(item.getProduct() != null ? item.getProduct().getId() : null);
        dto.setSellerId(item.getSellerId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        dto.setSubtotal(item.getSubtotal());
        dto.setStatus(item.getStatus());

        return new ApiResponse(200, "Order item status updated", dto);
    }
}
