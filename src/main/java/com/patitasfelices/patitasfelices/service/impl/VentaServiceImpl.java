package com.patitasfelices.patitasfelices.service.impl;

import com.patitasfelices.patitasfelices.model.entity.DetalleVenta;
import com.patitasfelices.patitasfelices.model.entity.Producto;
import com.patitasfelices.patitasfelices.model.entity.Venta;
import com.patitasfelices.patitasfelices.repository.ProductoRepository;
import com.patitasfelices.patitasfelices.repository.VentaRepository;
import com.patitasfelices.patitasfelices.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public Venta registrarVenta(Venta venta) throws Exception {
        //  Asignar fecha actual
        venta.setFechaVenta(LocalDateTime.now());

        BigDecimal totalCalculado = BigDecimal.ZERO;

        //  Procesar cada detalle de la venta
        for (DetalleVenta detalle : venta.getDetalles()) {

            // Lógica para Productos Físicos
            if (detalle.getProducto() != null) {
                Producto prodDB = productoRepository.findById(detalle.getProducto().getIdProducto())
                        .orElseThrow(() -> new Exception("Producto no encontrado"));

                // Validar Stock
                if (prodDB.getStock() < detalle.getCantidad()) {
                    throw new Exception("Stock insuficiente para: " + prodDB.getNombreProducto());
                }

                // Descontar Stock
                prodDB.setStock(prodDB.getStock() - detalle.getCantidad());
                productoRepository.save(prodDB);

                // Asignar precio unitario actual del producto
                detalle.setPrecioUnitario(prodDB.getPrecio());
            }
            // Lógica para Servicios
            else if (detalle.getServicio() != null) {
                // Aquí podrías buscar el precio del servicio si fuera necesario
                // detalle.setPrecioUnitario(servicioDB.getPrecio());
            }


            BigDecimal subTotal = detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidad()));
            totalCalculado = totalCalculado.add(subTotal);


            detalle.setVenta(venta);
        }

        venta.setTotal(totalCalculado);
        return ventaRepository.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> reporteVentasPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        return ventaRepository.findByFechaVentaBetween(inicio, fin);
    }
}
