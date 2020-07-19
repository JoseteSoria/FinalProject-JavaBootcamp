package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.UserClient;
import com.ironhack.PharmacyEdge.exceptions.UserServiceDownException;
import com.ironhack.PharmacyEdge.model.user.User;
import com.ironhack.PharmacyEdge.model.user.dto.UserDTO;
import com.ironhack.PharmacyEdge.model.user.viewModel.UserVM;
import com.ironhack.PharmacyEdge.security.CustomSecurityUser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserClient userClient;

    @HystrixCommand(fallbackMethod = "errorGetAll")
    public List<UserVM> getAll() {
        LOGGER.info("GET request to retrieve every user");
        return userClient.getAll();
    }

    public List<UserVM> errorGetAll() {
        LOGGER.error("Controlled exception - fail in GET request to retrieve all the users. ");
        throw new UserServiceDownException("User Service Down. Method getAll. ");
    }

    @HystrixCommand(fallbackMethod = "errorGetById")
    public UserVM getById(Integer id) {
        LOGGER.info("GET request to retrieve user with id " + id);
        return userClient.getById(id);
    }

    public UserVM errorGetById(Integer id) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve the user with id " + id);
        throw new UserServiceDownException("User Service Down. Method getById. ");
    }

    @HystrixCommand(fallbackMethod = "errorCreate", ignoreExceptions = {RuntimeException.class})
    public UserVM create(UserDTO newUser) {
        LOGGER.info("POST request to create a new user");
        return userClient.create(newUser);
    }

    public UserVM errorCreate(UserDTO newUser) {
        LOGGER.error("Controlled exception - fail in POST request to create a new user. ");
        throw new UserServiceDownException("User Service Down. Method create. ");
    }

    @HystrixCommand(fallbackMethod = "errorDelete")
    public void delete(Integer id) {
        LOGGER.info("DELETE request to remove user with id " + id);
        userClient.delete(id);
    }

    public void errorDelete(Integer id) {
        LOGGER.error("Controlled exception - fail in DELETE request to remove a user with id: " + id);
        throw new UserServiceDownException("User Service Down. Method delete. ");
    }

    @Override
    @HystrixCommand(fallbackMethod = "errorLoadUserByUsername")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Load user by username " + username);
        Optional<User> user = userClient.findByUsername(username);
        return new CustomSecurityUser(user.orElseThrow(() ->
                new UsernameNotFoundException("Invalid username/password combination.")));
    }

    public UserDetails errorLoadUserByUsername(String username) {
        LOGGER.error("Controlled exception - Fail in Authorization to find user (salesRep) with name " + username);
        throw new UserServiceDownException("User Service Down. Method loadUserByUsername. ");
    }

}
