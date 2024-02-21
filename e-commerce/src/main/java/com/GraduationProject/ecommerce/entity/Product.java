package com.GraduationProject.ecommerce.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    private String productName;
    @Column(length = 2000)
    private String productDescription;
    private Double productActualPrice;
    private Double productDiscountedPrice;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // In this state, it's better to use @OneToMany relationship.
    @JoinTable(
            name = "product_images",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private Set<ImageModel> productImages; // Set not List-> to prevent images duplication.

    // constructors
    public Product() {
    }

    public Product(String productName, String productDescription, Double productActualPrice, Double productDiscountedPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productActualPrice = productActualPrice;
        this.productDiscountedPrice = productDiscountedPrice;
    }

    // convenient method to add images
    public void addImageModel(ImageModel imageModel) {
        if (productImages == null)
            productImages = new HashSet<>();

        productImages.add(imageModel);
    }

    // getters & setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductActualPrice() {
        return productActualPrice;
    }

    public void setProductActualPrice(Double productActualPrice) {
        this.productActualPrice = productActualPrice;
    }

    public Double getProductDiscountedPrice() {
        return productDiscountedPrice;
    }

    public void setProductDiscountedPrice(Double productDiscountedPrice) {
        this.productDiscountedPrice = productDiscountedPrice;
    }

    public Set<ImageModel> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ImageModel> productImages) {
        this.productImages = productImages;
    }
}
