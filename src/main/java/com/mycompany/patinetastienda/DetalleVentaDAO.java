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
public class DetalleVentaDAO {
    
    // Obtener detalle de venta por ID
    public DetalleVenta getDetalleVentaById(int id) throws SQLException {
        DetalleVenta detalleVenta = null;
        String sql = "SELECT * FROM detalles_venta WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                detalleVenta = new DetalleVenta();
                detalleVenta.setId(rs.getInt("id"));
                detalleVenta.setVentaId(rs.getInt("venta_id"));
                detalleVenta.setProductoId(rs.getInt("producto_id"));
                detalleVenta.setCantidad(rs.getInt("cantidad"));
                detalleVenta.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalleVenta.setSubtotal(rs.getDouble("subtotal"));
            }
        }
        return detalleVenta;
    }

    // Obtener todos los detalles de venta
    public List<DetalleVenta> getAllDetallesVenta() throws SQLException {
        List<DetalleVenta> detallesVenta = new ArrayList<>();
        String sql = "SELECT * FROM detalles_venta";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setId(rs.getInt("id"));
                detalleVenta.setVentaId(rs.getInt("venta_id"));
                detalleVenta.setProductoId(rs.getInt("producto_id"));
                detalleVenta.setCantidad(rs.getInt("cantidad"));
                detalleVenta.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalleVenta.setSubtotal(rs.getDouble("subtotal"));
                detallesVenta.add(detalleVenta);
            }
        }
        return detallesVenta;
    }

    // Obtener detalles de venta por ID de venta
    public List<DetalleVenta> getDetallesVentaByVentaId(int ventaId) throws SQLException {
        List<DetalleVenta> detallesVenta = new ArrayList<>();
        String sql = "SELECT * FROM detalles_venta WHERE venta_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ventaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setId(rs.getInt("id"));
                detalleVenta.setVentaId(rs.getInt("venta_id"));
                detalleVenta.setProductoId(rs.getInt("producto_id"));
                detalleVenta.setCantidad(rs.getInt("cantidad"));
                detalleVenta.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalleVenta.setSubtotal(rs.getDouble("subtotal"));
                detallesVenta.add(detalleVenta);
            }
        }
        return detallesVenta;
    }

    // Obtener detalles de venta por ID de producto
    public List<DetalleVenta> getDetallesVentaByProductoId(int productoId) throws SQLException {
        List<DetalleVenta> detallesVenta = new ArrayList<>();
        String sql = "SELECT * FROM detalles_venta WHERE producto_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setId(rs.getInt("id"));
                detalleVenta.setVentaId(rs.getInt("venta_id"));
                detalleVenta.setProductoId(rs.getInt("producto_id"));
                detalleVenta.setCantidad(rs.getInt("cantidad"));
                detalleVenta.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalleVenta.setSubtotal(rs.getDouble("subtotal"));
                detallesVenta.add(detalleVenta);
            }
        }
        return detallesVenta;
    }
    
    // Insertar detalle de venta
    public int insertDetalleVenta(DetalleVenta detalleVenta) throws SQLException {
        String sql = "INSERT INTO detalles_venta (venta_id, producto_id, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        System.out.println("DEBUG: Intentando insertar detalle de venta para venta ID: " + detalleVenta.getVentaId());
        System.out.println("DEBUG: Producto ID: " + detalleVenta.getProductoId());
        System.out.println("DEBUG: Cantidad: " + detalleVenta.getCantidad());
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, detalleVenta.getVentaId());
            stmt.setInt(2, detalleVenta.getProductoId());
            stmt.setInt(3, detalleVenta.getCantidad());
            stmt.setDouble(4, detalleVenta.getPrecioUnitario());
            stmt.setDouble(5, detalleVenta.getSubtotal());
            
            System.out.println("DEBUG: Parámetros preparados:");
            System.out.println("DEBUG: - Venta ID: " + detalleVenta.getVentaId());
            System.out.println("DEBUG: - Producto ID: " + detalleVenta.getProductoId());
            System.out.println("DEBUG: - Cantidad: " + detalleVenta.getCantidad());
            System.out.println("DEBUG: - Precio Unitario: " + detalleVenta.getPrecioUnitario());
            System.out.println("DEBUG: - Subtotal: " + detalleVenta.getSubtotal());
            
            int affectedRows = stmt.executeUpdate();
            System.out.println("DEBUG: executeUpdate affected rows: " + affectedRows);
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("DEBUG: Generated DetalleVenta ID: " + generatedId);
                        return generatedId;
                    }
                }
            }
            System.out.println("DEBUG: Failed to get generated ID or no rows affected.");
            return -1; // Retorna -1 si no se pudo obtener el ID
        }
    }

    // Actualizar detalle de venta
    public boolean updateDetalleVenta(DetalleVenta detalleVenta) throws SQLException {
        String sql = "UPDATE detalles_venta SET venta_id = ?, producto_id = ?, cantidad = ?, precio_unitario = ?, subtotal = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detalleVenta.getVentaId());
            stmt.setInt(2, detalleVenta.getProductoId());
            stmt.setInt(3, detalleVenta.getCantidad());
            stmt.setDouble(4, detalleVenta.getPrecioUnitario());
            stmt.setDouble(5, detalleVenta.getSubtotal());
            stmt.setInt(6, detalleVenta.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar detalle de venta
    public boolean deleteDetalleVenta(int id) throws SQLException {
        String sql = "DELETE FROM detalles_venta WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar todos los detalles de una venta específica
    public boolean deleteDetallesVentaByVentaId(int ventaId) throws SQLException {
        String sql = "DELETE FROM detalles_venta WHERE venta_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ventaId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Contar total de detalles de venta
    public int countDetallesVenta() throws SQLException {
        String sql = "SELECT COUNT(*) FROM detalles_venta";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
    
    // Contar detalles de venta por ID de venta
    public int countDetallesVentaByVentaId(int ventaId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM detalles_venta WHERE venta_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ventaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    // Contar detalles de venta por ID de producto
    public int countDetallesVentaByProductoId(int productoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM detalles_venta WHERE producto_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    // Calcular total de cantidad vendida por producto
    public int getTotalCantidadVendidaByProducto(int productoId) throws SQLException {
        String sql = "SELECT SUM(cantidad) FROM detalles_venta WHERE producto_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Calcular total de ingresos por producto
    public double getTotalIngresosByProducto(int productoId) throws SQLException {
        String sql = "SELECT SUM(subtotal) FROM detalles_venta WHERE producto_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0.0;
    }
}