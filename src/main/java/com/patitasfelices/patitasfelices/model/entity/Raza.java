package com.patitasfelices.patitasfelices.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "razas")
public class Raza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRaza;

    @Column(nullable = false, length = 50)
    private String nombreRaza;

    @ManyToOne
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;
}