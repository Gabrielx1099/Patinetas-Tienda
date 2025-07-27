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
public class VentaDAO {
    
    // Obtener venta por ID
    public Venta getVentaById(int id) throws SQLException {
        Venta venta = null;
        String sql = "SELECT * FROM ventas WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setUsuarioId(rs.getInt("usuario_id"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setTotal(rs.getDouble("total"));
                venta.setEstado(rs.getString("estado"));
                venta.setDireccionEnvio(rs.getString("direccion_envio"));
                venta.setTipoEntrega(rs.getString("tipo_entrega"));
                venta.setCostoDelivery(rs.getDouble("costo_delivery"));
                venta.setTelefonoEntrega(rs.getString("telefono_entrega"));
            }
        }
        return venta;
    }

    // Obtener todas las ventas
    public List<Venta> getAllVentas() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas ORDER BY fecha_venta DESC";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setUsuarioId(rs.getInt("usuario_id"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setTotal(rs.getDouble("total"));
                venta.setEstado(rs.getString("estado"));
                venta.setDireccionEnvio(rs.getString("direccion_envio"));
                venta.setTipoEntrega(rs.getString("tipo_entrega"));
                venta.setCostoDelivery(rs.getDouble("costo_delivery"));
                venta.setTelefonoEntrega(rs.getString("telefono_entrega"));
                ventas.add(venta);
            }
        }
        return ventas;
    }
    
    // Obtener ventas por usuario
    public List<Venta> getVentasByUsuario(int usuarioId) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE usuario_id = ? ORDER BY fecha_venta DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setUsuarioId(rs.getInt("usuario_id"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setTotal(rs.getDouble("total"));
                venta.setEstado(rs.getString("estado"));
                venta.setDireccionEnvio(rs.getString("direccion_envio"));
                venta.setTipoEntrega(rs.getString("tipo_entrega"));
                venta.setCostoDelivery(rs.getDouble("costo_delivery"));
                venta.setTelefonoEntrega(rs.getString("telefono_entrega"));
                ventas.add(venta);
            }
        }
        return ventas;
    }
    
    // Obtener ventas por estado
    public List<Venta> getVentasByEstado(String estado) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE estado = ? ORDER BY fecha_venta DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estado);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setUsuarioId(rs.getInt("usuario_id"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setTotal(rs.getDouble("total"));
                venta.setEstado(rs.getString("estado"));
                venta.setDireccionEnvio(rs.getString("direccion_envio"));
                venta.setTipoEntrega(rs.getString("tipo_entrega"));
                venta.setCostoDelivery(rs.getDouble("costo_delivery"));
                venta.setTelefonoEntrega(rs.getString("telefono_entrega"));
                ventas.add(venta);
            }
        }
        return ventas;
    }
    
    // Obtener ventas por tipo de entrega
    public List<Venta> getVentasByTipoEntrega(String tipoEntrega) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE tipo_entrega = ? ORDER BY fecha_venta DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipoEntrega);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setUsuarioId(rs.getInt("usuario_id"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setTotal(rs.getDouble("total"));
                venta.setEstado(rs.getString("estado"));
                venta.setDireccionEnvio(rs.getString("direccion_envio"));
                venta.setTipoEntrega(rs.getString("tipo_entrega"));
                venta.setCostoDelivery(rs.getDouble("costo_delivery"));
                venta.setTelefonoEntrega(rs.getString("telefono_entrega"));
                ventas.add(venta);
            }
        }
        return ventas;
    }
    
    // Obtener ventas por rango de fechas
    public List<Venta> getVentasByRangoFechas(String fechaInicio, String fechaFin) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE DATE(fecha_venta) BETWEEN ? AND ? ORDER BY fecha_venta DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fechaInicio);
            stmt.setString(2, fechaFin);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setUsuarioId(rs.getInt("usuario_id"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setTotal(rs.getDouble("total"));
                venta.setEstado(rs.getString("estado"));
                venta.setDireccionEnvio(rs.getString("direccion_envio"));
                venta.setTipoEntrega(rs.getString("tipo_entrega"));
                venta.setCostoDelivery(rs.getDouble("costo_delivery"));
                venta.setTelefonoEntrega(rs.getString("telefono_entrega"));
                ventas.add(venta);
            }
        }
        return ventas;
    }
    
    // Obtener ventas del día actual
    public List<Venta> getVentasDelDia() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE DATE(fecha_venta) = CURDATE() ORDER BY fecha_venta DESC";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setUsuarioId(rs.getInt("usuario_id"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setTotal(rs.getDouble("total"));
                venta.setEstado(rs.getString("estado"));
                venta.setDireccionEnvio(rs.getString("direccion_envio"));
                venta.setTipoEntrega(rs.getString("tipo_entrega"));
                venta.setCostoDelivery(rs.getDouble("costo_delivery"));
                venta.setTelefonoEntrega(rs.getString("telefono_entrega"));
                ventas.add(venta);
            }
        }
        return ventas;
    }
    
    // Insertar venta
    public int insertVenta(Venta venta) throws SQLException {
        String sql = "INSERT INTO ventas (usuario_id, fecha_venta, total, estado, direccion_envio, tipo_entrega, costo_delivery, telefono_entrega) VALUES (?, NOW(), ?, ?, ?, ?, ?, ?)";
        System.out.println("DEBUG: Intentando insertar venta para usuario: " + venta.getUsuarioId());
        System.out.println("DEBUG: Total de la venta: " + venta.getTotal());
        System.out.println("DEBUG: Estado de la venta: " + venta.getEstado());
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, venta.getUsuarioId());
            stmt.setDouble(2, venta.getTotal());
            stmt.setString(3, venta.getEstado());
            stmt.setString(4, venta.getDireccionEnvio());
            stmt.setString(5, venta.getTipoEntrega());
            stmt.setDouble(6, venta.getCostoDelivery());
            stmt.setString(7, venta.getTelefonoEntrega());
            
            System.out.println("DEBUG: Parámetros preparados:");
            System.out.println("DEBUG: - Usuario ID: " + venta.getUsuarioId());
            System.out.println("DEBUG: - Total: " + venta.getTotal());
            System.out.println("DEBUG: - Estado: " + venta.getEstado());
            System.out.println("DEBUG: - Dirección: " + venta.getDireccionEnvio());
            System.out.println("DEBUG: - Tipo entrega: " + venta.getTipoEntrega());
            System.out.println("DEBUG: - Costo delivery: " + venta.getCostoDelivery());
            System.out.println("DEBUG: - Teléfono: " + venta.getTelefonoEntrega());
            
            int affectedRows = stmt.executeUpdate();
            System.out.println("DEBUG: executeUpdate affected rows: " + affectedRows);
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("DEBUG: Generated Venta ID: " + generatedId);
                        return generatedId;
                    }
                }
            }
            System.out.println("DEBUG: Failed to get generated ID or no rows affected.");
            return -1; // Retorna -1 si no se pudo obtener el ID
        }
    }

    // Actualizar venta
    public boolean updateVenta(Venta venta) throws SQLException {
        String sql = "UPDATE ventas SET usuario_id = ?, total = ?, estado = ?, direccion_envio = ?, tipo_entrega = ?, costo_delivery = ?, telefono_entrega = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venta.getUsuarioId());
            stmt.setDouble(2, venta.getTotal());
            stmt.setString(3, venta.getEstado());
            stmt.setString(4, venta.getDireccionEnvio());
            stmt.setString(5, venta.getTipoEntrega());
            stmt.setDouble(6, venta.getCostoDelivery());
            stmt.setString(7, venta.getTelefonoEntrega());
            stmt.setInt(8, venta.getId());
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Actualizar solo el estado de la venta
    public boolean updateEstadoVenta(int id, String nuevoEstado) throws SQLException {
        String sql = "UPDATE ventas SET estado = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Actualizar información de entrega
    public boolean updateDatosEntrega(int id, String direccionEnvio, String telefonoEntrega) throws SQLException {
        String sql = "UPDATE ventas SET direccion_envio = ?, telefono_entrega = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, direccionEnvio);
            stmt.setString(2, telefonoEntrega);
            stmt.setInt(3, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar venta (generalmente no se usa, mejor cambiar estado)
    public boolean deleteVenta(int id) throws SQLException {
        String sql = "DELETE FROM ventas WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Registrar venta con validaciones
    public int registrarVenta(Venta venta) throws Exception {
        // Validar total
        if (venta.getTotal() <= 0) {
            throw new Exception("El total de la venta debe ser mayor a 0");
        }
        
        // Validar usuario
        if (venta.getUsuarioId() <= 0) {
            throw new Exception("Debe especificar un usuario válido");
        }
        
        // Validar estado
        if (venta.getEstado() == null || venta.getEstado().trim().isEmpty()) {
            venta.setEstado("PENDIENTE"); // Estado por defecto
        }
        
        // Validar tipo de entrega
        if (venta.getTipoEntrega() == null || venta.getTipoEntrega().trim().isEmpty()) {
            throw new Exception("Debe especificar el tipo de entrega");
        }
        
        // Validar dirección si es delivery
        if ("DELIVERY".equalsIgnoreCase(venta.getTipoEntrega())) {
            if (venta.getDireccionEnvio() == null || venta.getDireccionEnvio().trim().isEmpty()) {
                throw new Exception("La dirección de envío es obligatoria para delivery");
            }
            if (venta.getTelefonoEntrega() == null || venta.getTelefonoEntrega().trim().isEmpty()) {
                throw new Exception("El teléfono de contacto es obligatorio para delivery");
            }
        }

        System.out.println("DEBUG: registrarVenta - Usuario ID: " + venta.getUsuarioId());
        System.out.println("DEBUG: registrarVenta - Total: " + venta.getTotal());
        System.out.println("DEBUG: registrarVenta - Estado: " + venta.getEstado());
        System.out.println("DEBUG: registrarVenta - Tipo entrega: " + venta.getTipoEntrega());

        return insertVenta(venta);
    }
    
    // Contar ventas
    public int countVentas() throws SQLException {
        String sql = "SELECT COUNT(*) FROM ventas";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
    
    // Contar ventas por estado
    public int countVentasByEstado(String estado) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ventas WHERE estado = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estado);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
    
    // Contar ventas por usuario
    public int countVentasByUsuario(int usuarioId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ventas WHERE usuario_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
    
    // Contar ventas del día
    public int countVentasDelDia() throws SQLException {
        String sql = "SELECT COUNT(*) FROM ventas WHERE DATE(fecha_venta) = CURDATE()";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
    
    // Obtener total de ventas (suma de montos)
    public double getTotalVentas() throws SQLException {
        String sql = "SELECT SUM(total) FROM ventas";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }
    
    // Obtener total de ventas por estado
    public double getTotalVentasByEstado(String estado) throws SQLException {
        String sql = "SELECT SUM(total) FROM ventas WHERE estado = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estado);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }
    
    // Obtener total de ventas del día
    public double getTotalVentasDelDia() throws SQLException {
        String sql = "SELECT SUM(total) FROM ventas WHERE DATE(fecha_venta) = CURDATE()";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }
    
    // Obtener total de delivery cobrado
    public double getTotalDeliveryCobrado() throws SQLException {
        String sql = "SELECT SUM(costo_delivery) FROM ventas WHERE tipo_entrega = 'DELIVERY'";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }
}