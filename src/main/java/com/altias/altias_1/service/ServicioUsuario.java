// package com.altias.altias_1.service;

// //import com.altias.altias_1.controller.User;
// import com.altias.altias_1.model.User;
// //import com.altias.mic_autenticacion.model.usuario;
// import com.altias.altias_1.repository.UserRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class ServicioUsuario {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;

//     @Autowired
//     public ServicioUsuario(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }
    
//     public ResponseEntity<?> login(User user) {
//         User authenticatedUser = userRepository.findByNombreUsuarioAndPassword(user.getNombreUsuario(), user.getPassword());
//         if (authenticatedUser != null) {
//             return ResponseEntity.ok(authenticatedUser);
//         } else {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autorizado");
//         }
//     }

//     public ResponseEntity<User> createUser(User user) {
//         try {
//             // Codifica la contraseña si es necesario
//             if (user.getPassword() != null) {
//                 user.setPassword(passwordEncoder.encode(user.getPassword()));
//             }
            
//             User savedUser = userRepository.save(user);
//             return ResponseEntity.ok(savedUser);
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//         }
//     }

//     public ResponseEntity<List<User>> getAllUsers() {
//         List<User> users = userRepository.findAll();
//         return ResponseEntity.ok(users);
//     }

//     public ResponseEntity<User> getUserById(Long id) {
//         User user = userRepository.findById(id).orElse(null);
//         if (user != null) {
//             //retorna solo email y nombre
//             User userResponse = new User();

//             userResponse.setNombre(user.getNombre());
//             userResponse.setNombreUsuario(user.getNombreUsuario());
//             userResponse.setApellido_paterno(user.getApellido_paterno());
//             userResponse.setApellido_materno(user.getApellido_materno());
//             userResponse.setEmail(user.getEmail());
//             //userResponse.setFecha_nacimiento(user.getFecha_nacimiento());
//             userResponse.setId(user.getId());
//             return ResponseEntity.ok(userResponse);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     public ResponseEntity<String> deleteUser(Long id) {
//     if (userRepository.existsById(id)) {
//         userRepository.deleteById(id);  // Elimina directamente por ID
//         return ResponseEntity.ok("Usuario con ID " + id + " eliminado");
//     } else {
//         return ResponseEntity.status(404).body("Usuario no encontrado");
//     }
//     }



//swa
// {
//   "id": 7,
//   "nombre": "egon",
//   "nombreUsuario": "von",
//   "password": "1234",
//   "apellido_paterno": "von",
//   "apellido_materno": "glen",
//   "email": "glen.von@.com",
//   "fecha_nacimiento": "2025-06-27"
// }7

package com.altias.altias_1.service;

import com.altias.altias_1.model.User;
import com.altias.altias_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuario {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ServicioUsuario(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    // public ResponseEntity<?> login(User user) {
    //     User usuarioEnDB = userRepository.findByNombreUsuario(user.getNombreUsuario());
    //     if (usuarioEnDB != null && passwordEncoder.matches(user.getPassword(), usuarioEnDB.getPassword())) {
    //         return ResponseEntity.ok(usuarioEnDB);
    //     } else {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autorizado");
    //     }
    // }

    public ResponseEntity<User> createUser(User user) {
        try {
            // Codificar la contraseña antes de guardar
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User usuarioGuardado = userRepository.save(user);
            return ResponseEntity.ok(usuarioGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> usuarios = userRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    public ResponseEntity<User> getUserById(Long id) {
        User usuario = userRepository.findById(id).orElse(null);
        if (usuario != null) {
            // Retornar solo campos seleccionados
            User respuestaUsuario = new User();
            respuestaUsuario.setNombre(usuario.getNombre());
            respuestaUsuario.setNombreUsuario(usuario.getNombreUsuario());
            respuestaUsuario.setApellido_paterno(usuario.getApellido_paterno());
            respuestaUsuario.setApellido_materno(usuario.getApellido_materno());
            respuestaUsuario.setEmail(usuario.getEmail());
            respuestaUsuario.setId(usuario.getId());
            return ResponseEntity.ok(respuestaUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("Usuario con ID " + id + " eliminado");
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }

    public ResponseEntity<?> login(User user) {
        // metodo autenticar usuario
        User authenticatedUser = userRepository.findByNombreUsuarioAndPassword(user.getNombreUsuario(), user.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autorizado");
        }
    }
}
