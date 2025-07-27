/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.patinetastienda;

/**
 *
 * @author gv250
 */
public class Venta {
    private int id;
    private int usuarioId;
    private String fechaVenta;
    private double total;
    private String estado;
    private String direccionEnvio;
    private String tipoEntrega;
    private double costoDelivery;
    private String telefonoEntrega;
    
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
    
    public String getFechaVenta() {
        return fechaVenta;
    }
    
    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getDireccionEnvio() {
        return direccionEnvio;
    }
    
    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
    
    public String getTipoEntrega() {
        return tipoEntrega;
    }
    
    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }
    
    public double getCostoDelivery() {
        return costoDelivery;
    }
    
    public void setCostoDelivery(double costoDelivery) {
        this.costoDelivery = costoDelivery;
    }
    
    public String getTelefonoEntrega() {
        return telefonoEntrega;
    }
    
    public void setTelefonoEntrega(String telefonoEntrega) {
        this.telefonoEntrega = telefonoEntrega;
    }
    
    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", fechaVenta='" + fechaVenta + '\'' +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                ", direccionEnvio='" + direccionEnvio + '\'' +
                ", tipoEntrega='" + tipoEntrega + '\'' +
                ", costoDelivery=" + costoDelivery +
                ", telefonoEntrega='" + telefonoEntrega + '\'' +
                '}';
    }
}