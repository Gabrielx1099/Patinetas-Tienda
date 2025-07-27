package com.mycompany.patinetastienda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO {

    // Mapear ResultSet a objeto Marca
    private Marca mapResultSetToMarca(ResultSet rs) throws SQLException {
        return new Marca(rs.getInt("id"), rs.getString("nombre"));
    }

    // Obtener marca por nombre
    public Marca getMarcaByNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM marcas WHERE nombre = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMarca(rs);
                }
            }
        }
        return null;
    }

    // Obtener todas las marcas
    public List<Marca> getAllMarcas() throws SQLException {
        List<Marca> marcas = new ArrayList<>();
        String sql = "SELECT * FROM marcas";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                marcas.add(mapResultSetToMarca(rs));
            }
        }
        return marcas;
    }

    // Insertar nueva marca y retornar el ID generado
    public int insertMarca(Marca marca) throws SQLException {
        validarMarca(marca);

        String sql = "INSERT INTO marcas (nombre) VALUES (?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, marca.getNombre());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        return keys.getInt(1);
                    }
                }
            }
        }

        return -1; // Inserción fallida
    }

    // Actualizar marca existente
    public boolean updateMarca(Marca marca) throws SQLException {
        validarMarca(marca);

        String sql = "UPDATE marcas SET nombre = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, marca.getNombre());
            stmt.setInt(2, marca.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar marca por ID
    public boolean deleteMarca(int id) throws SQLException {
        String sql = "DELETE FROM marcas WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Registrar nueva marca (con validación de duplicado)
    public void registrarMarca(Marca marca) throws Exception {
        validarMarca(marca);

        if (getMarcaByNombre(marca.getNombre()) != null) {
            throw new Exception("El nombre de la marca ya está registrado.");
        }

        insertMarca(marca); // Reutiliza lógica de inserción
    }

    // Obtener marca por ID
    public Marca getMarcaById(int id) throws SQLException {
        String sql = "SELECT * FROM marcas WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMarca(rs);
                }
            }
        }
        return null;
    }

    // Contar cuántas marcas hay
    public int countMarcas() throws SQLException {
        String sql = "SELECT COUNT(*) FROM marcas";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Validación básica de marca
    private void validarMarca(Marca marca) {
        if (marca == null || marca.getNombre() == null || marca.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la marca no puede estar vacío.");
        }
    }
}
