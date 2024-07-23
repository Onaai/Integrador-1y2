package com.example.app;

import com.example.dao.PeliculaDAO;
import com.example.dao.PeliculaDAOImpl;
import com.example.modelo.Pelicula;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static PeliculaDAO peliculaDAO = new PeliculaDAOImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar película por título");
            System.out.println("2. Buscar película por género");
            System.out.println("3. Salir");
            int opcion = 0;

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Por favor, ingrese 1, 2 o 3.");
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarPeliculaPorTitulo(scanner);
                    break;
                case 2:
                    buscarPeliculaPorGenero(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese 1, 2 o 3.");
            }
        }
    }

    private static void buscarPeliculaPorTitulo(Scanner scanner) {
        System.out.println("Ingrese el título:");
        String titulo = scanner.nextLine();
        List<Pelicula> peliculas = peliculaDAO.buscarPeliculasPorTitulo(titulo);
        mostrarPeliculas(peliculas, scanner);
    }

    private static void buscarPeliculaPorGenero(Scanner scanner) {
        System.out.println("Ingrese el género:");
        String genero = scanner.nextLine();
        List<Pelicula> peliculas = peliculaDAO.buscarPeliculasPorGenero(genero);
        mostrarPeliculas(peliculas, scanner);
    }

    private static void mostrarPeliculas(List<Pelicula> peliculas, Scanner scanner) {
        if (peliculas.isEmpty()) {
            System.out.println("No se encontraron películas.");
        } else {
            for (Pelicula pelicula : peliculas) {
                System.out.println("Código: " + pelicula.getCodigo() + ", Título: " + pelicula.getTitulo());
            }

            System.out.println("Ingrese el código de la película para ver detalles, o presione Enter para regresar al menú:");
            String codigo = scanner.nextLine();

            if (!codigo.trim().isEmpty()) {
                Pelicula pelicula = peliculaDAO.obtenerPeliculaPorCodigo(codigo);
                mostrarDetallesPelicula(pelicula);
            }
        }
    }

    private static void mostrarDetallesPelicula(Pelicula pelicula) {
        if (pelicula != null) {
            System.out.println("Código: " + pelicula.getCodigo());
            System.out.println("Título: " + pelicula.getTitulo());
            System.out.println("URL: " + pelicula.getUrl());
            System.out.println("Imagen: " + pelicula.getImagen());
            System.out.println("Géneros: " + String.join(", ", pelicula.getGeneros()));
        } else {
            System.out.println("Película no encontrada.");
        }
    }
}
