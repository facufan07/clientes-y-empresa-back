package com.facundo.manejoVentas.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private Double total;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Producto> productos;
    @ManyToOne
    private Cliente cliente;

    public Venta() {
    }

    public Venta(Long id, LocalDate fecha, Double total, List<Producto> productos, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.productos = productos;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
