package com.example.demo.model;

import java.sql.Date;

public class Curso {
    private int     id;
    private String  titulo;
    private String  descricao;
    private Date    stamp;
    private int     usuario;

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

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getStamp() {
        return stamp;
    }
    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public int getUsuario() {
        return usuario;
    }
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public Curso() { }

    public Curso(String titulo, String descricao, Date stamp, int usuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.stamp = stamp;
        this.usuario = usuario;
    }

    public Curso(int id, String titulo, String descricao, Date stamp, int usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.stamp = stamp;
        this.usuario = usuario;
    }
}
