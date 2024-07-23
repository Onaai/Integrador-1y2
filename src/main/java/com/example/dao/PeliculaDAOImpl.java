
package com.example.dao;

import com.example.modelo.Pelicula;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAOImpl implements PeliculaDAO {
    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/peliculas");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO Pelicula (codigo, titulo, url, imagen, generos) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pelicula.getCodigo());
            stmt.setString(2, pelicula.getTitulo());
            stmt.setString(3, pelicula.getUrl());
            stmt.setString(4, pelicula.getImagen());
            stmt.setString(5, String.join(",", pelicula.getGeneros()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pelicula obtenerPeliculaPorCodigo(String codigo) {
        String sql = "SELECT * FROM Pelicula WHERE codigo = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                List<String> generos = List.of(rs.getString("generos").split(","));
                return new Pelicula(
                        rs.getString("codigo"),
                        rs.getString("titulo"),
                        rs.getString("url"),
                        rs.getString("imagen"),
                        generos
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pelicula> buscarPeliculasPorTitulo(String titulo) {
        String sql = "SELECT * FROM Pelicula WHERE titulo LIKE ?";
        List<Pelicula> peliculas = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + titulo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                List<String> generos = List.of(rs.getString("generos").split(","));
                peliculas.add(new Pelicula(
                        rs.getString("codigo"),
                        rs.getString("titulo"),
                        rs.getString("url"),
                        rs.getString("imagen"),
                        generos
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculas;
    }

    @Override
    public List<Pelicula> buscarPeliculasPorGenero(String genero) {
        String sql = "SELECT * FROM Pelicula WHERE generos LIKE ?";
        List<Pelicula> peliculas = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + genero + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                List<String> generos = List.of(rs.getString("generos").split(","));
                peliculas.add(new Pelicula(
                        rs.getString("codigo"),
                        rs.getString("titulo"),
                        rs.getString("url"),
                        rs.getString("imagen"),
                        generos
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculas;
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        String sql = "UPDATE Pelicula SET titulo = ?, url = ?, imagen = ?, generos = ? WHERE codigo = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getUrl());
            stmt.setString(3, pelicula.getImagen());
            stmt.setString(4, String.join(",", pelicula.getGeneros()));
            stmt.setString(5, pelicula.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPelicula(String codigo) {
        String sql = "DELETE FROM Pelicula WHERE codigo = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



