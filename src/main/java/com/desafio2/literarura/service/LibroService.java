package com.desafio2.literarura.service;

import com.desafio2.literarura.model.Libro;
import com.desafio2.literarura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    public List<Libro> listarPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    public Libro guardar(Libro libro) {
        if (libroRepository.findByTituloContainingIgnoreCase(libro.getTitulo()).isEmpty()) {
            return libroRepository.save(libro);
        }
        throw new RuntimeException("No se puede registrar el mismo libro más de una vez");
    }
}

