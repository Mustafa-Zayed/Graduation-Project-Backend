package com.GraduationProject.ecommerce.entity;

/**
 * This class defines the combination of products id and quantity.
 * <p> Not annotated with @Entity as we don't need this in db.
 * It's a helper class for OrderDetail.
 */
public class OrderProductQuantity {
    private Integer productId;
    private Integer quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
