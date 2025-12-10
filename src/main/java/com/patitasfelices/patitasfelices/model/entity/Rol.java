package com.patitasfelices.patitasfelices.model.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Column(nullable = false, length = 50)
    private String nombreRol; // Ej: 'ADMIN', 'VETERINARIO', 'RECEPCIONISTA'
}