package com.altias.altias_1.service;

import com.altias.altias_1.model.User;
import com.altias.altias_1.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServicioUsuarioTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ServicioUsuario servicioUsuario;

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
        //testUser.setFecha_nacimiento("1992-04-01");
    }

    @Test
    void login_Success() {
        when(userRepository.findByNombreUsuarioAndPassword("admin", "1234"))
            .thenReturn(testUser);

        ResponseEntity<?> response = servicioUsuario.login(testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void login_Failure() {
        when(userRepository.findByNombreUsuarioAndPassword(anyString(), anyString()))
            .thenReturn(null);

        ResponseEntity<?> response = servicioUsuario.login(testUser);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("No autorizado", response.getBody());
    }

    @Test
    void createUser_Success() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        ResponseEntity<User> response = servicioUsuario.createUser(testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void getAllUsers_Success() {
        List<User> users = Arrays.asList(testUser);
        when(userRepository.findAll()).thenReturn(users);

        ResponseEntity<List<User>> response = servicioUsuario.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(testUser, response.getBody().get(0));
    }

    @Test
    void getUserById_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        ResponseEntity<User> response = servicioUsuario.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getNombre());
        assertEquals("jdoe", response.getBody().getNombreUsuario());
    }

    @Test
    void getUserById_NotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<User> response = servicioUsuario.getUserById(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteUser_Success() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);

        ResponseEntity<String> response = servicioUsuario.deleteUser(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario con ID 1 eliminado", response.getBody());
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_NotFound() {
        when(userRepository.existsById(99L)).thenReturn(false);

        ResponseEntity<String> response = servicioUsuario.deleteUser(99L);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Usuario no encontrado", response.getBody());
        verify(userRepository, never()).deleteById(anyLong());
    }
}