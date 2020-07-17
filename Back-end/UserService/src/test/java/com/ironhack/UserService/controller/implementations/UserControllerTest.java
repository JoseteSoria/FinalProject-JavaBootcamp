package com.ironhack.UserService.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.UserService.enums.Role;
import com.ironhack.UserService.model.User;
import com.ironhack.UserService.model.dto.UserDTO;
import com.ironhack.UserService.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        user = new User( "Groucho Marx", "groucho","1234", Role.ROLE_OWNER);
        User user1 = new User("Harpo Marx","harpo","1234", Role.ROLE_ASSISTANT);

        userRepository.saveAll(Stream.of(user, user1).collect(Collectors.toList()));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Test get request to retrieve every user")
    void getAll() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test get request to retrieve a user with specific id")
    void getById() throws Exception {
        MvcResult result = mockMvc.perform(get("/users/" + user.getId()))
                .andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Groucho"));
    }

    @Test
    @DisplayName("Test get request when user with id doesn't exist, expected 404 status code")
    void getById_WrongId() throws Exception {
        mockMvc.perform(get("/users/0")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test post request to create new user, expected 201 status code")
    void create() throws Exception {
        UserDTO userDTO = new UserDTO("Chico Marx", "chico", "1234", Role.ROLE_PHARMACIST);
        MvcResult result = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(userDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Chico"));
    }

    @Test
    @DisplayName("Test delete request to remove user, expected 204 status code")
    void deleteUserById() throws Exception {
        mockMvc.perform(delete("/users/" + user.getId())).andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Test delete request to remove non-existing user, expected 404 status code")
    void deleteUserById_WrongId() throws Exception {
        mockMvc.perform(delete("/users/0")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test get request to retrieve user by username")
    void findByUsername() throws Exception {
        mockMvc.perform(get("/users/username/" + user.getUsername())).andExpect(status().isOk());
    }

}