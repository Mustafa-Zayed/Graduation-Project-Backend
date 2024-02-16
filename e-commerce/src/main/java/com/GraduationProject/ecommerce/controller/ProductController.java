package com.GraduationProject.ecommerce.controller;

import com.GraduationProject.ecommerce.entity.Product;
import com.GraduationProject.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addNewProduct") // best name convention is "/product/add"
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }
}
