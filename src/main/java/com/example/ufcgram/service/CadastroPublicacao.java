package com.example.ufcgram.service;

import com.example.ufcgram.model.PublicacaoEvento;
import com.example.ufcgram.model.PublicacaoTexto;
import com.example.ufcgram.repository.PublicacaoDAO;
import java.time.LocalDate;

//Classe responsável pelas ações principais das publicações (criar, editar e excluir)
//e pela comunicação com o banco de dados. As validações ficam na classe ValidacaoPublicacao.

public class CadastroPublicacao {

    //Instanciar para se comunicar com o banco de dados

    private PublicacaoDAO dao = new PublicacaoDAO();

    //Instanciar para validar os dados antes de enviar ao banco de dados

    private ValidacaoPublicacao validacao = new ValidacaoPublicacao();

    //Metodo chamado pelo Model

    public void criarPublicacaoTexto(PublicacaoTexto publicacao) {

        validacao.validarTexto(publicacao);

        //A data de criação é definida pelo sistema
        publicacao.setDataHoraCriacao(LocalDate.now());

        dao.criarTexto(publicacao);
    }

    //Metodo chamado pelo Model

    public void criarPublicacaoEvento(PublicacaoEvento publicacao) {

        validacao.validarNovoEvento(publicacao);

        //A data de criação é definida pelo sistema
        publicacao.setDataHoraCriacao(LocalDate.now());

        dao.criarEvento(publicacao);
    }

    //Metodo chamado pelo Model

    public void editarPublicacaoTexto(PublicacaoTexto publicacao) {

        validacao.validarTexto(publicacao);
        validacao.validarIdPublicacao(publicacao.getIdPublicacao());

        dao.editarTexto(publicacao);
    }

    //Metodo chamado pelo Model

    public void editarPublicacaoEvento(PublicacaoEvento publicacao) {

        validacao.validarEvento(publicacao);
        validacao.validarIdPublicacao(publicacao.getIdPublicacao());

        dao.editarEvento(publicacao);
    }

    //Metodo chamado pelo Model

    public void excluirPublicacao(int idPublicacao, int matriculaCriador) {

        validacao.validarIdentificacao(idPublicacao, matriculaCriador);

        dao.excluir(idPublicacao, matriculaCriador);
    }
}