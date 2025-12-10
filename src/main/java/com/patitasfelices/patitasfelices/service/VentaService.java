package com.patitasfelices.patitasfelices.service;

import com.patitasfelices.patitasfelices.model.entity.Venta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VentaService {

    Venta registrarVenta(Venta venta) throws Exception;
    List<Venta> listarVentas();
    Optional<Venta> buscarPorId(Long id);
    List<Venta> reporteVentasPorFecha(LocalDateTime inicio, LocalDateTime fin);
}
