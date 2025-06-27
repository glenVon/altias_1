// package com.altias.altias_1.controller;

// import com.altias.altias_1.model.User;
// import com.altias.altias_1.service.ServicioUsuario;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.web.WebAppConfiguration;

// import java.util.Arrays;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// @SpringBootTest
// @WebAppConfiguration
// @WebMvcTest

// public class controllerTest {

//     @Mock
//     private ServicioUsuario servicioUsuario;

//     @InjectMocks
//     private Control control;

//     @Autowired
//     private Control controlTest;

//     private User testUser;

//     private Object user;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);

        
//     }

    

//     private User newUser() {
//         User user = new User();
//         user.setNombre("Juan");
//         user.setNombreUsuario("jdoe");
//         user.setPassword("1234");
//         user.setApellido_paterno("Doe");
//         user.setApellido_materno("Smith");
//         user.setEmail("juan@example.com");
//         //user.setFecha_nacimiento("1992-04-01");
//         return user;
//     }



//     @Test
//     void testCreador() {
//         //given
//         User newUser = new User();

//         //when
//         ResponseEntity<User> response = control.crearUsuario(user);

//         //then
//         assertEquals(HttpStatus.CREATED, response.getStatusCode());
//         assertEquals("todo bien", response.getBody());  // Compara el body con "todo bien"
//         // O (si esperas código 200):
//         assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());  // Para validar código 200
//     }

//     @Test
//     void testCrearUsuario() {
//     // Given: Configuración inicial
//     User newUser = new User(user);
        
    
//     // Configuración más específica del mock
//     when(servicioUsuario.crearUsuario(newUser)).thenReturn(ResponseEntity.ok(newUser));

//     // When: Ejecución del método a testear
//     ResponseEntity<String> response = control.crearUsuario(newUser);

//     // Then: Verificaciones
//     // 1. Verificar código de estado (debe ser consistente)
//     assertEquals(HttpStatus.CREATED, response.getStatusCode());
    
//     // 2. Verificar cuerpo de respuesta (mejor usar constante o matcher)
//     assertEquals("Usuario creado exitosamente", response.getBody());
    
//     // Verificación adicional: que el servicio fue llamado con el usuario correcto
//     verify(servicioUsuario).crearUsuario(newUser);
//             System.out.println("Probando getUserById...");

//     }

//     @Test
//     void testGetAllUsers() {
//         // Given: Configuración inicial
//         List<User> users = Arrays.asList(newUser(), newUser());
//         when(servicioUsuario.getAllUsers()).thenReturn(ResponseEntity.ok(users));

//         // When: Ejecución del método a testear
//         ResponseEntity<List<User>> response = control.getAllUsers();

//         // Then: Verificaciones
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(users, response.getBody());
//     }

//     @Test
//     void testGetUserById() {
//         // Given: Configuración inicial
//         User user = newUser();
//         user.setId(1L);
//         when(servicioUsuario.getUserById(1L)).thenReturn(ResponseEntity.ok(user));
                


//         // When: Ejecución del método a testear
//         ResponseEntity<User> response = control.getUserById(1L);

//         // Then: Verificaciones
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(user, response.getBody());
//     }

//     @Test
//     void testDeleteUser() {
//         // Given: Configuración inicial
//         Long userId = 1L;
//         when(servicioUsuario.deleteUser(userId)).thenReturn(ResponseEntity.ok("Usuario eliminado exitosamente"));

//         // When: Ejecución del método a testear
//         ResponseEntity<String> response = control.deleteUser(userId);

//         // Then: Verificaciones
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals("Usuario eliminado exitosamente", response.getBody());
//     }

//     @Test
//     void testLogin() {
//         // Given: Configuración inicial
//         User user = newUser();
//         user.setNombreUsuario("admin");
//         user.setPassword("1234");
//         when(servicioUsuario.authenticatedUser(user)).thenReturn(ResponseEntity.ok(user));

//         // When: Ejecución del método a testear
//         ResponseEntity<?> response = control.login(user);

//         // Then: Verificaciones
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(user, response.getBody());
//     }

//     @Test
//     void testGetDatosProtegidos() {
//         // Given: Configuración inicial
//         User user = newUser();
//         user.setNombreUsuario("admin");
//         user.setPassword("1234");
//         when(servicioUsuario.authenticatedUser(user)).thenReturn(ResponseEntity.ok(user));

//         // When: Ejecución del método a testear
//         ResponseEntity<?> response = control.getDatosProtegidos("admin", "1234");

//         // Then: Verificaciones
//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(user, response.getBody());
//     }

    

// }

package com.altias.altias_1.controller;

import com.altias.altias_1.model.User;
import com.altias.altias_1.service.ServicioUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(Control.class)
@Import(ServicioUsuario.class)

public class ControladorTest {

    // @Mock
    // private ServicioUsuario ServicioUsuario;

    @BeforeEach
    void setUp() {
        usuarioDePrueba = new User();
        usuarioDePrueba.setId(1L);
        usuarioDePrueba.setNombre("Juan");
        usuarioDePrueba.setNombreUsuario("jdoe");
        usuarioDePrueba.setPassword("1234");
        usuarioDePrueba.setApellido_paterno("Doe");
        usuarioDePrueba.setApellido_materno("Smith");
        usuarioDePrueba.setEmail("juan@example.com");
        usuarioDePrueba.setFecha_nacimiento(LocalDate.of(1990, 1, 1));
    }   

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private ServicioUsuario ServicioUsuario;

    // @Autowired
    // private ObjectMapper objectMapper;

    @InjectMocks
    private Control control;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private User usuarioDePrueba;

    @BeforeEach
    void configuracionInicial() {
        MockitoAnnotations.openMocks(this);
        User usuarioMock = crearUsuarioEjemplo();
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
        usuario.setFecha_nacimiento(LocalDate.of(1990, 1, 1));
        return usuario;
    }

    @Test
    public void testCrearUsuario() throws Exception {
        // Configuración del mock
        when(ServicioUsuario.createUser(any(User.class)))
        .thenReturn(ResponseEntity.ok(usuarioDePrueba));

        // Ejecución y verificación
        mockMvc.perform(post("/api/v1/users/creador")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(usuarioDePrueba)))
               .andDo(MockMvcResultHandlers.print()) // Para depuración
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1L))
               .andExpect(jsonPath("$.nombre").value("Juan"));
               //.andExpect(jsonPath("$.email").value("juan@example.com"));
    }


    @Test
    void testObtenerTodosUsuarios() throws Exception {
        // 1. Configura el mock
        List<User> usuarios = List.of(usuarioDePrueba);
        when(ServicioUsuario.getAllUsers())
            .thenReturn(ResponseEntity.ok(usuarios));
        
        // 2. Ejecuta y verifica la petición HTTP
        mockMvc.perform(get("/api/v1/users/listar"))
               //.andDo(print()) // Opcional: muestra detalles de la petición/respuesta
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id").value(1L))
               .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }


    @Test
    void testObtenerUsuarioPorId() throws Exception {
    // 1. Configura el comportamiento del mock
    when(ServicioUsuario.getUserById(1L))
        .thenReturn(ResponseEntity.ok(usuarioDePrueba));

    // 2. Ejecuta la petición HTTP simulada
    mockMvc.perform(get("/api/v1/users/usuarioPorId/{id}", 1L)
           .contentType(MediaType.APPLICATION_JSON))
           //.andDo(print())  // Opcional: para ver detalles del request/response
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.id").value(1L))
           .andExpect(jsonPath("$.nombre").value("Juan"));
}

    @Test
    void testEliminarUsuario() throws Exception {
    // 1. Configura el comportamiento esperado del mock
    when(ServicioUsuario.deleteUser(1L))
        .thenReturn(ResponseEntity.ok("Usuario con ID 1 eliminado"));

    // 2. Ejecuta la petición HTTP simulada
    mockMvc.perform(delete("/api/v1/users/eliminar/{id}", 1L)
           .contentType(MediaType.APPLICATION_JSON))
           //.andDo(print())  // Opcional: muestra detalles de la petición/respuesta
           .andExpect(status().isOk())
           .andExpect(content().string("Usuario con ID 1 eliminado"));
}

    // @Test
    // void testLogin() {
    //     // Given: Configuración inicial
    //     when(ServicioUsuario.authenticatedUser(usuarioDePrueba)).thenReturn((ResponseEntity<?>) ResponseEntity.ok(usuarioDePrueba));

    //     // When: Ejecución del método a testear
    //     ResponseEntity<?> response = control.login(usuarioDePrueba);

    //     // Then: Verificaciones
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals(usuarioDePrueba, response.getBody());
    // }

    @Test
    void testSimple() {
        System.out.println("Probando controlador...");
    }

    
}

