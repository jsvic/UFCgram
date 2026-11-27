package com.example.ufcgram.model;

import com.example.ufcgram.service.CadastroPublicacao;

import java.time.LocalDate;

//Classe PublicacaoEvento: publicação que divulga um evento.

public class PublicacaoEvento extends Publicacao {
    private LocalDate dataEvento;
    private String descricao;
    private String local;
    private String titulo;

    //Construtor sem parâmetro:

    public PublicacaoEvento() {
        super();
    }

    //Construtor para criar uma publicação nova:

    public PublicacaoEvento(int matriculaCriador, String titulo, String descricao,
                            String local, LocalDate dataEvento) {
        super(matriculaCriador);
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.dataEvento = dataEvento;
    }

    //Construtor para uma publicação que veio do banco de dados:

    public PublicacaoEvento(int idPublicacao, String tipo, int matriculaCriador, LocalDate dataHoraCriacao, String titulo, String descricao, String local, LocalDate dataEvento) {
        super(idPublicacao, tipo, matriculaCriador, dataHoraCriacao);
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.dataEvento = dataEvento;
    }

    //Passar a publicação para o Service, que valida e salva

    public void criarPublicacaoEvento() {
        CadastroPublicacao service = new CadastroPublicacao();
        service.criarPublicacaoEvento(this);
    }

    //Passa a publicação para o Service

    public void editarPublicacaoEvento() {
        CadastroPublicacao service = new CadastroPublicacao();
        service.editarPublicacaoEvento(this);
    }

    @Override
    public void criarPublicacao() {
        criarPublicacaoEvento();
    }

    @Override
    public void editarPublicacao() {
        editarPublicacaoEvento();
    }

    //Getters

    public LocalDate getDataEvento() {
        return dataEvento;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getLocal() {
        return local;
    }
    public String getTitulo() {
        return titulo;
    }

    //Setters

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}