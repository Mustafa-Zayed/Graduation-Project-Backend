package com.GraduationProject.ecommerce.service;

import com.GraduationProject.ecommerce.dao.RoleDao;
import com.GraduationProject.ecommerce.dao.UserDao;
import com.GraduationProject.ecommerce.entity.Role;
import com.GraduationProject.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRolesAndUsers() {

        // the admin role
        Role adminRole = new Role("Admin", "Admin role for the application");
//        roleDao.save(adminRole); // we can dispense this because of cascade.PERSIST as it will automatically save Set<Role> roles after saving the user to the db

        // the user role
        Role userRole = new Role("User", "Default role for newly created user");
//        roleDao.save(userRole); // we can dispense this because of cascade.PERSIST as it will automatically save Set<Role> roles after saving the user to the db

        // default admin in the app
        User admin = new User("MustafaZ", "mustafa", "zayed", getEncodedPassword("mustafa@pass"));
        admin.addRole(adminRole);
        userDao.save(admin);

        // default user in the app
        User user = new User("Moaz2002", "moaz", "ehab", getEncodedPassword("moaz@pass"));
        user.addRole(userRole);
        userDao.save(user);

    }

    public User registerNewUser(User user) {

        Role userRole = roleDao.findById("User").get();

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
