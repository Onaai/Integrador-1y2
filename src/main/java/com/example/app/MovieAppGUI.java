package com.example.app;

import com.example.dao.PeliculaDAO;
import com.example.dao.PeliculaDAOImpl;
import com.example.modelo.Pelicula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MovieAppGUI extends JFrame {
    private PeliculaDAO peliculaDAO = new PeliculaDAOImpl();
    private JTextField searchField;
    private JTextArea resultArea;
    private JTextArea detailArea;
    private JButton searchButton;

    public MovieAppGUI() {
        setTitle("Buscador de Peliculas - EducacionIT");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        JLabel searchLabel = new JLabel("Buscar por titulo, genero o codigo:");
        searchField = new JTextField(20);
        searchButton = new JButton("Buscar");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panel.add(searchPanel, BorderLayout.NORTH);

        // Results area
        JPanel resultsPanel = new JPanel(new BorderLayout());
        JLabel resultsLabel = new JLabel("Resultado por busqueda de titulo o genero"); // Actualizado
        resultsPanel.add(resultsLabel, BorderLayout.NORTH);
        resultArea = new JTextArea(10, 50);
        resultArea.setEditable(false);
        resultsPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        panel.add(resultsPanel, BorderLayout.CENTER);

        // Detail area
        JPanel detailsPanel = new JPanel(new BorderLayout());
        JLabel detailsLabel = new JLabel("Detalles de resultado por codigo"); // Actualizado
        detailsPanel.add(detailsLabel, BorderLayout.NORTH);
        detailArea = new JTextArea(5, 50);
        detailArea.setEditable(false);
        detailsPanel.add(new JScrollPane(detailArea), BorderLayout.CENTER);

        panel.add(detailsPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMovies();
            }
        });

        add(panel);
        displayAllMovies(); // Display all movies initially
    }

    private void displayAllMovies() {
        List<Pelicula> peliculas = peliculaDAO.buscarPeliculasPorTitulo(""); // Fetch all movies
        mostrarResultados(peliculas);
    }

    private void searchMovies() {
        String searchText = searchField.getText();
        List<Pelicula> peliculas = peliculaDAO.buscarPeliculasPorTitulo(searchText);
        if (peliculas.isEmpty()) {
            peliculas = peliculaDAO.buscarPeliculasPorGenero(searchText);
        }
        if (peliculas.isEmpty()) {
            Pelicula pelicula = peliculaDAO.obtenerPeliculaPorCodigo(searchText);
            if (pelicula != null) {
                peliculas.add(pelicula);
            }
        }
        mostrarResultados(peliculas);
        mostrarDetalles(peliculas);
    }

    private void mostrarResultados(List<Pelicula> peliculas) {
        resultArea.setText("");
        for (Pelicula pelicula : peliculas) {
            resultArea.append("Codigo: " + pelicula.getCodigo() + ", Titulo: " + pelicula.getTitulo() + "\n");
        }
    }

    private void mostrarDetalles(List<Pelicula> peliculas) {
        if (!peliculas.isEmpty()) {
            detailArea.setText("");
            for (Pelicula pelicula : peliculas) {
                detailArea.append("Codigo: " + pelicula.getCodigo() + "\n");
                detailArea.append("Titulo: " + pelicula.getTitulo() + "\n");
                detailArea.append("URL: " + pelicula.getUrl() + "\n");
                detailArea.append("Imagen: " + pelicula.getImagen() + "\n");
                detailArea.append("Generos: " + String.join(", ", pelicula.getGeneros()) + "\n\n");
            }
        } else {
            detailArea.setText("No se encontraron peliculas.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovieAppGUI app = new MovieAppGUI();
            app.setVisible(true);
        });
    }
}
