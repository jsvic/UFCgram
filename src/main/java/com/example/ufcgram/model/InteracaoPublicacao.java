package com.example.ufcgram.model;

import java.time.LocalDate;

//Classe Mãe InteracaoPublicacao: dados comuns a uma curtida e a um comentário.
//Curtida e Comentario herdam esta classe.

public abstract class InteracaoPublicacao {
    protected LocalDate dataHoraInteracao;
    protected int matriculaUsuario;
    protected int idPublicacao;

    //Construtor sem parâmetro:
    public InteracaoPublicacao() {
    }

    //Construtor para criar uma interação nova (a data é definida ao salvar):
    public InteracaoPublicacao(int matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    //Construtor para montar uma interação que veio do banco de dados:
    public InteracaoPublicacao(int matriculaUsuario, int idPublicacao, LocalDate dataHoraInteracao) {
        this.matriculaUsuario = matriculaUsuario;
        this.idPublicacao = idPublicacao;
        this.dataHoraInteracao = dataHoraInteracao;
    }

    //Getters

    public LocalDate getDataHoraInteracao() {
        return dataHoraInteracao;
    }
    public int getMatriculaUsuario() {
        return matriculaUsuario;
    }
    public int getIdPublicacao() {
        return idPublicacao;
    }

    //Setters

    public void setDataHoraInteracao(LocalDate dataHoraInteracao) {
        this.dataHoraInteracao = dataHoraInteracao;
    }
    public void setMatriculaUsuario(int matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }
    public void setIdPublicacao(int idPublicacao) {
        this.idPublicacao = idPublicacao;
    }
}