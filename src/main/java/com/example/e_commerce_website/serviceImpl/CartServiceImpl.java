package com.example.e_commerce_website.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.dto.CartDTO;
import com.example.e_commerce_website.entity.Cart;
import com.example.e_commerce_website.entity.CartItem;
import com.example.e_commerce_website.entity.Product;
import com.example.e_commerce_website.entity.User;
import com.example.e_commerce_website.repository.CartRepository;
import com.example.e_commerce_website.repository.ProductRepository;
import com.example.e_commerce_website.repository.UserRepository;
import com.example.e_commerce_website.serviceInterface.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // ================= ADD TO CART =================
    @Override
    public ApiResponse addToCart(CartDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElse(new Cart());

        cart.setUser(user);

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(dto.getQuantity());
        
        double totalPrice = product.getDiscountPrice().doubleValue()
                * dto.getQuantity();

        cartItem.setTotalPrice(totalPrice);

        cart.getCartItems().add(cartItem);

        double totalAmount = cart.getCartItems()
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();

        cart.setTotalAmount(totalAmount);

        cartRepository.save(cart);

        return new ApiResponse(
                200,
                "Product added to cart successfully",
                cart
        );
    }
    
    // ================= GET CART =================
    @Override
    public ApiResponse getCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return new ApiResponse(
                200,
                "Cart fetched successfully",
                cart
        );
    }
    
 // ================= UPDATE CART =================
    @Override
    public ApiResponse updateCart(CartDTO dto) {

        Cart cart = cartRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        for (CartItem item : cart.getCartItems()) {

            if (item.getProduct().getId().equals(dto.getProductId())) {

                item.setQuantity(dto.getQuantity());

                double totalPrice =
                        item.getProduct().getDiscountPrice().doubleValue()
                        * dto.getQuantity();

                item.setTotalPrice(totalPrice);
            }
        }

        double totalAmount = cart.getCartItems()
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();

        cart.setTotalAmount(totalAmount);

        cartRepository.save(cart);

        return new ApiResponse(
                200,
                "Cart updated successfully",
                cart
        );
    }
    
 // ================= REMOVE FROM CART =================
    @Override
    public ApiResponse removeFromCart(Long userId, Long productId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getCartItems().removeIf(item ->
                item.getProduct().getId().equals(productId));

        double totalAmount = cart.getCartItems()
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();

        cart.setTotalAmount(totalAmount);

        cartRepository.save(cart);

        return new ApiResponse(
                200,
                "Product removed from cart successfully",
                cart
        );
    }
    
    // ================= REMOVE FROM CART =================
    
    @Override
    public ApiResponse clearCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getCartItems().clear();

        cart.setTotalAmount(0.0);

        cartRepository.save(cart);

        return new ApiResponse(
                200,
                "Cart cleared successfully",
                null
        );
    }
    
}