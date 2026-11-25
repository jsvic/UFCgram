package com.example.ufcgram.model;

//imports

import com.example.ufcgram.service.CadastroInteracao;
import com.example.ufcgram.service.Consulta;
import java.time.LocalDate;
import java.util.ArrayList;

//Classe Comentario: comentário de um usuário em uma publicação.
//Cada usuário pode ter apenas um comentário por publicação

public class Comentario extends InteracaoPublicacao {
    private String texto;

    // Construtor sem parâmetro:

    public Comentario() {
        super();
    }

    // Construtor para criar um comentário novo:

    public Comentario(int matriculaUsuario, String texto) {
        super(matriculaUsuario);
        this.texto = texto;
    }

    // Construtor para um comentário que veio do banco de dados:

    public Comentario(int matriculaUsuario, int idPublicacao, LocalDate dataHoraInteracao, String texto) {
        super(matriculaUsuario, idPublicacao, dataHoraInteracao);
        this.texto = texto;
    }

    public void comentarPublicacao(Publicacao publicacao) {
        idPublicacao = publicacao.getIdPublicacao();

        //Passar o comentário para o Service, que valida e salva
        CadastroInteracao service = new CadastroInteracao();
        service.comentar(this);
        //Se o Service nao lancou erro, atualiza a lista de comentários da publicação
        publicacao.getComentarios().add(this);
    }

    public void editarComentario(Publicacao publicacao) {
        idPublicacao = publicacao.getIdPublicacao();
        //Passa o comentário para o Service, que valida e atualiza
        CadastroInteracao service = new CadastroInteracao();
        service.editarComentario(this);
        //Se o Service nao lancou erro, atualiza o texto do comentário na lista da publicação
        for (Comentario comentario : publicacao.getComentarios()) {
            if (comentario.getMatriculaUsuario() == matriculaUsuario) {
                comentario.setTexto(texto);
            }
        }
    }

    public Publicacao removerComentario(Publicacao publicacao) {
        idPublicacao = publicacao.getIdPublicacao();
        //Passa o comentário para o Service, que valida e remove
        CadastroInteracao service = new CadastroInteracao();
        service.removerComentario(this);
        //Se o Service nao lancou erro, retira o comentário da lista e retorna a publicação atualizada
        publicacao.getComentarios().removeIf(comentario -> comentario.getMatriculaUsuario() == matriculaUsuario);
        return publicacao;
    }

    public ArrayList<Comentario> recuperarComentarios() {
        //Retorna todos os comentários feitos pelo usuário deste comentário
        Consulta service = new Consulta();
        return service.recuperarComentarios(matriculaUsuario);
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