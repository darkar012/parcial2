package com.example.parcial2android;

public class User {

    public String id, nombre, apellido, ciudad, correo;

    public User() {
    }

    public User(String id, String name, String apellido, String city, String email) {
        this.id = id;
        this.nombre = name;
        this.apellido = name;
        this.ciudad = city;
        this.correo = email;
    }
}
