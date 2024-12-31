package com.facundo.manejoVentas.service;

import com.facundo.manejoVentas.domain.Producto;
import com.facundo.manejoVentas.domain.Venta;
import com.facundo.manejoVentas.dto.MayorVentaDTO;
import com.facundo.manejoVentas.dto.VentasDiaDTO;
import com.facundo.manejoVentas.exception.ElementNotFoundException;
import com.facundo.manejoVentas.exception.NotStockException;
import com.facundo.manejoVentas.repository.IProductoRepository;
import com.facundo.manejoVentas.repository.IVentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService implements IVentaService{
    private final IVentaRepository ventaRepository;
    private final IProductoRepository productoRepository;

    public VentaService(IVentaRepository ventaRepository,
                        IProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional
    public void save(Venta venta) {
        try{
            double total = 0;
            for(Producto p : venta.getProductos()){
                Producto pBD = this.productoRepository.findById(p.getId())
                        .orElseThrow(() -> new ElementNotFoundException("Producto no encontrado: " + p.getNombre()));

                if(pBD.getStock() < 1){
                    throw new NotStockException("El producto: " + p.getNombre()
                            + "no tiene stock.");
                }

                pBD.setStock(pBD.getStock() - 1);
                total += pBD.getPrecio();
                this.productoRepository.save(pBD);
            }
            venta.setTotal(total);
            venta.setFecha(LocalDate.now());
            this.ventaRepository.save(venta);

        }catch (NotStockException e){
            throw new NotStockException("Error en stock: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("No se pudo procesar la venta: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try{
            this.ventaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ElementNotFoundException("No se encontro un elemento con id: " + id);

        } catch (Exception e){
            throw new RuntimeException("No se pudo eliminar el elemento");
        }
    }

    @Override
    public Venta findById(Long id) {
        return this.ventaRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("No se encontro un elemento con id: " + id));
    }

    @Override
    public List<Venta> findAll() {
        return this.ventaRepository.findAll();
    }

    @Override
    public void update(Venta venta) {
        this.findById(venta.getId());

        try {
            this.ventaRepository.save(venta);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar el elemento");
        }
    }

    @Override
    public List<Producto> findProductos(Long id) {
        Venta venta = this.findById(id);
        return venta.getProductos();
    }

    @Override
    public VentasDiaDTO ventasDia(LocalDate fecha) {
        List<Venta> ventas = this.findAll();

        List<Venta> ventasDia = ventas.stream()
                .filter(venta -> venta.getFecha().equals(fecha))
                .toList();

        if(ventasDia.isEmpty()){
            return new VentasDiaDTO(0.0, 0);
        }

        double montoTotal = 0;

        for (Venta v : ventasDia){
            montoTotal += v.getTotal();
        }

        return  new VentasDiaDTO(montoTotal, ventasDia.size());
    }

    @Override
    public MayorVentaDTO findMayorVenta() {
        List<Venta> ventas = this.findAll();

        Venta vMayor = ventas.stream()
                .max((v1,v2) -> Double.compare(v1.getTotal(), v2.getTotal()))
                .orElseThrow(() -> new ElementNotFoundException("No se encontro la venta mayor"));

        return new MayorVentaDTO(vMayor.getId(), vMayor.getTotal(),
                vMayor.getProductos(), vMayor.getCliente().getNombre(),
                vMayor.getCliente().getApellido());
    }
}
