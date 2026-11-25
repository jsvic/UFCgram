package com.example.ufcgram.model;

import com.example.ufcgram.service.CadastroInteracao;
import com.example.ufcgram.service.Consulta;

import java.time.LocalDate;
import java.util.ArrayList;

//Classe Curtida: curtida de um usuário em uma publicação.
//Cada usuário pode curtir uma publicação apenas uma vez.

public class Curtida extends InteracaoPublicacao {

    //Construtor sem parâmetro:

    public Curtida() {
        super();
    }

    //Construtor para criar uma curtida nova:

    public Curtida(int matriculaUsuario) {
        super(matriculaUsuario);
    }

    //Construtor para montar uma curtida que veio do banco de dados:

    public Curtida(int matriculaUsuario, int idPublicacao, LocalDate dataHoraInteracao) {
        super(matriculaUsuario, idPublicacao, dataHoraInteracao);
    }

    public void curtirPublicacao(Publicacao publicacao) {
        idPublicacao = publicacao.getIdPublicacao();
        //Passa a curtida para o Service, que valida e salva
        CadastroInteracao service = new CadastroInteracao();
        service.curtir(this);
        //Se o Service não der erro, atualiza a lista de curtidas da publicação
        publicacao.getCurtidas().add(this);
    }

    public void removerCurtida(Publicacao publicacao) {
        idPublicacao = publicacao.getIdPublicacao();
        //Passar a curtida para o Service, que valida e remove
        CadastroInteracao service = new CadastroInteracao();
        service.removerCurtida(this);
        //Se o Service não der erro, retira a curtida da lista da publicação
        publicacao.getCurtidas().removeIf(curtida -> curtida.getMatriculaUsuario() == matriculaUsuario);
    }

    public ArrayList<Curtida> recuperarCurtidas() {
        //Retorna todas as curtidas feitas pelo usuário desta curtida
        Consulta service = new Consulta();
        return service.recuperarCurtidas(matriculaUsuario);
    }
}