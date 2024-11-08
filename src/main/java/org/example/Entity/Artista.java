package org.example.Entity;


import org.example.Enumeracion.Genero;

public class Artista {
    private String nombre;
    private Genero genero;

    public Artista(String nombre, Genero genero) {
        this.nombre = nombre;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public Genero getGenero() {
        return genero;
    }

    public void procesarArtista() {
        System.out.println("Procesando artista: " + nombre + " (" + genero + ")");
    }

    @Override
    public String toString() {
        return "Artista: " + nombre + " (Género: " + genero + ")";
    }
}
