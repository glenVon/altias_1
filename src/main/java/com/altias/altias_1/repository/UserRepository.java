package com.altias.altias_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altias.altias_1.model.User;

public interface UserRepository extends JpaRepository<User, Long> { 
    User findByNombreUsuarioAndPassword(String nombreUsuario, String password);

}