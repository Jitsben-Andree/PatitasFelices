package com.patitasfelices.patitasfelices.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;

    @Column(nullable = false, length = 100)
    private String nombre;

    private LocalDate fechaNacimiento;


    @ManyToOne
    @JoinColumn(name = "id_propietario", nullable = false)
    private Propietario propietario;


    @ManyToOne
    @JoinColumn(name = "id_raza", nullable = false)
    private Raza raza;
}