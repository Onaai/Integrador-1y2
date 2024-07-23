package com.example.modelo;

import java.util.List;

public class Pelicula {
    private String codigo;
    private String titulo;
    private String url;
    private String imagen;
    private List<String> generos;

    // Constructor, getters y setters
    public Pelicula(String codigo, String titulo, String url, String imagen, List<String> generos) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.url = url;
        this.imagen = imagen;
        this.generos = generos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }
}
