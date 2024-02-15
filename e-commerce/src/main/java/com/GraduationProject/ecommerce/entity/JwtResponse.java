package com.GraduationProject.ecommerce.entity;

/**
 * Not annotated with @Entity as we don't need this in db. It's just for security stuff.
 */
public class JwtResponse {
    private User user;

    private String jwtToken;

    public JwtResponse(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
