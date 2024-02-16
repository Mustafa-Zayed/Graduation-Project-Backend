package com.GraduationProject.ecommerce.dao;

import com.GraduationProject.ecommerce.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
}
