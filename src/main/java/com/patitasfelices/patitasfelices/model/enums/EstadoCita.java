package com.patitasfelices.patitasfelices.model.enums;

public enum EstadoCita {
    PENDIENTE("Pendiente"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada"),
    ATENDIDA("Atendida"),
    NO_ASISTIO("No asistió");

    private final String nombre;

    EstadoCita(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}