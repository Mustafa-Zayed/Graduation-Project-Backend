package com.GraduationProject.ecommerce.controller;

import com.GraduationProject.ecommerce.entity.OrderDetail;
import com.GraduationProject.ecommerce.entity.OrderInput;
import com.GraduationProject.ecommerce.entity.TransactionDetails;
import com.GraduationProject.ecommerce.service.OrderDetailService;
import com.razorpay.RazorpayException;
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
    @GetMapping("/getAllOrderDetails/{status}")
    public List<OrderDetail> getAllOrderDetails(@PathVariable String status) {
        return orderDetailService.getAllOrderDetails(status);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/markOrderAsDelivered/{orderId}")
    public OrderDetail markOrderAsDelivered(@PathVariable Integer orderId) {
        return orderDetailService.markOrderAsDelivered(orderId);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/createTransaction/{amount}")
    public TransactionDetails createTransaction(@PathVariable Double amount) throws RazorpayException {
        return orderDetailService.createTransaction(amount);
    }
}
