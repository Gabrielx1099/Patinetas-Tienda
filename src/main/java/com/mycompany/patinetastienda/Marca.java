package com.mycompany.patinetastienda;

/**
 * Representa una marca de producto.
 * @author gv250
 */
public class Marca {
    private int id;
    private String nombre;

    // Constructor vacío (por defecto)
    public Marca() {
    }

    // Constructor con parámetros
    public Marca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getter y Setter para ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y Setter para Nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Representación como texto
    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
