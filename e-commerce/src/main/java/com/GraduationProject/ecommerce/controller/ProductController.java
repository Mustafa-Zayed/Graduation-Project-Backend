package com.GraduationProject.ecommerce.controller;

import com.GraduationProject.ecommerce.entity.Product;
import com.GraduationProject.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    // best name convention is "/product/add"
    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = "/addNewProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public Product addNewProduct(
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
    }
    /*// best name convention is "/product/add"
    @PreAuthorize("hasRole('Admin')")
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

        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }

        return imageModels;
    }*/

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber) {
        return productService.getAllProducts(pageNumber);
    }

    @GetMapping("/getProductDetailsById/{productId}")
    public Product getProductDetailsById(@PathVariable Integer productId){
        return productService.getProductDetailsById(productId);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deleteProductDetails/{productId}")
    public Product deleteProductDetails(@PathVariable Integer productId){
        return productService.deleteProductDetails(productId);
    }

    /**
     * The boolean flag indicates whether we check out
     * a single product if true, or the entire cart if false.
     */
    @PreAuthorize("hasRole('User')")
    @GetMapping("/getProductDetails/{isSingleProductCheckout}/{productId}")
    public List<Product> getProductDetails(@PathVariable boolean isSingleProductCheckout, @PathVariable Integer productId) {
        return productService.getProductDetails(isSingleProductCheckout, productId);
    }

}
