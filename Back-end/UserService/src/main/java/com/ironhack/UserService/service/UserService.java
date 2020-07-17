package com.ironhack.UserService.service;

import com.ironhack.UserService.exceptions.IllegalInputException;
import com.ironhack.UserService.exceptions.ResourceNotFoundException;
import com.ironhack.UserService.model.User;
import com.ironhack.UserService.model.dto.UserDTO;
import com.ironhack.UserService.model.viewModel.UserVM;
import com.ironhack.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserVM> getAll(){
        LOGGER.info("GET request to retrieve every user");
        return userRepository.findAll().stream().map(salesRep -> new UserVM(salesRep.getId(), salesRep.getName(), salesRep.getUsername(), salesRep.getRole())).collect(Collectors.toList());
    }

    public UserVM getById(Integer id){
        LOGGER.info("GET request to retrieve user with id " + id);
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " not found"));
        return new UserVM(user.getId(), user.getName(), user.getUsername(), user.getRole());
    }

    public UserVM store(UserDTO userDTO){
        Optional<User> foundUser = userRepository.findByUsername(userDTO.getUsername());
        if(foundUser.isPresent()){
            LOGGER.error("Controlled exception - Username " + userDTO.getUsername() + " is already taken");
            throw new IllegalInputException("Username " + userDTO.getUsername() + " is already taken");
        }

        LOGGER.info("POST request to create a new user");
        User user = userRepository.save(new User(userDTO.getUsername(), userDTO.getName(),
                passwordEncoder.encode(userDTO.getPassword()), userDTO.getRole()));

        LOGGER.info("User created: " + user);
        return new UserVM(user.getId(), user.getName(), user.getUsername(), user.getRole());
    }

    public void delete(Integer id) {
        LOGGER.info("DELETE request to remove User with id " + id);
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
    }

    public Optional<User> findByUsername(String username) {
        LOGGER.info("GET request to retrieve user with username " + username);
        Optional<User> result = userRepository.findByUsername(username);
        return result;
    }
}
