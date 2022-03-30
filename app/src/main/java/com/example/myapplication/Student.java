package com.example.myapplication;

public class Student {
    private int id;
    private String nom;
    private String prenom;
    private String username;

    public Student(int id, String nom, String prenom, String username) {
        this.id = id;
        this.nom = nom;
        this.prenom=prenom;
        this.username=username;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;
    }
}
