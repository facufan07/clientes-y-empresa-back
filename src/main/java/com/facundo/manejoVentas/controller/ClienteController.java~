package com.facundo.manejoVentas.controller;

import com.facundo.manejoVentas.domain.Cliente;
import com.facundo.manejoVentas.exception.ElementNotFoundException;
import com.facundo.manejoVentas.service.IClienteService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {
    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> clientes = this.clienteService.findAll();

        if(clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        try{
            Cliente cliente = this.clienteService.findById(id);
            return ResponseEntity.ok(cliente);
        }catch (ElementNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> save(@RequestBody Cliente cliente){
        try{
            this.clienteService.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            this.clienteService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> update(@RequestBody Cliente cliente){
        try {
            this.clienteService.update(cliente);
            return ResponseEntity.ok().build();
        }catch (ElementNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }
}
