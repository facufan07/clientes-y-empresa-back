package com.facundo.manejoVentas.service;

import com.facundo.manejoVentas.domain.Producto;
import com.facundo.manejoVentas.domain.Venta;
import com.facundo.manejoVentas.dto.MayorVentaDTO;
import com.facundo.manejoVentas.dto.VentasDiaDTO;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    public void save(Venta venta);

    public void delete(Long id);

    public Venta findById(Long id);

    public List<Venta> findAll();

    public void update(Venta venta);

    public List<Producto> findProductos(Long id);

    public VentasDiaDTO ventasDia(LocalDate fecha);

    public MayorVentaDTO findMayorVenta();
}
