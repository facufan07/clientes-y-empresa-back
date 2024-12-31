package com.facundo.manejoVentas.controller;

import com.facundo.manejoVentas.domain.Producto;
import com.facundo.manejoVentas.exception.ElementNotFoundException;
import com.facundo.manejoVentas.service.IProductoService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {
    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> findAll(){
        List<Producto> productos = this.productoService.findAll();

        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        try{
            Producto producto = this.productoService.findById(id);
            return ResponseEntity.ok(producto);
        }catch (ElementNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/falta_stock")
    public ResponseEntity<List<Producto>> findByLowStock(){
        List<Producto> productos = this.productoService.findByLowStock();
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> save(@RequestBody Producto producto){
        try{
            this.productoService.save(producto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            this.productoService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> update(@RequestBody Producto producto){
        try {
            this.productoService.update(producto);
            return ResponseEntity.ok().build();
        }catch (ElementNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }
}
