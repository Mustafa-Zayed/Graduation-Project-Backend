package com.GraduationProject.ecommerce.controller;

import com.GraduationProject.ecommerce.entity.ImageModel;
import com.GraduationProject.ecommerce.entity.Product;
import com.GraduationProject.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    /*@PostMapping(value = "/addNewProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public String addNewProduct(
                @RequestParam("productName") String productName,
                @RequestParam("productDescription") String productDescription,
                @RequestParam("productActualPrice") Double productActualPrice,
                @RequestParam("productDiscountedPrice") Double productDiscountedPrice,
                @RequestPart("files") MultipartFile[] files
        ) throws IOException {
            // Create a Product object using the received parameters
            Product product = new Product(productName, productDescription, productActualPrice, productDiscountedPrice);

            // Call the productService to add the new product
            return productService.addNewProduct(product, files);
        }*/
    // best name convention is "/product/add"
    @PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product,
                                 @RequestPart("imageFile") MultipartFile[] file) {
        try {
            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return productService.addNewProduct(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file: multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }

        return imageModels;
    }


}
