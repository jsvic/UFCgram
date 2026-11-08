package com.example.ufcgram.model;

import java.util.ArrayList;

public class Conta {
    private int matr;
    private String nome;
    private String senha;
    private String email;
    private int idade;
    private String curso;
    private String campus;
    private String bio;
    private String semestreInicial;
    private String genero;
    private ArrayList<Conta> seguidores = new ArrayList<>();
    private ArrayList<Conta> seguindos = new ArrayList<>();
    private ArrayList<Post> postsCriados = new ArrayList<>();
    private static int qtdContas;

    public Conta() {
        qtdContas++;
    }

    public Conta(int matr, String nome, String senha, String email,
                 int idade, String curso, String campus, String bio,
                 String semestreInicial, String genero) {
        this();
        this.matr = matr;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.idade = idade;
        this.curso = curso;
        this.campus = campus;
        this.bio = bio;
        this.semestreInicial = semestreInicial;
        this.genero = genero;
    }

    // Get

    public int getMatr() {
        return matr;
    }

    public String getGenero() {
        return genero;
    }

    public String getCurso() {
        return curso;
    }

    public String getBio() {
        return bio;
    }

    public String getNome() {
        return nome;
    }

    public String getSemestreInicial() {
        return semestreInicial;
    }

    public int getIdade() {
        return idade;
    }

    public String getSenha() {
        return senha;
    }

    public String getCampus() {
        return campus;
    }
    public String getEmail() {
        return email;
    }

    // Set

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatr(int matr) {
        this.matr = matr;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSemestreInicial(String semestreInicial) {
        this.semestreInicial = semestreInicial;
    }
}
