package com.GraduationProject.ecommerce.controller;

import com.GraduationProject.ecommerce.entity.User;
import com.GraduationProject.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * This is a method runs whenever the application gets started that initially saves users and roles in the database.
     */
    @PostConstruct
    public void initRolesAndUsers() {
        userService.initRolesAndUsers();
    }

    /**
     * create a user and save it in the database
     */
    @PostMapping("/registerNewUser")
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    // two endpoints to test authorization on
    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('Admin')")
    // Annotation for specifying a method access-control expression, which will be evaluated to decide whether a method invocation is allowed or not.
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping("/forUser")
    @PreAuthorize("hasRole('User')") // @PreAuthorize("hasAnyRole('Admin','User')") -> accessible to Admin and User.
    public String forUser(){
        return "This URL is only accessible to the user"; // accessible to Admin and User.
    }

}
