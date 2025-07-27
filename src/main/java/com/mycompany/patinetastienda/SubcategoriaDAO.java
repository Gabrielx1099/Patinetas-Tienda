package com.mycompany.patinetastienda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubcategoriaDAO {

    // Insertar subcategoría
    public int insertSubcategoria(Subcategoria sub) throws SQLException {
        String sql = "INSERT INTO subcategorias (nombre, id_categoria) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, sub.getNombre());
            stmt.setInt(2, sub.getIdCategoria());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        return keys.getInt(1); // ID generado
                    }
                }
            }
        }
        return -1;
    }

    // Obtener todas las subcategorías
    public List<Subcategoria> getAllSubcategorias() throws SQLException {
        List<Subcategoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM subcategorias";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Subcategoria sub = new Subcategoria();
                sub.setId(rs.getInt("id"));
                sub.setNombre(rs.getString("nombre"));
                sub.setIdCategoria(rs.getInt("id_categoria"));
                lista.add(sub);
            }
        }
        return lista;
    }

    // Obtener subcategorías por ID de categoría
    public List<Subcategoria> getSubcategoriasByCategoria(int idCategoria) throws SQLException {
        List<Subcategoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM subcategorias WHERE id_categoria = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCategoria);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subcategoria sub = new Subcategoria();
                    sub.setId(rs.getInt("id"));
                    sub.setNombre(rs.getString("nombre"));
                    sub.setIdCategoria(rs.getInt("id_categoria"));
                    lista.add(sub);
                }
            }
        }
        return lista;
    }

    // Obtener subcategoría por ID
    public Subcategoria getSubcategoriaById(int id) throws SQLException {
        String sql = "SELECT * FROM subcategorias WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Subcategoria(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("id_categoria")
                    );
                }
            }
        }
        return null;
    }

    // Eliminar subcategoría por ID
    public boolean deleteSubcategoria(int id) throws SQLException {
        String sql = "DELETE FROM subcategorias WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Actualizar subcategoría
    public boolean updateSubcategoria(Subcategoria sub) throws SQLException {
        String sql = "UPDATE subcategorias SET nombre = ?, id_categoria = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sub.getNombre());
            stmt.setInt(2, sub.getIdCategoria());
            stmt.setInt(3, sub.getId());
            return stmt.executeUpdate() > 0;
        }
    }
}
