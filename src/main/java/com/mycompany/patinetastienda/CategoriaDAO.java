package com.mycompany.patinetastienda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    // Mapear ResultSet a objeto Categoria
    private Categoria mapResultSetToCategoria(ResultSet rs) throws SQLException {
        return new Categoria(rs.getInt("id"), rs.getString("nombre"));
    }

    // Obtener categoría por nombre
    public Categoria getCategoriaByNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM categorias WHERE nombre = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCategoria(rs);
                }
            }
        }
        return null;
    }

    // Obtener todas las categorías
    public List<Categoria> getAllCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categorias.add(mapResultSetToCategoria(rs));
            }
        }
        return categorias;
    }

    // Insertar nueva categoría y retornar el ID generado
    public int insertCategoria(Categoria categoria) throws SQLException {
        validarCategoria(categoria);

        String sql = "INSERT INTO categorias (nombre) VALUES (?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, categoria.getNombre());
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

    // Actualizar categoría existente
    public boolean updateCategoria(Categoria categoria) throws SQLException {
        validarCategoria(categoria);

        String sql = "UPDATE categorias SET nombre = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNombre());
            stmt.setInt(2, categoria.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Eliminar categoría por ID
    public boolean deleteCategoria(int id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Registrar nueva categoría (con validación de duplicado)
    public void registrarCategoria(Categoria categoria) throws Exception {
        validarCategoria(categoria);

        if (getCategoriaByNombre(categoria.getNombre()) != null) {
            throw new Exception("El nombre de la categoría ya está registrado.");
        }

        insertCategoria(categoria); // Reutiliza lógica de inserción
    }

    // Obtener categoría por ID
    public Categoria getCategoriaById(int id) throws SQLException {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCategoria(rs);
                }
            }
        }
        return null;
    }

    // Contar cuántas categorías hay
    public int countCategorias() throws SQLException {
        String sql = "SELECT COUNT(*) FROM categorias";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Validación básica de categoría
    private void validarCategoria(Categoria categoria) {
        if (categoria == null || categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }
    }
}
