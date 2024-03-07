package com.GraduationProject.ecommerce.dao;

import com.GraduationProject.ecommerce.entity.Cart;
import com.GraduationProject.ecommerce.entity.Product;
import com.GraduationProject.ecommerce.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDao extends CrudRepository<Cart, Integer> {
    @Query("select c from Cart c where user = :data")
    List<Cart> findByUser(@Param("data") User user); // here, the method name is enough as spring uses methods naming convention to create the query but let us use @Query as a confirmation.

    @Query("select c from Cart c where product = :data1 and user = :data2")
    Optional<Cart> findByProductAndUser(@Param("data1") Product product, @Param("data2") User user);
}
