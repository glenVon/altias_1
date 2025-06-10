package com.altias.altias_1.service;

import com.altias.altias_1.model.User;
//import com.altias.mic_autenticacion.model.usuario;
import com.altias.altias_1.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuario {

    private final UserRepository userRepository;
    

    @Autowired
    public ServicioUsuario(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public ResponseEntity<?> login(User user) {
        User authenticatedUser = userRepository.findByNombreUsuarioAndPassword(user.getNombreUsuario(), user.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autorizado");
        }
    }

    public ResponseEntity<User> createUser(User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
        
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<User> getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            //retorna solo email y nombre
            User userResponse = new User();

            userResponse.setNombre(user.getNombre());
            userResponse.setNombreUsuario(user.getNombreUsuario());
            userResponse.setApellido_paterno(user.getApellido_paterno());
            userResponse.setApellido_materno(user.getApellido_materno());
            userResponse.setEmail(user.getEmail());
            userResponse.setFecha_nacimiento(user.getFecha_nacimiento());
            userResponse.setId(user.getId());
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
    if (userRepository.existsById(id)) {
        userRepository.deleteById(id);  // Elimina directamente por ID
        return ResponseEntity.ok("Usuario con ID " + id + " eliminado");
    } else {
        return ResponseEntity.status(404).body("Usuario no encontrado");
    }
    }

    public ResponseEntity<?> authenticatedUser(User user) {
        // metodo autenticar usuario
        User authenticatedUser = userRepository.findByNombreUsuarioAndPassword(user.getNombreUsuario(), user.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autorizado");
        }
    }




}
