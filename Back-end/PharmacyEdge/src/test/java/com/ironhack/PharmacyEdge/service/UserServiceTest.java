package com.ironhack.PharmacyEdge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.PharmacyEdge.client.UserClient;
import com.ironhack.PharmacyEdge.enums.Role;
import com.ironhack.PharmacyEdge.exceptions.UserServiceDownException;
import com.ironhack.PharmacyEdge.model.user.User;
import com.ironhack.PharmacyEdge.model.user.dto.LoginDTO;
import com.ironhack.PharmacyEdge.model.user.dto.UserDTO;
import com.ironhack.PharmacyEdge.model.user.viewModel.UserVM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.ironhack.PharmacyEdge.enums.Role.ROLE_ASSISTANT;
import static com.ironhack.PharmacyEdge.enums.Role.ROLE_OWNER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private UserClient userClient;
    @Autowired
    private UserService userService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserVM user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user = new UserVM(1, "OWNER", "owner", ROLE_OWNER);
        UserVM user2 = new UserVM(1, "ASSISTANT1", "assistant1", ROLE_ASSISTANT);

        List<UserVM> users = Arrays.asList(user, user2);
        when(userClient.getAll()).thenReturn(users);
        when(userClient.getById(user.getId())).thenReturn(user);
        User realUser = new User("OWNER", "owner", "$2a$10$4ezwkorIoW.TIwCj2XmKXOAUwpF5G8DZNQyKRWe77AoZlEfWfaK22", ROLE_OWNER);
        userDTO = new UserDTO("ASSISTANT1", "assistant1", "assistant1", ROLE_ASSISTANT);
        when(userClient.create(userDTO)).thenReturn(user2);
        when(userClient.findByUsername("owner")).thenReturn(Optional.of(realUser));
        doAnswer(i -> {
            return null;
        }).when(userClient).delete(user.getId());
    }

    @Test
    void getAllUser() throws Exception {
        List<UserVM> userVMS = userService.getAll();
        assertEquals(2, userVMS.size());
        assertEquals(Role.ROLE_OWNER, userVMS.get(0).getRole());
    }

    @Test
    void errorGetAll() throws Exception {
        assertThrows(UserServiceDownException.class, () -> userService.errorGetAll());
    }

    @Test
    void getById() throws Exception {
        UserVM userVM = userService.getById(user.getId());
        assertEquals(ROLE_OWNER, userVM.getRole());
    }

    @Test
    void errorGetById() throws Exception {
        assertThrows(UserServiceDownException.class, () -> userService.errorGetById(0));
    }

    @Test
    void createUser() throws Exception {
        UserVM userVM = userService.create(userDTO);
        assertEquals("ASSISTANT1", userVM.getName());
    }

    @Test
    void errorCreateUser() throws Exception {
        assertThrows(UserServiceDownException.class, () -> userService.errorCreate(null));
    }

    @Test
    void deleteUser() throws Exception {
        userService.delete(user.getId());
    }

    @Test
    void errorDeleteUser() throws Exception {
        assertThrows(UserServiceDownException.class, () -> userService.errorDelete(0));
    }

    @Test
    void loadByUsername() throws Exception {
        UserDetails user = userService.loadUserByUsername("owner");
        assertEquals("owner", user.getUsername());
    }

    @Test
    void errorLoadByUserName() throws Exception {
        assertThrows(UserServiceDownException.class, () -> userService.errorLoadUserByUsername("aappaco"));
    }

    @Test
    void getByUsername() throws Exception {
        LoginDTO loginDTO = new LoginDTO("owner", "owner");
        assertEquals(ROLE_OWNER, userService.getByUsername(loginDTO));
    }
}