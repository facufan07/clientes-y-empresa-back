package com.facundo.manejoVentas.dto;

public class VentasDiaDTO {
    private Double montoTotal;
    private Integer cantidadTotalVentas;

    public VentasDiaDTO() {
    }

    public VentasDiaDTO(Double montoTotal, Integer cantidadTotalVentas) {
        this.montoTotal = montoTotal;
        this.cantidadTotalVentas = cantidadTotalVentas;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getCantidadTotalVentas() {
        return cantidadTotalVentas;
    }

    public void setCantidadTotalVentas(Integer cantidadTotalVentas) {
        this.cantidadTotalVentas = cantidadTotalVentas;
    }
}
