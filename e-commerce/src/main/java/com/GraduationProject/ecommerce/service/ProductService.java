package com.GraduationProject.ecommerce.service;

import com.GraduationProject.ecommerce.dao.ProductDao;
import com.GraduationProject.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product addNewProduct(Product product){
        return productDao.save(product);
    }
}
