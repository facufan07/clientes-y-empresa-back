package com.facundo.manejoVentas.service;

import com.facundo.manejoVentas.domain.Cliente;

import java.util.List;

public interface IClienteService {
    public void save(Cliente cliente);

    public void delete(Long id);

    public Cliente findById(Long id);

    public List<Cliente> findAll();

    public void update(Cliente cliente);
}
