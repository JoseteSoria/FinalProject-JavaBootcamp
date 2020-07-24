package com.ironhack.PharmacyEdge.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.PharmacyEdge.model.user.User;
import com.ironhack.PharmacyEdge.model.user.dto.UserDTO;
import com.ironhack.PharmacyEdge.model.user.viewModel.UserVM;
import com.ironhack.PharmacyEdge.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static com.ironhack.PharmacyEdge.enums.Role.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UserVM user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user = new UserVM(1, "OWNER", "owner", ROLE_OWNER);
        UserVM user2 = new UserVM(1, "ASSISTANT1", "assistant1", ROLE_ASSISTANT);

        List<UserVM> users = Arrays.asList(user, user2);
        when(userService.getAll()).thenReturn(users);
        when(userService.getById(user.getId())).thenReturn(user);
        User realUser = new User("OWNER", "owner", "$2a$10$4ezwkorIoW.TIwCj2XmKXOAUwpF5G8DZNQyKRWe77AoZlEfWfaK22", ROLE_OWNER);
        userDTO = new UserDTO("ASSISTANT1", "assistant1", "assistant1", ROLE_ASSISTANT);
        when(userService.create(userDTO)).thenReturn(user2);
        doAnswer(i -> {
            return null;
        }).when(userService).delete(user.getId());
    }


    @Test
    @DisplayName("Test get request to retrieve every user")
    void getAll() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test get request to retrieve a user with specific id")
    void getById() throws Exception {
        mockMvc.perform(get("/users/" + user.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test post request to create new user, expected 201 status code")
    void create() throws Exception {
        MvcResult result = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(userDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    @DisplayName("Test delete request to remove user, expected 204 status code")
    void deleteUserById() throws Exception {
        mockMvc.perform(delete("/users/" + user.getId())).andExpect(status().isNoContent());
    }

}