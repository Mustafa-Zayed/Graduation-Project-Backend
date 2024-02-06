package com.GraduationProject.ecommerce.controller;

import com.GraduationProject.ecommerce.entity.User;
import com.GraduationProject.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
