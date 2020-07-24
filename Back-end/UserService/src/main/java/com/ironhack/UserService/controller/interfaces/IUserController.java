package com.ironhack.UserService.controller.interfaces;

import com.ironhack.UserService.model.User;
import com.ironhack.UserService.model.dto.UserDTO;
import com.ironhack.UserService.model.viewModel.UserVM;

import java.util.List;
import java.util.Optional;

public interface IUserController {
    List<UserVM> getAll();
    UserVM getById(Integer id);
    UserVM create(UserDTO userDTO);
    void delete(Integer id);
    Optional<User> findByUsername(String username);
}
