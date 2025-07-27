/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.patinetastienda;

/**
 *
 * @author gv250
 */
public class Carrito {
    private int id;
    private int usuarioId;
    private int productoId;
    private int cantidad;
    private String fechaAgregado;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public int getProductoId() {
        return productoId;
    }
    
    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getFechaAgregado() {
        return fechaAgregado;
    }
    
    public void setFechaAgregado(String fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }
    
    @Override
    public String toString() {
        return "Carrito{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", productoId=" + productoId +
                ", cantidad=" + cantidad +
                ", fechaAgregado='" + fechaAgregado + '\'' +
                '}';
    }
}
