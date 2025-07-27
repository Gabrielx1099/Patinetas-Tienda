package com.mycompany.patinetastienda;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String imagenUrl;
    private int categoriaId;
    private int subcategoriaId;
    private int marcaId;
    private String fechaCreacion;

    // Campos adicionales para mostrar nombres (opcional pero útil en JSP)
    private String categoriaNombre;
    private String subcategoriaNombre;
    private String marcaNombre;

    public Producto() {
    }

    public Producto(int id, String nombre, String descripcion, double precio, int stock, String imagenUrl,
                    int categoriaId, int subcategoriaId, int marcaId, String fechaCreacion,
                    String categoriaNombre, String subcategoriaNombre, String marcaNombre) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagenUrl = imagenUrl;
        this.categoriaId = categoriaId;
        this.subcategoriaId = subcategoriaId;
        this.marcaId = marcaId;
        this.fechaCreacion = fechaCreacion;
        this.categoriaNombre = categoriaNombre;
        this.subcategoriaNombre = subcategoriaNombre;
        this.marcaNombre = marcaNombre;
    }

    // Getters y setters principales
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

    public int getSubcategoriaId() { return subcategoriaId; }
    public void setSubcategoriaId(int subcategoriaId) { this.subcategoriaId = subcategoriaId; }

    public int getMarcaId() { return marcaId; }
    public void setMarcaId(int marcaId) { this.marcaId = marcaId; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    // Getters y setters adicionales para mostrar nombres
    public String getCategoriaNombre() { return categoriaNombre; }
    public void setCategoriaNombre(String categoriaNombre) { this.categoriaNombre = categoriaNombre; }

    public String getSubcategoriaNombre() { return subcategoriaNombre; }
    public void setSubcategoriaNombre(String subcategoriaNombre) { this.subcategoriaNombre = subcategoriaNombre; }

    public String getMarcaNombre() { return marcaNombre; }
    public void setMarcaNombre(String marcaNombre) { this.marcaNombre = marcaNombre; }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", imagenUrl='" + imagenUrl + '\'' +
                ", categoriaId=" + categoriaId +
                ", subcategoriaId=" + subcategoriaId +
                ", marcaId=" + marcaId +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", categoriaNombre='" + categoriaNombre + '\'' +
                ", subcategoriaNombre='" + subcategoriaNombre + '\'' +
                ", marcaNombre='" + marcaNombre + '\'' +
                '}';
    }
}
