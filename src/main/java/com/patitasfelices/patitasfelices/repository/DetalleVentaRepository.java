package com.patitasfelices.patitasfelices.repository;

import com.patitasfelices.patitasfelices.model.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByProducto_IdProducto(Long idProducto);
}
