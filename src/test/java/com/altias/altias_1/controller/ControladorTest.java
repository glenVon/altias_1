// // package com.altias.altias_1.controller;

// // import com.altias.altias_1.model.User;
// // import com.altias.altias_1.service.ServicioUsuario;
// // import org.junit.jupiter.api.BeforeEach;
// // import org.junit.jupiter.api.Test;
// // import org.mockito.InjectMocks;
// // import org.mockito.Mock;
// // import org.mockito.MockitoAnnotations;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// // import org.springframework.boot.test.context.SpringBootTest;
// // import org.springframework.http.HttpStatus;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.test.context.web.WebAppConfiguration;

// // import java.util.Arrays;
// // import java.util.List;

// // import static org.junit.jupiter.api.Assertions.*;
// // import static org.mockito.ArgumentMatchers.any;
// // import static org.mockito.Mockito.verify;
// // import static org.mockito.Mockito.when;

// // @SpringBootTest
// // @WebAppConfiguration
// // @WebMvcTest

// // public class controllerTest {

// //     @Mock
// //     private ServicioUsuario servicioUsuario;

// //     @InjectMocks
// //     private Control control;

// //     @Autowired
// //     private Control controlTest;

// //     private User testUser;

// //     @BeforeEach
// //     void setUp() {
// //         MockitoAnnotations.openMocks(this);

        
// //     }

    

// //     private User newUser() {
// //         User user = new User();
// //         user.setNombre("Juan");
// //         user.setNombreUsuario("jdoe");
// //         user.setPassword("1234");
// //         user.setApellido_paterno("Doe");
// //         user.setApellido_materno("Smith");
// //         user.setEmail("juan@example.com");
// //         user.setFecha_nacimiento("1992-04-01");
// //         return user;
// //     }



//     // @Test
//     // void testCreador() {
//     //     //given
//     //     User newUser = new User(
//     //         "Juan",
//     //         "jdoe",
//     //         "1234",
//     //         "Doe",
//     //         "Smith",
//     //         "egon@von.cl",
//     //         "1992-04-01");
//     //     when(servicioUsuario.crearUsuario(any(User.class))).thenReturn(ResponseEntity.ok(newUser));

//     //     //when
//     //     ResponseEntity<String> response = control.crearUsuario(newUser);

//     //     //then
//     //     assertEquals(HttpStatus.CREATED, response.getStatusCode());
//     //     assertEquals("todo bien", response.getBody());  // Compara el body con "todo bien"
//     //     // O (si esperas código 200):
//     //     assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());  // Para validar código 200
//     // }

//     // @Test
//     // void testCrearUsuario() {
//     // // Given: Configuración inicial
//     // User newUser = new User(
//     //     "Juan",
//     //     "jdoe",
//     //     "1234",
//     //     "Doe",
//     //     "Smith",
//     //     "egon@von.cl",
//     //     "1992-04-01");
    
//     // // Configuración más específica del mock
//     // when(servicioUsuario.crearUsuario(newUser)).thenReturn(ResponseEntity.ok(newUser));

//     // // When: Ejecución del método a testear
//     // ResponseEntity<String> response = control.crearUsuario(newUser);

//     // // Then: Verificaciones
//     // // 1. Verificar código de estado (debe ser consistente)
//     // assertEquals(HttpStatus.CREATED, response.getStatusCode());
    
//     // // 2. Verificar cuerpo de respuesta (mejor usar constante o matcher)
//     // assertEquals("Usuario creado exitosamente", response.getBody());
    
//     // // Verificación adicional: que el servicio fue llamado con el usuario correcto
//     // verify(servicioUsuario).crearUsuario(newUser);
//     //         System.out.println("Probando getUserById...");

//     // }

//     // @Test
//     // void testGetAllUsers() {
//     //     // Given: Configuración inicial
//     //     List<User> users = Arrays.asList(newUser(), newUser());
//     //     when(servicioUsuario.getAllUsers()).thenReturn(ResponseEntity.ok(users));

//     //     // When: Ejecución del método a testear
//     //     ResponseEntity<List<User>> response = control.getAllUsers();

//     //     // Then: Verificaciones
//     //     assertEquals(HttpStatus.OK, response.getStatusCode());
//     //     assertEquals(users, response.getBody());
//     // }

//     // @Test
//     // void testGetUserById() {
//     //     // Given: Configuración inicial
//     //     User user = newUser();
//     //     user.setId(1L);
//     //     when(servicioUsuario.getUserById(1L)).thenReturn(ResponseEntity.ok(user));
                


//     //     // When: Ejecución del método a testear
//     //     ResponseEntity<User> response = control.getUserById(1L);

//     //     // Then: Verificaciones
//     //     assertEquals(HttpStatus.OK, response.getStatusCode());
//     //     assertEquals(user, response.getBody());
//     // }

//     // @Test
//     // void testDeleteUser() {
//     //     // Given: Configuración inicial
//     //     Long userId = 1L;
//     //     when(servicioUsuario.deleteUser(userId)).thenReturn(ResponseEntity.ok("Usuario eliminado exitosamente"));

//     //     // When: Ejecución del método a testear
//     //     ResponseEntity<String> response = control.deleteUser(userId);

//     //     // Then: Verificaciones
//     //     assertEquals(HttpStatus.OK, response.getStatusCode());
//     //     assertEquals("Usuario eliminado exitosamente", response.getBody());
//     // }

//     // @Test
//     // void testLogin() {
//     //     // Given: Configuración inicial
//     //     User user = newUser();
//     //     user.setNombreUsuario("admin");
//     //     user.setPassword("1234");
//     //     when(servicioUsuario.authenticatedUser(user)).thenReturn(ResponseEntity.ok(user));

//     //     // When: Ejecución del método a testear
//     //     ResponseEntity<?> response = control.login(user);

//     //     // Then: Verificaciones
//     //     assertEquals(HttpStatus.OK, response.getStatusCode());
//     //     assertEquals(user, response.getBody());
//     // }

//     // @Test
//     // void testGetDatosProtegidos() {
//     //     // Given: Configuración inicial
//     //     User user = newUser();
//     //     user.setNombreUsuario("admin");
//     //     user.setPassword("1234");
//     //     when(servicioUsuario.authenticatedUser(user)).thenReturn(ResponseEntity.ok(user));

//     //     // When: Ejecución del método a testear
//     //     ResponseEntity<?> response = control.getDatosProtegidos("admin", "1234");

//     //     // Then: Verificaciones
//     //     assertEquals(HttpStatus.OK, response.getStatusCode());
//     //     assertEquals(user, response.getBody());
//     // }

    

// //}

package com.altias.altias_1.controller;

import com.altias.altias_1.model.User;
import com.altias.altias_1.service.ServicioUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(Control.class)
public class ControladorTest {

    @Mock
    private ServicioUsuario servicioUsuario;

    @InjectMocks
    private Control controlador;

    private User usuarioDePrueba;

    @BeforeEach
    void configuracionInicial() {
        MockitoAnnotations.openMocks(this);
        usuarioDePrueba = crearUsuarioEjemplo();
    }

    private User crearUsuarioEjemplo() {
        User usuario = new User();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setNombreUsuario("jdoe");
        usuario.setPassword("1234");
        usuario.setApellido_paterno("Doe");
        usuario.setApellido_materno("Smith");
        usuario.setEmail("juan@example.com");
        //usuario.setFecha_nacimiento("1992-04-01");
        return usuario;
    }

    @Test
    void testCrearUsuario() {
        // Configuración
        when(servicioUsuario.createUser(any(User.class))).thenReturn(ResponseEntity.ok(usuarioDePrueba));

        // Ejecución
        ResponseEntity<User> respuesta = controlador.createUser(usuarioDePrueba);

        // Verificación
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(usuarioDePrueba, respuesta.getBody());
        verify(servicioUsuario).createUser(usuarioDePrueba);
    }

    @Test
    void testObtenerTodosUsuarios() {
        // Configuración
        List<User> usuarios = Arrays.asList(usuarioDePrueba);
        when(servicioUsuario.getAllUsers()).thenReturn(ResponseEntity.ok(usuarios));

        // Ejecución
        ResponseEntity<List<User>> respuesta = controlador.getAllUsers();

        // Verificación
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(usuarios, respuesta.getBody());
    }

    @Test
    void testObtenerUsuarioPorId() {
        // Configuración
        when(servicioUsuario.getUserById(1L)).thenReturn(ResponseEntity.ok(usuarioDePrueba));

        // Ejecución
        ResponseEntity<User> respuesta = controlador.getUserById(1L);

        // Verificación
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(usuarioDePrueba, respuesta.getBody());
    }

    @Test
    void testEliminarUsuario() {
        // Configuración
        when(servicioUsuario.deleteUser(1L)).thenReturn(ResponseEntity.ok("Usuario con ID 1 eliminado"));

        // Ejecución
        ResponseEntity<String> respuesta = controlador.deleteUser(1L);

        // Verificación
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("Usuario con ID 1 eliminado", respuesta.getBody());
    }

    

    
}

