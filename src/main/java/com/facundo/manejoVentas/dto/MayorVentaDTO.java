package com.facundo.manejoVentas.dto;
import com.facundo.manejoVentas.domain.Producto;

import java.util.List;

public class MayorVentaDTO {
    private Long id;
    private Double total;
    private List<Producto> productos;
    private String clienteNombre;
    private String clienteApellido;

    public MayorVentaDTO() {
    }

    public MayorVentaDTO(Long id, Double total, List<Producto> productos, String clienteNombre, String clienteApellido) {
        this.id = id;
        this.total = total;
        this.productos = productos;
        this.clienteNombre = clienteNombre;
        this.clienteApellido = clienteApellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteApellido() {
        return clienteApellido;
    }

    public void setClienteApellido(String clienteApellido) {
        this.clienteApellido = clienteApellido;
    }
}
