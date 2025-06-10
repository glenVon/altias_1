package com.altias.altias_1.controller;

import com.altias.altias_1.model.User;
import com.altias.altias_1.service.ServicioUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



class ControlTest {

    @Mock
    private ServicioUsuario servicioUsuario;

    @InjectMocks
    private Control control;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testUser = new User();
        testUser.setId(1L);
        testUser.setNombre("Juan");
        testUser.setNombreUsuario("jdoe");
        testUser.setPassword("1234");
        testUser.setApellido_paterno("Doe");
        testUser.setApellido_materno("Smith");
        testUser.setEmail("juan@example.com");
        testUser.setFecha_nacimiento("1992-04-01");
    }

    // @Test
    // void login_Success() {
    //     when(servicioUsuario.login(any(User.class)))
    //         .thenReturn(ResponseEntity.ok(testUser));

    //     ResponseEntity<?> response = control.login(testUser);

    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals(testUser, response.getBody());
    // }

    @Test
    void createUser_Success() {
        when(servicioUsuario.createUser(any(User.class)))
            .thenReturn(ResponseEntity.ok(testUser));

        ResponseEntity<User> response = control.createUser(testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void getAllUsers_Success() {
        List<User> users = Arrays.asList(testUser);
        when(servicioUsuario.getAllUsers())
            .thenReturn(ResponseEntity.ok(users));

        ResponseEntity<List<User>> response = control.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(testUser, response.getBody().get(0));
    }

    @Test
    void getUserById_Success() {
        when(servicioUsuario.getUserById(1L))
            .thenReturn(ResponseEntity.ok(testUser));

        ResponseEntity<User> response = control.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void deleteUser_Success() {
        when(servicioUsuario.deleteUser(1L))
            .thenReturn(ResponseEntity.ok("Usuario con ID 1 eliminado"));

        ResponseEntity<String> response = control.deleteUser(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario con ID 1 eliminado", response.getBody());
    }
}