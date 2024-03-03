package com.GraduationProject.ecommerce.service;

import com.GraduationProject.ecommerce.dao.ProductDao;
import com.GraduationProject.ecommerce.entity.ImageModel;
import com.GraduationProject.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product addNewProduct(Product product, MultipartFile[] files) throws IOException {

        for (MultipartFile file : files) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            product.addImageModel(imageModel);
        }
        return productDao.save(product);
    }

    public Product addNewProduct(Product product) {
        return productDao.save(product);
    }

    public List<Product> getAllProducts(int pageNumber, String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber, 12);

        if (searchKey.isEmpty()) {
            return productDao.findAll(pageable);
        } else {
            return productDao.findInSearchBox(pageable, searchKey);
        }
    }

    public Product getProductDetailsById(Integer productId) {
        return productDao.findById(productId).get();
    }

    public Product deleteProductDetails(Integer productId) {
        Product product = productDao.findById(productId).get();
        productDao.deleteById(productId);
        return product;
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
        if (isSingleProductCheckout) {
            // we are going to buy a single product.

            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;

        } else {
            // we are going to check out the entire cart.


        }

        return new ArrayList<>();
    }
}
