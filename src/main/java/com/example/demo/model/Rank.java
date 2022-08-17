package com.example.demo.model;

public class Rank {


    private int publicao;
    private String nome;
    private String level;



    public int getPublicao() {
        return publicao;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setPublicao(int publicao) {
        this.publicao = publicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Rank () {
    }

    public Rank(String nome, String level) {
        this.nome = nome;
        this.level = level;
    }

    public Rank(int publicao, String nome, String level) {
        this.publicao = publicao;
        this.nome = nome;
        this.level = level;
    }
}
