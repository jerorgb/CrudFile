package com.jero.playerCrud;

import java.util.concurrent.atomic.AtomicInteger;

public class Jugadores {
    
   private final static AtomicInteger idGen = new AtomicInteger(0);
   private int id;
   private String nombre;
   private String juegoFavorito;
   private char level;
   private int score;

    public static int getNextID() {
        return idGen.incrementAndGet();
    }

    public Jugadores(String nombre, String juegoFavorito, char level, int score) {
        
        this.nombre = nombre;
        this.juegoFavorito = juegoFavorito;
        this.level = level;
        this.score = score;
        this.id=getNextID();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getJuegoFavorito() {
        return juegoFavorito;
    }

    public void setJuegoFavorito(String juegoFavorito) {
        this.juegoFavorito = juegoFavorito;
    }

    public char getLevel() {
        return level;
    }

    public void setLevel(char level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}


