package com.ironhack.PharmacyEdge.client;

import com.ironhack.PharmacyEdge.model.user.User;
import com.ironhack.PharmacyEdge.model.user.dto.UserDTO;
import com.ironhack.PharmacyEdge.model.user.viewModel.UserVM;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users")
    public List<UserVM> getAll();

    @GetMapping("/users/{id}")
    public UserVM getById(@PathVariable Integer id);

    @PostMapping("/users")
    public UserVM create(@RequestBody @Valid UserDTO userDTO);

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Integer id);

    @GetMapping("/users/username/{username}")
    public Optional<User> findByUsername(@PathVariable(name = "username") String username);
}
