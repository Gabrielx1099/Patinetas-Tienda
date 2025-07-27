/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.patinetastienda;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gv250
 */


public class ProductoDAO {
    
    // Obtener producto por ID
    public Producto getProductoById(int id) throws SQLException {
        Producto producto = null;
        String sql = "SELECT * FROM productos WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagenUrl(rs.getString("imagen_url"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                producto.setMarcaId(rs.getInt("marca_id"));
                producto.setFechaCreacion(rs.getString("fecha_creacion"));
            }
        }
        return producto;
    }
    
    // Obtener producto por nombre
    public Producto getProductoByNombre(String nombre) throws SQLException {
        Producto producto = null;
        String sql = "SELECT * FROM productos WHERE nombre = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagenUrl(rs.getString("imagen_url"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                producto.setMarcaId(rs.getInt("marca_id"));
                producto.setFechaCreacion(rs.getString("fecha_creacion"));
            }
        }
        return producto;
    }

    // Obtener todos los productos
    // Obtener todos los productos con nombres de categoría, subcategoría y marca
public List<Producto> getAllProductos() throws SQLException {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT p.*, " +
                 "c.nombre AS categoria_nombre, " +
                 "s.nombre AS subcategoria_nombre, " +
                 "m.nombre AS marca_nombre " +
                 "FROM productos p " +
                 "JOIN categorias c ON p.categoria_id = c.id " +
                 "JOIN subcategorias s ON p.subcategoria_id = s.id " +
                 "JOIN marcas m ON p.marca_id = m.id";

    try (Connection conn = DBUtil.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Producto producto = new Producto();
            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setPrecio(rs.getDouble("precio"));
            producto.setStock(rs.getInt("stock"));
            producto.setImagenUrl(rs.getString("imagen_url"));
            producto.setCategoriaId(rs.getInt("categoria_id"));
            producto.setSubcategoriaId(rs.getInt("subcategoria_id"));
            producto.setMarcaId(rs.getInt("marca_id"));
            producto.setFechaCreacion(rs.getString("fecha_creacion"));

            // Nuevos campos: nombres asociados
            producto.setCategoriaNombre(rs.getString("categoria_nombre"));
            producto.setSubcategoriaNombre(rs.getString("subcategoria_nombre"));
            producto.setMarcaNombre(rs.getString("marca_nombre"));

            productos.add(producto);
        }
    }
    return productos;
}

    
    // Obtener productos por categoría
    public List<Producto> getProductosByCategoria(int categoriaId) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE categoria_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoriaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagenUrl(rs.getString("imagen_url"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                producto.setMarcaId(rs.getInt("marca_id"));
                producto.setFechaCreacion(rs.getString("fecha_creacion"));
                productos.add(producto);
            }
        }
        return productos;
    }
    
    // Obtener productos por marca
    public List<Producto> getProductosByMarca(int marcaId) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE marca_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, marcaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagenUrl(rs.getString("imagen_url"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                producto.setMarcaId(rs.getInt("marca_id"));
                producto.setFechaCreacion(rs.getString("fecha_creacion"));
                productos.add(producto);
            }
        }
        return productos;
    }
    
    // Obtener productos con stock disponible
    public List<Producto> getProductosConStock() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE stock > 0";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagenUrl(rs.getString("imagen_url"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                producto.setMarcaId(rs.getInt("marca_id"));
                producto.setFechaCreacion(rs.getString("fecha_creacion"));
                productos.add(producto);
            }
        }
        return productos;
    }
    
    // Insertar producto
    public int insertProducto(Producto producto) throws SQLException {
    String sql = "INSERT INTO productos (nombre, descripcion, precio, stock, imagen_url, categoria_id, subcategoria_id, marca_id, fecha_creacion) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";

    System.out.println("DEBUG: Intentando insertar producto con nombre: " + producto.getNombre());
    System.out.println("DEBUG: Precio: " + producto.getPrecio());
    System.out.println("DEBUG: Stock: " + producto.getStock());
    System.out.println("DEBUG: Imagen URL: " + producto.getImagenUrl());
    System.out.println("DEBUG: Categoría ID: " + producto.getCategoriaId());
    System.out.println("DEBUG: Subcategoría ID: " + producto.getSubcategoriaId());
    System.out.println("DEBUG: Marca ID: " + producto.getMarcaId());

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, producto.getNombre());
        stmt.setString(2, producto.getDescripcion());
        stmt.setDouble(3, producto.getPrecio());
        stmt.setInt(4, producto.getStock());
        stmt.setString(5, producto.getImagenUrl());
        stmt.setInt(6, producto.getCategoriaId());
        stmt.setInt(7, producto.getSubcategoriaId()); // ✅ Aquí se incluye subcategoría
        stmt.setInt(8, producto.getMarcaId());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("DEBUG: ID generado del producto: " + generatedId);
                    return generatedId;
                }
            }
        }
        System.out.println("DEBUG: No se pudo insertar el producto.");
        return -1;
    }
}


    // Actualizar producto
    public boolean updateProducto(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, stock = ?, imagen_url = ?, categoria_id = ?, marca_id = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());
            stmt.setString(5, producto.getImagenUrl());
            stmt.setInt(6, producto.getCategoriaId());
            stmt.setInt(7, producto.getMarcaId());
            stmt.setInt(8, producto.getId());
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Actualizar solo el stock de un producto
    public boolean updateStock(int id, int nuevoStock) throws SQLException {
        String sql = "UPDATE productos SET stock = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, nuevoStock);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Reducir stock de un producto (útil para ventas)
    public boolean reducirStock(int id, int cantidad) throws SQLException {
        String sql = "UPDATE productos SET stock = stock - ? WHERE id = ? AND stock >= ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cantidad);
            stmt.setInt(2, id);
            stmt.setInt(3, cantidad);
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar producto
    public boolean deleteProducto(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Registrar producto con validación
    public void registrarProducto(Producto producto) throws Exception {
        // Verificar si el producto ya existe
        if (getProductoByNombre(producto.getNombre()) != null) {
            throw new Exception("Ya existe un producto con ese nombre");
        }
        
        // Validar precio
        if (producto.getPrecio() <= 0) {
            throw new Exception("El precio debe ser mayor a 0");
        }
        
        // Validar stock
        if (producto.getStock() < 0) {
            throw new Exception("El stock no puede ser negativo");
        }

        System.out.println("DEBUG: registrarProducto - Nombre del producto: " + producto.getNombre());
        System.out.println("DEBUG: registrarProducto - Precio del producto: " + producto.getPrecio());
        System.out.println("DEBUG: registrarProducto - Stock del producto: " + producto.getStock());

        String sql = "INSERT INTO productos (nombre, descripcion, precio, stock, imagen_url, categoria_id, marca_id, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());
            stmt.setString(5, producto.getImagenUrl());
            stmt.setInt(6, producto.getCategoriaId());
            stmt.setInt(7, producto.getMarcaId());
            
            System.out.println("DEBUG: registrarProducto - Parámetros preparados:");
            System.out.println("DEBUG: - Nombre: " + producto.getNombre());
            System.out.println("DEBUG: - Descripción: " + producto.getDescripcion());
            System.out.println("DEBUG: - Precio: " + producto.getPrecio());
            System.out.println("DEBUG: - Stock: " + producto.getStock());
            System.out.println("DEBUG: - Categoría ID: " + producto.getCategoriaId());
            System.out.println("DEBUG: - Marca ID: " + producto.getMarcaId());
            
            stmt.executeUpdate();
        }
    }
    
    // Buscar productos por nombre (búsqueda parcial)
    public List<Producto> buscarProductosPorNombre(String nombre) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE nombre LIKE ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagenUrl(rs.getString("imagen_url"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                producto.setMarcaId(rs.getInt("marca_id"));
                producto.setFechaCreacion(rs.getString("fecha_creacion"));
                productos.add(producto);
            }
        }
        return productos;
    }
    
    // Obtener productos por rango de precios
    public List<Producto> getProductosPorRangoPrecio(double precioMin, double precioMax) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE precio BETWEEN ? AND ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, precioMin);
            stmt.setDouble(2, precioMax);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagenUrl(rs.getString("imagen_url"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                producto.setMarcaId(rs.getInt("marca_id"));
                producto.setFechaCreacion(rs.getString("fecha_creacion"));
                productos.add(producto);
            }
        }
        return productos;
    }

    // Contar productos
    public int countProductos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM productos";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
    
    // Contar productos por categoría
    public int countProductosByCategoria(int categoriaId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM productos WHERE categoria_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoriaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
    
    // Contar productos por marca
    public int countProductosByMarca(int marcaId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM productos WHERE marca_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, marcaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
    
    // Contar productos con stock bajo (menos de cierta cantidad)
    public int countProductosStockBajo(int stockMinimo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM productos WHERE stock < ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stockMinimo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
    public List<Producto> getProductosByCategoriaId(int categoriaId) {
    List<Producto> lista = new ArrayList<>();
    String sql = "SELECT p.*, " +
                 "s.nombre AS subcategoria_nombre, " +
                 "m.nombre AS marca_nombre " +
                 "FROM productos p " +
                 "LEFT JOIN subcategorias s ON p.subcategoria_id = s.id " +
                 "LEFT JOIN marcas m ON p.marca_id = m.id " +
                 "WHERE p.categoria_id = ?";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, categoriaId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Producto p = new Producto();
            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setPrecio(rs.getDouble("precio"));
            p.setStock(rs.getInt("stock"));
            p.setImagenUrl(rs.getString("imagen_url"));
            p.setCategoriaId(rs.getInt("categoria_id"));
            p.setSubcategoriaId(rs.getInt("subcategoria_id"));
            p.setMarcaId(rs.getInt("marca_id"));

            // nombres reales
            p.setSubcategoriaNombre(rs.getString("subcategoria_nombre"));
            p.setMarcaNombre(rs.getString("marca_nombre"));

            lista.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}



    
}

