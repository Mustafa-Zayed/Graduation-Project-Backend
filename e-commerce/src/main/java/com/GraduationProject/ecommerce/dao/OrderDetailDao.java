package com.GraduationProject.ecommerce.dao;

import com.GraduationProject.ecommerce.entity.OrderDetail;
import com.GraduationProject.ecommerce.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {
    @Query("SELECT od FROM OrderDetail od WHERE od.user = ?1")
    List<OrderDetail> findByUser(User user);
}
