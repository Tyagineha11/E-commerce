package com.example.e_commerce_website.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private RazorpayClient razorpayClient;
 
    @PostMapping("/create-order")
    public String createOrder(@RequestParam int amount) throws Exception {

        JSONObject options = new JSONObject();

        options.put("amount", amount * 100); // paise
        options.put("currency", "INR");
        options.put("payment_capture", 1);

        Order order = razorpayClient.orders.create(options);

        return order.toString();
    }
}