package com.ironhack.PharmacyEdge.controller.implementations;

import com.ironhack.PharmacyEdge.controller.interfaces.IUserController;
import com.ironhack.PharmacyEdge.enums.Role;
import com.ironhack.PharmacyEdge.model.user.dto.LoginDTO;
import com.ironhack.PharmacyEdge.model.user.dto.UserDTO;
import com.ironhack.PharmacyEdge.model.user.viewModel.UserVM;
import com.ironhack.PharmacyEdge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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
    public UserVM create(@RequestBody @Valid UserDTO newUser) {
        return userService.create(newUser);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PostMapping("/users/login")
    @ResponseStatus(HttpStatus.OK)
    public Role login(@RequestBody LoginDTO loginDTO) {
        return userService.getByUsername(loginDTO);
    }

}
