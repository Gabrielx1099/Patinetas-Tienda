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

public class CarritoDAO {

    // Obtener un carrito por ID
    public Carrito getCarritoById(int id) throws SQLException {
        Carrito carrito = null;
        String sql = "SELECT * FROM carrito WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                carrito = new Carrito();
                carrito.setId(rs.getInt("id"));
                carrito.setUsuarioId(rs.getInt("usuario_id"));
                carrito.setProductoId(rs.getInt("producto_id"));
                carrito.setCantidad(rs.getInt("cantidad"));
                carrito.setFechaAgregado(rs.getString("fecha_agregado"));
            }
        }
        return carrito;
    }

    // Obtener todos los registros del carrito
    public List<Carrito> getAllCarritos() throws SQLException {
        List<Carrito> carritos = new ArrayList<>();
        String sql = "SELECT * FROM carrito";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Carrito carrito = new Carrito();
                carrito.setId(rs.getInt("id"));
                carrito.setUsuarioId(rs.getInt("usuario_id"));
                carrito.setProductoId(rs.getInt("producto_id"));
                carrito.setCantidad(rs.getInt("cantidad"));
                carrito.setFechaAgregado(rs.getString("fecha_agregado"));
                carritos.add(carrito);
            }
        }
        return carritos;
    }

    // Insertar un nuevo registro en el carrito
    public int insertCarrito(Carrito carrito) throws SQLException {
        String sql = "INSERT INTO carrito (usuario_id, producto_id, cantidad, fecha_agregado) VALUES (?, ?, ?, NOW())";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, carrito.getUsuarioId());
            stmt.setInt(2, carrito.getProductoId());
            stmt.setInt(3, carrito.getCantidad());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            return -1;
        }
    }

    // Actualizar un registro del carrito
    public boolean updateCarrito(Carrito carrito) throws SQLException {
        String sql = "UPDATE carrito SET usuario_id = ?, producto_id = ?, cantidad = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carrito.getUsuarioId());
            stmt.setInt(2, carrito.getProductoId());
            stmt.setInt(3, carrito.getCantidad());
            stmt.setInt(4, carrito.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar un registro del carrito
    public boolean deleteCarrito(int id) throws SQLException {
        String sql = "DELETE FROM carrito WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Obtener carritos por ID de usuario
    public List<Carrito> getCarritosByUsuarioId(int usuarioId) throws SQLException {
        List<Carrito> carritos = new ArrayList<>();
        String sql = "SELECT * FROM carrito WHERE usuario_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Carrito carrito = new Carrito();
                carrito.setId(rs.getInt("id"));
                carrito.setUsuarioId(rs.getInt("usuario_id"));
                carrito.setProductoId(rs.getInt("producto_id"));
                carrito.setCantidad(rs.getInt("cantidad"));
                carrito.setFechaAgregado(rs.getString("fecha_agregado"));
                carritos.add(carrito);
            }
        }
        return carritos;
    }

    // Contar los registros del carrito
    public int countCarritos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM carrito";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
    // Vaciar carrito de un usuario después de la compra
public void vaciarCarritoPorUsuario(int usuarioId) throws SQLException {
    String sql = "DELETE FROM carrito WHERE usuario_id = ?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, usuarioId);
        stmt.executeUpdate();
    }
}
public Carrito getCarritoByUsuarioAndProducto(int usuarioId, int productoId) throws SQLException {
    String sql = "SELECT * FROM carrito WHERE usuario_id = ? AND producto_id = ?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, usuarioId);
        stmt.setInt(2, productoId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Carrito c = new Carrito();
            c.setId(rs.getInt("id"));
            c.setUsuarioId(rs.getInt("usuario_id"));
            c.setProductoId(rs.getInt("producto_id"));
            c.setCantidad(rs.getInt("cantidad"));
            return c;
        }
    }
    return null;
}


}
