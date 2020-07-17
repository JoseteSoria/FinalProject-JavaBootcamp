package com.ironhack.UserService.controller.implementations;


import com.ironhack.UserService.controller.interfaces.IUserController;
import com.ironhack.UserService.model.User;
import com.ironhack.UserService.model.dto.UserDTO;
import com.ironhack.UserService.model.viewModel.UserVM;
import com.ironhack.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController implements IUserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserVM> getAll() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserVM getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserVM create(@RequestBody @Valid UserDTO userDTO) {
        return userService.store(userDTO);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @GetMapping( "/users/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> findByUsername(@PathVariable(name = "username") String username) {
        return userService.findByUsername(username);
    }
}
