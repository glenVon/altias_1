//https://github.com/glenVon/mic_usuarios
package com.altias.altias_1.controller;

import com.altias.altias_1.model.User;
import com.altias.altias_1.service.ServicioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/users")//ruta base para los endpoints
//http://localhost:8080/api/v1/users (en POSTMAN)
//SELECT * FROM bd_usuario.user;(en la base de datos bd_usuario)
@Tag(name = "Controlador de Usuarios", description = "Operaciones relacionadas con la gestión de usuarios")

public class Control {

    private final ServicioUsuario ServicioUsuario;
    

    @Autowired
    public Control(ServicioUsuario servicioUsuario) {
        this.ServicioUsuario = servicioUsuario;
    }

    @Operation(summary = "Autenticar usuario", description = "Permite a un usuario iniciar sesión")
    @ApiResponse(responseCode = "200", description = "Usuario autenticado correctamente")
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    @PostMapping("/login")
    //http://localhost:8080/api/v1/users/login (en POSTMAN)
    //{"nombreUsuario":"admin","password":"1234"}
    public ResponseEntity<?> login(@RequestBody User user) {
        return ServicioUsuario.authenticatedUser(user);
    }

    @Operation(summary = "Crear usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente")
    @PostMapping("/creador")
    //http://localhost:8080/api/v1/users/creador(en POSTMAN)
    //{"nombre":"Juan","nombreUsuario":"jdoe","password":"1234","apellido_paterno":"Doe","apellido_materno":"Smith","email":"egon@von.cl","fecha_nacimiento":"1992-04-01"}
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ServicioUsuario.createUser(user);
    }

    @Operation(summary = "Listar usuarios", description = "Obtiene la lista completa de usuarios registrados")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente")
    @GetMapping("/listar")
    //http://localhost:8080/api/v1/users/listar (en POSTMAN)
    //retorna todos los usuarios
    public ResponseEntity<List<User>> getAllUsers() {
        return ServicioUsuario.getAllUsers();
    }

    @Operation(summary = "Obtener usuario por ID", description = "Recupera la información de un usuario específico")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @GetMapping("/usuarioPorId/{id}")
    //http://localhost:8080/api/v1/users/usuarioPorId/1 (en POSTMAN)
    //retorna un usuario por su id
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ServicioUsuario.getUserById(id);
    }

    //eliminar usuario
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario del sistema")
    @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @DeleteMapping("/eliminar/{id}")
    //http://localhost:8080/api/v1/users/eliminar/1 (en POSTMAN)
    //elimina un usuario por su id
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ServicioUsuario.deleteUser(id);
    }
  
    @Operation(summary = "Datos protegidos", description = "Acceso a información protegida del sistema")
    @ApiResponse(responseCode = "200", description = "Acceso concedido")
    @ApiResponse(responseCode = "401", description = "Acceso no autorizado")
    @GetMapping("/datos-protegidos")
    //http://localhost:8080/api/v1/users/datos-protegidos?nombreUsuario=admin&password=1234
    public ResponseEntity<?> getDatosProtegidos(@RequestParam String nombreUsuario, @RequestParam String password) {
        User user = new User();
        user.setNombreUsuario(nombreUsuario);
        user.setPassword(password);
        return ServicioUsuario.login(user);
    }

    
    

}