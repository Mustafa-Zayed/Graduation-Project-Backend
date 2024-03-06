package com.GraduationProject.ecommerce.controller;

import com.GraduationProject.ecommerce.entity.OrderInput;
import com.GraduationProject.ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('User')")
    @PostMapping("/placeOrder/{isCartCheckout}")
    public void placeOrder(@RequestBody OrderInput orderInput, @PathVariable boolean isCartCheckout) {
        orderDetailService.placeOrder(orderInput, isCartCheckout);
    }
}
