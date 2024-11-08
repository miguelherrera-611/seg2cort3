package org.example.Principal;

import org.example.Entity.Artista;
import org.example.Enumeracion.Genero;
import org.example.Persistence.ArtistaPersistence;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArtistaPersistence artistaPersistence = new ArtistaPersistence();

        Artista artista1 = new Artista("The Rolling Stones", Genero.ROCK);
        Artista artista2 = new Artista("Ariana Grande", Genero.POP);
        Artista artista3 = new Artista("Miles Davis", Genero.JAZZ);

        artistaPersistence.guardarArtista(artista1);
        artistaPersistence.guardarArtista(artista2);
        artistaPersistence.guardarArtista(artista3);

        Thread hilo1 = new Thread(() -> {
            artista1.procesarArtista();
        });

        Thread hilo2 = new Thread(() -> {
            artista2.procesarArtista();
        });

        Thread hilo3 = new Thread(() -> {
            artista3.procesarArtista();
        });

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Lista de artistas ---");
        List<Artista> artistas = artistaPersistence.obtenerArtistas();
        for (Artista artista : artistas) {
            System.out.println(artista);
        }

        artistaPersistence.cerrar();
    }
}


