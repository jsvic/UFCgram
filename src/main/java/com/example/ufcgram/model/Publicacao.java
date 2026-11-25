package com.example.ufcgram.model;

import com.example.ufcgram.service.CadastroPublicacao;
import java.time.LocalDate;
import java.util.ArrayList;

//Classe Mãe Publicacao
//PublicacaoTexto e PublicacaoEvento herdam esta classe.

public abstract class Publicacao {
    protected int idPublicacao;
    protected int matriculaCriador;
    protected LocalDate dataHoraCriacao;
    protected ArrayList<Comentario> comentarios = new ArrayList<>();
    protected ArrayList<Curtida> curtidas = new ArrayList<>();

    //Construtor sem parâmetro:
    public Publicacao() {
    }

    //Construtor usado ao criar uma publicação nova (o id e a data também são definidos ao salvar):
    public Publicacao(int matriculaCriador) {
        this.matriculaCriador = matriculaCriador;
    }

    //Construtor usado para montar uma publicação que veio do banco de dados:
    public Publicacao(int idPublicacao, int matriculaCriador, LocalDate dataHoraCriacao) {
        this.idPublicacao = idPublicacao;
        this.matriculaCriador = matriculaCriador;
        this.dataHoraCriacao = dataHoraCriacao;
    }

    //Métodos abstratos:
    public abstract void criarPublicacao();

    public abstract void editarPublicacao();

    //Passar o id e o criador para o Service (só o criador pode excluir a própria publicação)
    public void excluirPublicacao() {
        CadastroPublicacao service = new CadastroPublicacao();
        service.excluirPublicacao(idPublicacao, matriculaCriador);
    }

    public int totalComentarios() {
        return comentarios.size();
    }

    public int totalCurtidas() {
        return curtidas.size();
    }

    //Retornar true se o usuário já comentou
    public boolean verificarComentario(Usuario usuario) {
        for (Comentario comentario : comentarios) {
            if (comentario.getMatriculaUsuario() == usuario.getMatricula()) {
                return true;
            }
        }
        return false;
    }

    //Retornar true se o usuário já curtiu
    public boolean verificarCurtida(Usuario usuario) {
        for (Curtida curtida : curtidas) {
            if (curtida.getMatriculaUsuario() == usuario.getMatricula()) {
                return true;
            }
        }
        return false;
    }

    //Getters

    public int getIdPublicacao() {
        return idPublicacao;
    }
    public int getMatriculaCriador() {
        return matriculaCriador;
    }
    public LocalDate getDataHoraCriacao() {
        return dataHoraCriacao;
    }
    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
    public ArrayList<Curtida> getCurtidas() {
        return curtidas;
    }

    //Setters

    public void setIdPublicacao(int idPublicacao) {
        this.idPublicacao = idPublicacao;
    }
    public void setMatriculaCriador(int matriculaCriador) {
        this.matriculaCriador = matriculaCriador;
    }
    public void setDataHoraCriacao(LocalDate dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }
    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    public void setCurtidas(ArrayList<Curtida> curtidas) {
        this.curtidas = curtidas;
    }
}
