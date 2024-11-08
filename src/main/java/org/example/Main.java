package org.example;

import org.example.Entity.Artista;
import org.example.Enumeracion.Genero;
import org.example.Persistence.ArtistaPersistence;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArtistaPersistence artistaPersistence = new ArtistaPersistence();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Agregar Artista");
            System.out.println("2. Ver Artistas");
            System.out.println("3. Procesar Artista");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarArtista(scanner, artistaPersistence);
                    break;
                case 2:
                    verArtistas(artistaPersistence);
                    break;
                case 3:
                    procesarArtistas(scanner, artistaPersistence);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    artistaPersistence.cerrar();
                    return;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
    }

    private static void agregarArtista(Scanner scanner, ArtistaPersistence artistaPersistence) {
        System.out.print("\nIngrese el nombre del artista: ");
        String nombre = scanner.nextLine();

        System.out.println("Seleccione el género del artista:");
        System.out.println("1. ROCK");
        System.out.println("2. POP");
        System.out.println("3. JAZZ");
        System.out.print("Seleccione una opción: ");
        int opcionGenero = scanner.nextInt();
        scanner.nextLine();

        Genero genero = null;
        switch (opcionGenero) {
            case 1:
                genero = Genero.ROCK;
                break;
            case 2:
                genero = Genero.POP;
                break;
            case 3:
                genero = Genero.JAZZ;
                break;
            default:
                System.out.println("Género no válido, asignando ROCK por defecto.");
                genero = Genero.ROCK;
        }

        Artista nuevoArtista = new Artista(nombre, genero);
        artistaPersistence.guardarArtista(nuevoArtista);
        System.out.println("Artista " + nombre + " agregado correctamente.");
    }

    private static void verArtistas(ArtistaPersistence artistaPersistence) {
        List<Artista> artistas = artistaPersistence.obtenerArtistas();
        if (artistas.isEmpty()) {
            System.out.println("\nNo hay artistas guardados.");
        } else {
            System.out.println("\n--- Lista de Artistas ---");
            for (Artista artista : artistas) {
                System.out.println(artista);
            }
        }
    }

    private static void procesarArtistas(Scanner scanner, ArtistaPersistence artistaPersistence) {
        List<Artista> artistas = artistaPersistence.obtenerArtistas();
        if (artistas.isEmpty()) {
            System.out.println("\nNo hay artistas para procesar.");
            return;
        }

        System.out.println("\n--- Seleccione un artista para procesar ---");
        for (int i = 0; i < artistas.size(); i++) {
            System.out.println((i + 1) + ". " + artistas.get(i).getNombre());
        }

        System.out.print("Seleccione el número del artista a procesar: ");
        int opcionArtista = scanner.nextInt();
        scanner.nextLine();

        if (opcionArtista > 0 && opcionArtista <= artistas.size()) {
            Artista artista = artistas.get(opcionArtista - 1);
            System.out.println("Procesando artista: " + artista.getNombre());
            artista.procesarArtista();
        } else {
            System.out.println("Opción no válida.");
        }
    }
}
