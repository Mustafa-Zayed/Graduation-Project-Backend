package com.GraduationProject.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

    // fields
    @Id
    private String roleName;
    private String roleDescription;

    //constructors
    public Role() {
    }

    public Role(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    // getters & setters
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
