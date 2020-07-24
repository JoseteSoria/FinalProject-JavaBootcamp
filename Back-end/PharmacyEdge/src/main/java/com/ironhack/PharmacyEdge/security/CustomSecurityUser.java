package com.ironhack.PharmacyEdge.security;

import com.ironhack.PharmacyEdge.enums.Role;
import com.ironhack.PharmacyEdge.model.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomSecurityUser extends User implements UserDetails, Authentication {
    private static final long serialVersionUID = -4381938875186527688L;
    private Role role;

    public CustomSecurityUser(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.role = user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Role getRole() {
        return role;
    }
}
