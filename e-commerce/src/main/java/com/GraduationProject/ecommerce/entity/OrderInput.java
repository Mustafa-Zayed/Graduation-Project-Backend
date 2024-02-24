package com.GraduationProject.ecommerce.entity;

import java.util.List;

/**
 * This class defines all fields required from the user who asks for an order.
 * <p> Not annotated with @Entity as we don't need this in db.
 * It's a helper class for OrderDetail.
 */
public class OrderInput {

    List<OrderProductQuantity> orderProductQuantityList;
    // fields
    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private String alternateContactNumber;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public List<OrderProductQuantity> getOrderProductQuantityList() {
        return orderProductQuantityList;
    }

    public void setOrderProductQuantityList(List<OrderProductQuantity> orderProductQuantityList) {
        this.orderProductQuantityList = orderProductQuantityList;
    }
}
