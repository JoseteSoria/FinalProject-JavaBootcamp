package com.ironhack.UserService.service;

import com.ironhack.UserService.enums.Role;
import com.ironhack.UserService.model.User;
import com.ironhack.UserService.model.dto.UserDTO;
import com.ironhack.UserService.model.viewModel.UserVM;
import com.ironhack.UserService.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User( "Groucho Marx", "groucho","1234", Role.ROLE_OWNER);
        user.setId(1);
        User user2 = new User("Harpo Marx","harpo","1234", Role.ROLE_ASSISTANT);
        User user3 = new User("Chico Marx", "chico", "1234", Role.ROLE_PHARMACIST);

        List<User> userList = Arrays.asList(user, user2);

        // mock responses
        when(userRepository.findAll()).thenReturn(userList);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user3);
        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.findByUsername("groucho")).thenReturn(Optional.of(user));
    }

    @Test
    @DisplayName("Unit test - retrieval of all users")
    void getAll() {
        assertEquals(2, userService.getAll().size());
    }

    @Test
    @DisplayName("Unit test - retrieval of a user with specific id")
    void getById() {
        assertEquals("groucho", userService.getById(1).getUsername());
    }

    @Test
    @DisplayName("Unit test - create new user")
    void create() {
        UserDTO userDTO = new UserDTO("Chico Marx", "chico", "1234", Role.ROLE_PHARMACIST);
        UserVM savedUser = userService.store(userDTO);
        assertEquals("chico", savedUser.getUsername());
    }

    @Test
    @DisplayName("Unit test - delete new user")
    void delete() {
        userService.delete(1);
        verify(userRepository, times(1)).deleteById(user.getId());
    }

    @Test
    @DisplayName("Unit test - retrieval of a user by username")
    void findByUsername() {
        assertEquals("groucho", userService.findByUsername("groucho").get().getUsername());
    }
}