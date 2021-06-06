package com.example.parcial2android;

public class Pelicula {

    private String id;
    private float total, votos;

    public Pelicula() {
    }

    public Pelicula(String id, Long total, Long votos) {
        this.id = id;
        this.total = total;
        this.votos = votos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getVotos() {
        return votos;
    }

    public void setVotos(float votos) {
        this.votos = votos;
    }
}
