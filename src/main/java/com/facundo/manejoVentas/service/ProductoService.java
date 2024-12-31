package com.facundo.manejoVentas.service;

import com.facundo.manejoVentas.domain.Producto;
import com.facundo.manejoVentas.exception.ElementNotFoundException;
import com.facundo.manejoVentas.repository.IProductoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService{
    private final IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void save(Producto producto) {
        try{
            this.productoRepository.save(producto);
        }catch (Exception e){
            throw new RuntimeException("No se pudo guardar el elemento");
        }
    }

    @Override
    public void delete(Long id) {
        try{
            this.productoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ElementNotFoundException("No se encontro un elemento con id: " + id);

        } catch (Exception e){
            throw new RuntimeException("No se pudo eliminar el elemento");
        }
    }

    @Override
    public Producto findById(Long id) {
        return this.productoRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("No se encontro un elemento con id: " + id));
    }

    @Override
    public List<Producto> findAll() {
        return this.productoRepository.findAll();
    }

    @Override
    public void update(Producto producto) {
        this.findById(producto.getId());
        try {
            this.save(producto);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar el elemento");
        }

    }

    @Override
    public List<Producto> findByLowStock() {
        List<Producto> productos = this.productoRepository.findAll();

        return productos.stream()
                .filter(producto -> producto.getStock() < 5)
                .collect(Collectors.toList());
    }
}
