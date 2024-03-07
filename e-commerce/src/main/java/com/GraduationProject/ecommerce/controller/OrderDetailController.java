package com.GraduationProject.ecommerce.controller;

import com.GraduationProject.ecommerce.entity.OrderDetail;
import com.GraduationProject.ecommerce.entity.OrderInput;
import com.GraduationProject.ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('User')")
    @PostMapping("/placeOrder/{isCartCheckout}")
    public void placeOrder(@RequestBody OrderInput orderInput, @PathVariable boolean isCartCheckout) {
        orderDetailService.placeOrder(orderInput, isCartCheckout);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/getOrderDetails")
    public List<OrderDetail> getOrderDetails() {
        return orderDetailService.getOrderDetails();
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/getAllOrderDetails")
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }
}
