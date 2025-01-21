package com.desafio2.literarura.controller;

import com.desafio2.literarura.model.Libro;
import com.desafio2.literarura.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;

@Controller
public class MenuController {

    private final LibroService libroService;
    private final RestTemplate restTemplate;

    public MenuController(LibroService libroService) {
        this.libroService = libroService;
        this.restTemplate = new RestTemplate();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        while (true) {
            mostrarOpciones();
            System.out.print("Seleccione una opción: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // Consumir entrada inválida
                continue;
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            try {
                if (opcion == 0) {
                    System.out.println("Saliendo del programa...");
                    break;
                }
                procesarOpcion(opcion, scanner);
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }

    private void mostrarOpciones() {
        System.out.println("\nMenú:");
        System.out.println("1. Buscar libros por títulos");
        System.out.println("2. Listar libros registrados en BD");
        System.out.println("3. Listar autores registrados en BD");
        System.out.println("4. Listar autores vivos en un determinado año en BD");
        System.out.println("5. Listar libros por idioma en BD");
        System.out.println("0. Salir");
    }

    private void procesarOpcion(int opcion, Scanner scanner) {
        switch (opcion) {
            case 1 -> buscarLibro(scanner);
            case 2 -> listarLibros();
            case 3 -> listarAutores();
            case 4 -> listarAutoresVivos(scanner);
            case 5 -> listarLibrosPorIdioma(scanner);
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    private void buscarLibro(Scanner scanner) {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        String url = "https://gutendex.com/books?search=" + titulo;

        try {
            var response = restTemplate.getForObject(url, String.class);
            if (response != null && !response.isEmpty()) {
                // TODO: Mapear la respuesta JSON a un objeto Libro
                Libro libro = new Libro();
                libroService.guardar(libro);
                System.out.println("Libro guardado exitosamente.");
            } else {
                System.out.println("Libro no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar o guardar el libro: " + e.getMessage());
        }
    }

    private void listarLibros() {
        List<Libro> libros = libroService.listarTodos();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(libro -> System.out.println(libro.toString()));
        }
    }

    private void listarAutores() {
        // TODO: Implementar lógica para listar autores únicos de la BD
        System.out.println("Función no implementada aún.");
    }

    private void listarAutoresVivos(Scanner scanner) {
        System.out.print("Ingrese el año: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Año inválido. Intente nuevamente.");
            scanner.next(); // Consumir entrada inválida
            return;
        }
        int anio = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        // TODO: Implementar lógica para listar autores vivos en el año especificado
        System.out.println("Función no implementada aún para el año: " + anio);
    }

    private void listarLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingrese el idioma (código ISO): ");
        String idioma = scanner.nextLine();
        List<Libro> libros = libroService.listarPorIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + idioma);
        } else {
            libros.forEach(libro -> System.out.println(libro.toString()));
        }
    }
}