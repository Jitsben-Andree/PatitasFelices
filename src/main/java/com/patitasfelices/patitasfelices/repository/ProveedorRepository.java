package com.patitasfelices.patitasfelices.repository;

import com.patitasfelices.patitasfelices.model.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    boolean existsByNombreProveedor(String nombreProveedor);
}
