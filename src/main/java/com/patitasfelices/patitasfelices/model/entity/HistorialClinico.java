package com.patitasfelices.patitasfelices.model.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historial_clinico")
public class HistorialClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;

    @Column(nullable = false)
    private LocalDateTime fechaConsulta;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String diagnostico;

    @Column(columnDefinition = "TEXT")
    private String observaciones;


    @ManyToOne
    @JoinColumn(name = "id_mascota", nullable = false)
    private Mascota mascota;


    @ManyToOne
    @JoinColumn(name = "id_veterinario", nullable = false)
    private Usuario veterinario;
}