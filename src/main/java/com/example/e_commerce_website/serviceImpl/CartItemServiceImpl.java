package com.example.e_commerce_website.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.CartItemResponseDTO;
import com.example.e_commerce_website.dto.CartResponseDTO;
import com.example.e_commerce_website.entity.Cart;
import com.example.e_commerce_website.entity.CartItem;
import com.example.e_commerce_website.repository.CartRepository;
import com.example.e_commerce_website.serviceInterface.CartItemService;

@Service
public class CartItemServiceImpl
        implements CartItemService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public ApiResponse getCartItems(Long userId) {

        Cart cart = cartRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Cart not found"));

        CartResponseDTO responseDTO =
                new CartResponseDTO();

        responseDTO.setCartId(cart.getId());

        responseDTO.setUserId(
                cart.getUser().getId());

        responseDTO.setTotalAmount(
                cart.getTotalAmount());
        
        

        List<CartItemResponseDTO> itemDTOList =
                new ArrayList<>();

        for (CartItem item : cart.getCartItems()) {

            CartItemResponseDTO itemDTO =
                    new CartItemResponseDTO();

            itemDTO.setProductId(
                    item.getProduct().getId());

            itemDTO.setProductName(
                    item.getProduct().getName());

            itemDTO.setPrice(
                    item.getProduct()
                    .getDiscountPrice()
                    .doubleValue());

            itemDTO.setQuantity(
                    item.getQuantity());

            itemDTO.setSubtotal(
                    item.getTotalPrice());

            itemDTOList.add(itemDTO);
        }

        responseDTO.setItems(itemDTOList);

        return new ApiResponse(
                200,
                "Cart Items fetched successfully",
                responseDTO
        );
    }
}