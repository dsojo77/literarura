package com.desafio2.literarura.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String idioma;

    private String autor;

    private Integer anio;

    public Libro() {}

    public Libro(String titulo, String idioma, String autor, Integer anio) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.autor = autor;
        this.anio = anio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(id, libro.id) && Objects.equals(titulo, libro.titulo) && Objects.equals(idioma, libro.idioma) && Objects.equals(autor, libro.autor) && Objects.equals(anio, libro.anio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, idioma, autor, anio);
    }

    // Getters y Setters
    // hashCode y equals

}
