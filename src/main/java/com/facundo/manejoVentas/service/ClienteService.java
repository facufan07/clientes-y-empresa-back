package com.facundo.manejoVentas.service;

import com.facundo.manejoVentas.domain.Cliente;
import com.facundo.manejoVentas.exception.ElementNotFoundException;
import com.facundo.manejoVentas.repository.IClienteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService{
    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void save(Cliente cliente) {
        try{
            this.clienteRepository.save(cliente);
        }catch (Exception e){
            throw new RuntimeException("No se pudo guardar el elemento");
        }
    }

    @Override
    public void delete(Long id) {
        try{
            this.clienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ElementNotFoundException("No se encontro un elemento con id: " + id);

        } catch (Exception e){
            throw new RuntimeException("No se pudo eliminar el elemento");
        }
    }

    @Override
    public Cliente findById(Long id) {
        return this.clienteRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("No se encontro un elemento con id: " + id));
    }

    @Override
    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    @Override
    public void update(Cliente cliente) {
        this.findById(cliente.getId());

        try {
            this.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar el elemento");
        }
    }
}
