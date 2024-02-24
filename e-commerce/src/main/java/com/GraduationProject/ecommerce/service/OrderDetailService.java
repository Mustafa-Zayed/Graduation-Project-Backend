package com.GraduationProject.ecommerce.service;

import com.GraduationProject.ecommerce.configuration.JwtRequestFilter;
import com.GraduationProject.ecommerce.dao.OrderDetailDao;
import com.GraduationProject.ecommerce.dao.ProductDao;
import com.GraduationProject.ecommerce.dao.UserDao;
import com.GraduationProject.ecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Placed";
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    public void placeOrder(OrderInput orderInput) {

        List<OrderProductQuantity> list = orderInput.getOrderProductQuantityList();

        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).get();

        list.forEach(e -> {

            Product product = productDao.findById(e.getProductId()).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    e.getQuantity() * product.getProductDiscountedPrice(),
                    product,
                    user
            );

            orderDetailDao.save(orderDetail);
        });

    }
}
/*
*
* public void placeOrder(OrderInput orderInput) {
        List<OrderProductQuantity> orderProductQuantityList = orderInput.getOrderProductQuantityList();

        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).get();

        for (OrderProductQuantity o : orderProductQuantityList) {

            Product product = productDao.findById(o.getProductId()).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice() * o.getQuantity(),
                    product,
                    user
            );

            orderDetailDao.save(orderDetail);
        }
}*/