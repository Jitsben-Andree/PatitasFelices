package com.patitasfelices.patitasfelices.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "especies")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecie;

    @Column(nullable = false, length = 50)
    private String nombreEspecie;
}