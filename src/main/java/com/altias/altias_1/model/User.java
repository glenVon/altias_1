//https://5w4x6xmn-8080.brs.devtunnels.ms/
package com.altias.altias_1.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Entity
@Data
@Table(name = "user")
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
    private LocalDate fecha_nacimiento;
    public void setFecha_nacimiento(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFecha_nacimiento'");
    }
    public void setFecha_nacimiento(LocalDate fecha_nacimiento2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFecha_nacimiento'");
    }

    

}
