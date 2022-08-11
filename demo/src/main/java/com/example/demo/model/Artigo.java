package com.example.demo.model;

import java.sql.Date;

public class Artigo {
    private int     id;
    private String  title;
    private String  text;
    private Date    stamp;
    private int     usuario;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
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

    public Artigo() { }

    public Artigo(String title, String text, Date stamp, int usuario) {
        this.title = title;
        this.text = text;
        this.stamp = stamp;
        this.usuario = usuario;
    }

    public Artigo(int id, String title, String text, Date stamp, int usuario) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.stamp = stamp;
        this.usuario = usuario;
    }
}
