package com.altias.altias_1.service;

import com.altias.altias_1.model.User;
import com.altias.altias_1.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicioUsuarioTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ServicioUsuario servicioUsuario;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setNombre("Juan");
        testUser.setNombreUsuario("jdoe");
        testUser.setPassword("1234");
        testUser.setApellido_paterno("Doe");
        testUser.setApellido_materno("Smith");
        testUser.setEmail("juan@example.com");
    }

    @Test
    void createUser_Success() {
        // Configuración del mock
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Ejecución del método
        ResponseEntity<User> response = servicioUsuario.createUser(testUser);

        // Verificaciones
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testUser.getId(), response.getBody().getId());
        assertEquals(testUser.getNombre(), response.getBody().getNombre());
        
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(anyString());
    }

    @Test
    void getAllUsers_Success() {
        // Configuración del mock
        List<User> users = Arrays.asList(testUser);
        when(userRepository.findAll()).thenReturn(users);

        // Ejecución del método
        ResponseEntity<List<User>> response = servicioUsuario.getAllUsers();

        // Verificaciones
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(testUser, response.getBody().get(0));
        
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_Success() {
    // 1. Configuración del mock - crea un objeto idéntico al que devuelve el servicio
    User userFromService = new User();
    userFromService.setId(1L);
    userFromService.setNombre("Juan");
    userFromService.setNombreUsuario("jdoe");
    userFromService.setPassword(null); // Password null como lo devuelve el servicio
    userFromService.setApellido_paterno("Doe");
    userFromService.setApellido_materno("Smith");
    userFromService.setEmail("juan@example.com");
    
    when(userRepository.findById(1L)).thenReturn(Optional.of(userFromService));

    // 2. Ejecución del método
    ResponseEntity<User> response = servicioUsuario.getUserById(1L);

    // 3. Verificaciones
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    
    // Comparación campo por campo (más seguro)
    assertEquals(userFromService.getId(), response.getBody().getId());
    assertEquals(userFromService.getNombre(), response.getBody().getNombre());
    assertEquals(userFromService.getNombreUsuario(), response.getBody().getNombreUsuario());
    assertNull(response.getBody().getPassword()); // Asegura que el password es null
    assertEquals(userFromService.getApellido_paterno(), response.getBody().getApellido_paterno());
    assertEquals(userFromService.getApellido_materno(), response.getBody().getApellido_materno());
    assertEquals(userFromService.getEmail(), response.getBody().getEmail());
    
    verify(userRepository, times(1)).findById(1L);
}

    @Test
    void getUserById_NotFound() {
        // Configuración del mock (sin stubbing innecesario)
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Ejecución del método
        ResponseEntity<User> response = servicioUsuario.getUserById(99L);

        // Verificaciones
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        
        verify(userRepository, times(1)).findById(99L);
    }

    @Test
    void deleteUser_Success() {
        // Configuración del mock
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);

        // Ejecución del método
        ResponseEntity<String> response = servicioUsuario.deleteUser(1L);

        // Verificaciones
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario con ID 1 eliminado", response.getBody());
        
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_NotFound() {
        // Configuración del mock (sin stubbing innecesario)
        when(userRepository.existsById(99L)).thenReturn(false);

        // Ejecución del método
        ResponseEntity<String> response = servicioUsuario.deleteUser(99L);

        // Verificaciones
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuario no encontrado", response.getBody());
        
        verify(userRepository, times(1)).existsById(99L);
        verify(userRepository, never()).deleteById(anyLong());
    }
}