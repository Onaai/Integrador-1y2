package com.example.dao;

import com.example.modelo.Pelicula;
import java.util.List;

public interface PeliculaDAO {
    void agregarPelicula(Pelicula pelicula);
    Pelicula obtenerPeliculaPorCodigo(String codigo);
    List<Pelicula> buscarPeliculasPorTitulo(String titulo);
    List<Pelicula> buscarPeliculasPorGenero(String genero);
    void actualizarPelicula(Pelicula pelicula);
    void eliminarPelicula(String codigo);
}

