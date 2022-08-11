package com.example.demo.model;

import java.sql.Date;

public class Modulo {
    private int id;
    private String titulo;
    private Date stamp;
    private int curso;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getStamp() {
        return stamp;
    }
    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public int getCurso() {
        return curso;
    }
    public void setCurso(int curso) {
        this.curso = curso;
    }

    public Modulo() { }

    public Modulo(String titulo, Date stamp, int curso) {
        this.titulo = titulo;
        this.stamp = stamp;
        this.curso = curso;
    }

    public Modulo(int id, String titulo, Date stamp, int curso) {
        this.id = id;
        this.titulo = titulo;
        this.stamp = stamp;
        this.curso = curso;
    }
}
