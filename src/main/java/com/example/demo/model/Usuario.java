package com.example.demo.model;

import java.sql.Date;

public class Usuario {
    private int       id;
    private String    nome;
    private String    email;
    private String    senha;
    private Date      stamp;
    private boolean   status;
    private Permissao permissao;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getStamp() {
        return stamp;
    }
    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public Permissao getPermissao() {
        return permissao;
    }
    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public Usuario() {}

    public Usuario(String nome, String email, String senha, Date stamp, boolean status, Permissao permissao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.stamp = stamp;
        this.status = status;
        this.permissao = permissao;
    }

    public Usuario(int id, String nome, String email, String senha, Date stamp, boolean status, Permissao permissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.stamp = stamp;
        this.status = status;
        this.permissao = permissao;
    }
}
