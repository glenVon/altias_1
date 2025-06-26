//https://5w4x6xmn-8080.brs.devtunnels.ms/
package com.altias.altias_1.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GenerationType;
import io.swagger.v3.oas.annotations.media.Schema;


@Entity
@Data
@Table(name = "user")
@Schema(description = "Modelo que representa un usuario del sistema")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String nombreUsuario;
    private String password;
    private String apellido_paterno;
    private String apellido_materno;
    private String email;
    //private LocalDate fecha_nacimiento;
    

    

}
