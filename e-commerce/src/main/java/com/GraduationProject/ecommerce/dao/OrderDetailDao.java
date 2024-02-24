package com.GraduationProject.ecommerce.dao;

import com.GraduationProject.ecommerce.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {
}
