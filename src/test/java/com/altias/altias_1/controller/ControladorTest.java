package com.altias.altias_1.controller;

import com.altias.altias_1.model.User;
import com.altias.altias_1.service.ServicioUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Control.class)
@Import(ServicioUsuario.class)
public class ControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioUsuario servicioUsuario;

    @InjectMocks
    private Control control;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private User usuarioDePrueba;

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

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCrearUsuario() throws Exception {
        when(servicioUsuario.createUser(any(User.class)))
                .thenReturn(ResponseEntity.ok(usuarioDePrueba));

        mockMvc.perform(post("/api/v1/users/creador")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDePrueba)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.email").value("juan@example.com"));
    }

    @Test
    @WithMockUser
    void testObtenerTodosUsuarios() throws Exception {
        when(servicioUsuario.getAllUsers())
                .thenReturn(ResponseEntity.ok(List.of(usuarioDePrueba)));

        mockMvc.perform(get("/api/v1/users/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    @Test
    @WithMockUser
    void testObtenerUsuarioPorId() throws Exception {
        when(servicioUsuario.getUserById(anyLong()))
                .thenReturn(ResponseEntity.ok(usuarioDePrueba));

        mockMvc.perform(get("/api/v1/users/usuarioPorId/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testEliminarUsuario() throws Exception {
        when(servicioUsuario.deleteUser(anyLong()))
                .thenReturn(ResponseEntity.ok("Usuario con ID 1 eliminado"));

        mockMvc.perform(delete("/api/v1/users/eliminar/{id}", 1L)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario con ID 1 eliminado"));
    }

    

    // @Test
    // @WithMockUser
    // void testGetDatosProtegidos() throws Exception {
    //     when(servicioUsuario.authenticatedUser(any(User.class)))
    //             .thenReturn(ResponseEntity.ok(usuarioDePrueba));

    //     mockMvc.perform(get("/api/v1/users/datos-protegidos")
    //                     .param("username", "admin")
    //                     .param("password", "1234"))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.nombreUsuario").value("jdoe"));
    // }

    @Test
    void testSimple() {
        System.out.println("Probando controlador...");
    }
}