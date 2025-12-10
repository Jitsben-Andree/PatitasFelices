package com.patitasfelices.patitasfelices.repository;

import com.patitasfelices.patitasfelices.model.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Ventas realizadas a un cliente
    List<Venta> findByPropietario_IdPropietario(Long idPropietario);

    // Reporte de ventas por rango de fechas (Diario, Mensual)
    List<Venta> findByFechaVentaBetween(LocalDateTime inicio, LocalDateTime fin);
}
