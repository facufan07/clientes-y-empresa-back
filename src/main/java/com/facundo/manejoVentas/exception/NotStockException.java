package com.facundo.manejoVentas.exception;

public class NotStockException extends RuntimeException{
    public NotStockException(String message) {
        super(message);
    }
}
