package com.GraduationProject.ecommerce.entity;

/**
 * Not annotated with @Entity as we don't need this in db. It's a helper class in the payment process.
 */
public class TransactionDetails {

    private String orderId;
    private String orderCurrency;
    private Integer orderAmount;
    private String key;

    public TransactionDetails() {

    }

    public TransactionDetails(String orderId, String orderCurrency, Integer orderAmount, String key) {
        this.orderId = orderId;
        this.orderCurrency = orderCurrency;
        this.orderAmount = orderAmount;
        this.key = key;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
