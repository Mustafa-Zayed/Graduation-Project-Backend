package com.GraduationProject.ecommerce.service;

import com.GraduationProject.ecommerce.dao.ProductDao;
import com.GraduationProject.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    /*public Product addNewProduct(Product product, MultipartFile[] files) throws IOException {

        for(MultipartFile file : files){
            ImageModel imageModel=new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            product.addImageModel(imageModel);
        }
        return productDao.save(product);
    }*/
    /*public String addNewProduct(Product product, MultipartFile[] files) throws IOException {

        for (MultipartFile file : files) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            product.addImageModel(imageModel);
        }
        productDao.save(product);
        return "file uploaded successfully";
    }*/

    public Product addNewProduct(Product product) {
        return productDao.save(product);
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productDao.findAll();
    }


}
