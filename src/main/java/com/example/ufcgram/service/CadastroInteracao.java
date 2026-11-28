package com.example.ufcgram.service;

import com.example.ufcgram.model.Comentario;
import com.example.ufcgram.model.Curtida;
import com.example.ufcgram.repository.ComentarioDAO;
import com.example.ufcgram.repository.CurtidaDAO;
import java.time.LocalDate;

//Classe responsável pelas ações principais das interações (curtir, descurtir e comentar)
//e pela comunicação com o banco de dados. As validações ficam na classe ValidacaoPublicacao.

public class CadastroInteracao {

    //Instanciar para se comunicar com o banco de dados

    private CurtidaDAO curtidaDAO = new CurtidaDAO();
    private ComentarioDAO comentarioDAO = new ComentarioDAO();

    //Instanciar para validar os dados antes de enviar ao banco de dados

    private ValidacaoPublicacao validacao = new ValidacaoPublicacao();

    //Metodo chamado pelo Model

    public void curtir(Curtida curtida) {

        validacao.validarCurtida(curtida);

        //A data da interação é definida pelo sistema
        curtida.setDataHoraInteracao(LocalDate.now());

        curtidaDAO.inserir(curtida);
    }

    //Metodo chamado pelo Model (Curtida.removerCurtida)

    public void removerCurtida(Curtida curtida) {

        validacao.validarCurtida(curtida);

        curtidaDAO.remover(curtida.getIdPublicacao(), curtida.getMatriculaUsuario());
    }

    //Metodo chamado pelo Model (Comentario.comentarPublicacao)

    public void comentar(Comentario comentario) {

        validacao.validarComentario(comentario);

        //A data da interação é definida pelo sistema

        comentario.setDataHoraInteracao(LocalDate.now());

        comentarioDAO.inserir(comentario);
    }

    //Metodo chamado pelo Model (Comentario.editarComentario)

    public void editarComentario(Comentario comentario) {

        validacao.validarComentario(comentario);

        comentarioDAO.editar(comentario);
    }

    //Metodo chamado pelo Model (Comentario.removerComentario)

    public void removerComentario(Comentario comentario) {

        validacao.validarComentario(comentario);

        comentarioDAO.remover(comentario.getIdComentario());
    }
}