package com.GraduationProject.ecommerce.entity;

/**
 * Not annotated with @Entity as we don't need this in db. It's just for security stuff.
 */
public class JwtRequest {
    private String userName;
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
