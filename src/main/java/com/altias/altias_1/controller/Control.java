//https://github.com/glenVon/mic_usuarios
package com.altias.altias_1.controller;

import com.altias.altias_1.model.User;
import com.altias.altias_1.service.ServicioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")//ruta base para los endpoints
//http://localhost:8080/api/v1/users (en POSTMAN)
//SELECT * FROM bd_usuario.user;(en la base de datos bd_usuario)
public class Control {

    private final ServicioUsuario ServicioUsuario;
    

    @Autowired
    public Control(ServicioUsuario servicioUsuario) {
        this.ServicioUsuario = servicioUsuario;
    }

    @PostMapping("/login")
    //http://localhost:8080/api/v1/users/login (en POSTMAN)
    //{"nombreUsuario":"admin","password":"1234"}
    public ResponseEntity<?> login(@RequestBody User user) {
        return ServicioUsuario.authenticatedUser(user);
    }

    @PostMapping("/creador")
    //http://localhost:8080/api/v1/users/creador(en POSTMAN)
    //{"nombre":"Juan","nombreUsuario":"jdoe","password":"1234","apellido_paterno":"Doe","apellido_materno":"Smith","email":"egon@von.cl","fecha_nacimiento":"1992-04-01"}
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ServicioUsuario.createUser(user);
    }

    @GetMapping("/listar")
    //http://localhost:8080/api/v1/users/listar (en POSTMAN)
    //retorna todos los usuarios
    public ResponseEntity<List<User>> getAllUsers() {
        return ServicioUsuario.getAllUsers();
    }
    
    @GetMapping("/usuarioPorId/{id}")
    //http://localhost:8080/api/v1/users/usuarioPorId/1 (en POSTMAN)
    //retorna un usuario por su id
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        return ServicioUsuario.getUserById(id);
    }

    //eliminar usuario
    @DeleteMapping("/eliminar/{id}")
    //http://localhost:8080/api/v1/users/eliminar/1 (en POSTMAN)
    //elimina un usuario por su id
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ServicioUsuario.deleteUser(id);
    }
  
    @GetMapping("/datos-protegidos")
    //http://localhost:8080/api/v1/users/datos-protegidos?nombreUsuario=admin&password=1234
    public ResponseEntity<?> getDatosProtegidos(@RequestParam String nombreUsuario, @RequestParam String password) {
        User user = new User();
        user.setNombreUsuario(nombreUsuario);
        user.setPassword(password);
        return ServicioUsuario.login(user);
    }

}