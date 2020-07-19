package com.ironhack.PharmacyEdge.model.user.viewModel;

import com.ironhack.PharmacyEdge.enums.Role;

public class UserVM {
    private Integer id;
    private String name;
    private String username;
    private Role role;

    public UserVM(Integer id, String name, String username, Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
