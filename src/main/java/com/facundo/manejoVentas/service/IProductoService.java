package com.facundo.manejoVentas.service;

import com.facundo.manejoVentas.domain.Producto;

import java.util.List;

public interface IProductoService {
    public void save(Producto producto);

    public void delete(Long id);

    public Producto findById(Long id);

    public List<Producto> findAll();

    public void update(Producto producto);

    public List<Producto> findByLowStock();
}
