package com.GraduationProject.ecommerce.service;

import com.GraduationProject.ecommerce.dao.RoleDao;
import com.GraduationProject.ecommerce.dao.UserDao;
import com.GraduationProject.ecommerce.entity.Role;
import com.GraduationProject.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    public void initRolesAndUsers() {

        // the admin role
        Role adminRole = new Role("Admin", "Admin role for the application");
//        roleDao.save(adminRole); // we can dispense this because of cascade.PERSIST as it will automatically save Set<Role> roles after saving the user to the db

        // the user role
        Role userRole = new Role("User", "Default role for newly created user");
//        roleDao.save(userRole); // we can dispense this because of cascade.PERSIST as it will automatically save Set<Role> roles after saving the user to the db

        // default admin in the app
        User admin = new User("Mustafa2002", "mustafa", "zayed", "mustafa@pass");
        admin.addRole(adminRole);
        userDao.save(admin);
//        User admin=new User();
//        admin.setUserName("Mustafa2002");
//        admin.setUserPassword("mustafa@pass");
//        admin.setUserFirstName("mustafa");
//        admin.setUserLastName("zayed");
        // default user in the app
        User user = new User("Moaz2002", "moaz", "ehab", "moaz@pass");
        user.addRole(userRole);
        userDao.save(user);

    }

    public User registerNewUser(User user) {
        return userDao.save(user);
    }
}
