package com.example.ufcgram.model;

import com.example.ufcgram.service.CadastroPublicacao;

import java.time.LocalDate;

//Classe PublicacaoTexto: publicação que contém apenas um texto.

public class PublicacaoTexto extends Publicacao {
    private String texto;

    //Construtor sem parâmetro:

    public PublicacaoTexto() {
        super();
    }

    //Construtor para criar uma publicação nova:

    public PublicacaoTexto(int matriculaCriador, String texto) {
        super(matriculaCriador);
        this.texto = texto;
    }

    //Construtor para montar uma publicação que veio do banco de dados:

    public PublicacaoTexto(int idPublicacao, String tipo, int matriculaCriador, LocalDate dataHoraCriacao, String texto) {
        super(idPublicacao, tipo, matriculaCriador, dataHoraCriacao);
        this.texto = texto;
    }

    //Passa a publicação para o Service, que valida e salva

    public void criarPublicacaoTexto() {
        CadastroPublicacao service = new CadastroPublicacao();
        service.criarPublicacaoTexto(this);
    }

    //Passar a publicação para o Service

    public void editarPublicacaoTexto() {
        CadastroPublicacao service = new CadastroPublicacao();
        service.editarPublicacaoTexto(this);
    }

    @Override
    public void criarPublicacao() {
        criarPublicacaoTexto();
    }

    @Override
    public void editarPublicacao() {
        editarPublicacaoTexto();
    }

    //Getter

    public String getTexto() {
        return texto;
    }

    //Setter

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
