package com.ironhack.PharmacyEdge.controller.interfaces;

import com.ironhack.PharmacyEdge.enums.Role;
import com.ironhack.PharmacyEdge.model.user.dto.LoginDTO;
import com.ironhack.PharmacyEdge.model.user.dto.UserDTO;
import com.ironhack.PharmacyEdge.model.user.viewModel.UserVM;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IUserController {
    public List<UserVM> getAll();

    public UserVM getById(Integer id);

    public UserVM create(UserDTO newUser);

    public void delete(Integer id);

    public Role login(LoginDTO loginDTO);
}
