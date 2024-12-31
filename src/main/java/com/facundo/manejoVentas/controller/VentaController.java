package com.facundo.manejoVentas.controller;

import com.facundo.manejoVentas.domain.Producto;
import com.facundo.manejoVentas.domain.Venta;
import com.facundo.manejoVentas.dto.MayorVentaDTO;
import com.facundo.manejoVentas.dto.VentasDiaDTO;
import com.facundo.manejoVentas.exception.ElementNotFoundException;
import com.facundo.manejoVentas.service.IVentaService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "http://localhost:3000")
public class VentaController {
    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> findAll(){
        List<Venta> ventas = this.ventaService.findAll();

        if(ventas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> findById(@PathVariable Long id){
        try{
            Venta venta = this.ventaService.findById(id);
            return ResponseEntity.ok(venta);
        }catch (ElementNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<List<Producto>> findProductos(@PathVariable Long id){
        try {
            List<Producto> productos = this.ventaService.findProductos(id);
            return ResponseEntity.ok(productos);
        }catch (ElementNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/total/{fecha}")
    public ResponseEntity<VentasDiaDTO> totalVentasFecha(@PathVariable LocalDate fecha){
        try{
            VentasDiaDTO ventasDiaDTO = this.ventaService.ventasDia(fecha);

            if(ventasDiaDTO.getCantidadTotalVentas() == 0){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND).body(ventasDiaDTO);
            }

            return ResponseEntity.ok(ventasDiaDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/mayor_venta")
    public ResponseEntity<MayorVentaDTO> findMayorVenta(){
        try {
            MayorVentaDTO mayorVentaDTO = this.ventaService.findMayorVenta();

            return ResponseEntity.ok(mayorVentaDTO);
        }catch (ElementNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> save(@RequestBody Venta venta){
        try{
            this.ventaService.save(venta);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            this.ventaService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> update(@RequestBody Venta venta){
        try {
            this.ventaService.update(venta);
            return ResponseEntity.ok().build();
        }catch (ElementNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }
}
