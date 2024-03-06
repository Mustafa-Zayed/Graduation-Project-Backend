package com.GraduationProject.ecommerce.service;

import com.GraduationProject.ecommerce.configuration.JwtRequestFilter;
import com.GraduationProject.ecommerce.dao.CartDao;
import com.GraduationProject.ecommerce.dao.OrderDetailDao;
import com.GraduationProject.ecommerce.dao.ProductDao;
import com.GraduationProject.ecommerce.dao.UserDao;
import com.GraduationProject.ecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Placed";
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CartDao cartDao;


    /**
     * isCartCheckout -> true: this indicates the products in the cart,
     * so we will place the order and then empty the cart.
     * <p>
     * isCartCheckout -> false: this indicates a single product, so we just place the order.
     */
    public void placeOrder(OrderInput orderInput, boolean isCartCheckout) {

        List<OrderProductQuantity> list = orderInput.getOrderProductQuantityList();

        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).orElseThrow(
                () -> new NoSuchElementException("The user not found"));

        list.forEach(e -> {

            Product product = productDao.findById(e.getProductId()).orElseThrow(
                    () -> new NoSuchElementException("The product not found"));

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

        // empty the cart.
        if (isCartCheckout) {
            List<Cart> cartList = cartDao.findByUser(user); // List<Cart> cartList = cartService.getCartDetails();

            cartList.forEach(cart -> cartDao.delete(cart));
        }
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