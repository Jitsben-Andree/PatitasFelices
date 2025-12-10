package com.patitasfelices.patitasfelices.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class FechasUtil {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");


    public static String formatoFechaCorta(LocalDateTime fecha) {
        if (fecha == null) return "";
        return fecha.format(FORMATO_FECHA);
    }


    public static String formatoHora(LocalDateTime fecha) {
        if (fecha == null) return "";
        return fecha.format(FORMATO_HORA);
    }


    public static String formatoFechaLarga(LocalDateTime fecha) {
        if (fecha == null) return "";
        String diaSemana = fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        String mes = fecha.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        return String.format("%s, %d de %s de %d",
                diaSemana.substring(0, 1).toUpperCase() + diaSemana.substring(1),
                fecha.getDayOfMonth(),
                mes,
                fecha.getYear());
    }
}
