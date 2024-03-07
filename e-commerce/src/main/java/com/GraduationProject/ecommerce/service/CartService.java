package com.GraduationProject.ecommerce.service;

import com.GraduationProject.ecommerce.configuration.JwtRequestFilter;
import com.GraduationProject.ecommerce.dao.CartDao;
import com.GraduationProject.ecommerce.dao.ProductDao;
import com.GraduationProject.ecommerce.dao.UserDao;
import com.GraduationProject.ecommerce.entity.Cart;
import com.GraduationProject.ecommerce.entity.Product;
import com.GraduationProject.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    public Cart addToCart(Integer productId) {
        Product product = productDao.findById(productId).orElseThrow(
                () -> new NoSuchElementException("The product not found"));

        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).orElseThrow(
                () -> new NoSuchElementException("The user not found"));

        if (cartDao.findByProductAndUser(product, user).isPresent())
            return null;

//        List<Cart> cartList = cartDao.findByUser(user);
//        for (Cart cart : cartList) {
//            if (cart.getProduct().equals(product))
//                return null;
//        }

        Cart cart = new Cart(product, user);

        return cartDao.save(cart);

    }

    public List<Cart> getCartDetails() {

        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).orElseThrow(
                () -> new NoSuchElementException("The user not found"));

//        List<Product> products = new ArrayList<>();
//        List<Cart> carts = cartDao.findByUser(user);
//        carts.forEach(cart -> products.add(cart.getProduct()));
//        return products;

        return cartDao.findByUser(user);
    }
}
