package com.example.ufcgram.service;

import com.example.ufcgram.model.Comentario;
import com.example.ufcgram.model.Curtida;
import com.example.ufcgram.model.PublicacaoEvento;
import com.example.ufcgram.model.PublicacaoTexto;
import java.time.LocalDate;

//Classe responsável por validar os dados de publicações, curtidas e comentários antes de enviar ao banco de dados.
//As mensagens seguem o padrão "Titulo: mensagem"

public class ValidacaoPublicacao {

    //Instanciar para validar a matrícula

    private ContaCadastro contaCadastro = new ContaCadastro();

    //Publicações

    public void validarTexto(PublicacaoTexto publicacao) {

        if (publicacao == null) {
            throw new RuntimeException("Erro de validação: Publicação inválida");
        }

        contaCadastro.validarMatricula(publicacao.getMatriculaCriador());

        if (vazio(publicacao.getTexto()) || publicacao.getTexto().length() > 500) {
            throw new RuntimeException("Erro de validação: Escreva um texto de até 500 caracteres");
        }
    }

    public void validarEvento(PublicacaoEvento publicacao) {

        if (publicacao == null) {
            throw new RuntimeException("Erro de validação: Publicação inválida");
        }

        contaCadastro.validarMatricula(publicacao.getMatriculaCriador());

        if (vazio(publicacao.getTitulo()) || publicacao.getTitulo().length() > 100) {
            throw new RuntimeException("Erro de validação: Informe um título de até 100 caracteres");
        }

        if (vazio(publicacao.getDescricao()) || publicacao.getDescricao().length() > 500) {
            throw new RuntimeException("Erro de validação: Informe uma descrição de até 500 caracteres");
        }

        if (vazio(publicacao.getLocal()) || publicacao.getLocal().length() > 100) {
            throw new RuntimeException("Erro de validação: Informe um local de até 100 caracteres");
        }

        if (publicacao.getDataEvento() == null) {
            throw new RuntimeException("Erro de validação: Informe a data do evento");
        }
    }

    //Um evento novo não pode ser marcado para uma data que já passou:

    public void validarNovoEvento(PublicacaoEvento publicacao) {

        validarEvento(publicacao);

        if (publicacao.getDataEvento().isBefore(LocalDate.now())) {
            throw new RuntimeException("Erro de validação: A data do evento não pode ser anterior a hoje");
        }
    }

    //Validar o id da publicação e a matrícula do criador (usado ao editar e excluir):

    public void validarIdentificacao(int idPublicacao, int matriculaCriador) {

        validarIdPublicacao(idPublicacao);
        contaCadastro.validarMatricula(matriculaCriador);
    }

    public void validarIdPublicacao(int idPublicacao) {

        if (idPublicacao <= 0) {
            throw new RuntimeException("Erro de validação: Publicação inválida");
        }
    }

    public void validarCurtida(Curtida curtida) {

        if (curtida == null) {
            throw new RuntimeException("Erro de validação: Curtida inválida");
        }

        contaCadastro.validarMatricula(curtida.getMatriculaUsuario());
        validarIdPublicacao(curtida.getIdPublicacao());
    }

    public void validarComentario(Comentario comentario) {

        if (comentario == null) {
            throw new RuntimeException("Erro de validação: Comentário inválido");
        }

        contaCadastro.validarMatricula(comentario.getMatriculaUsuario());
        validarIdPublicacao(comentario.getIdPublicacao());

        if (vazio(comentario.getTexto()) || comentario.getTexto().length() > 300) {
            throw new RuntimeException("Erro de validação: Escreva um comentário de até 300 caracteres");
        }
    }

    //Metodo intermediário: retornar true se o texto for nulo ou estiver em branco

    private boolean vazio(String texto) {
        return texto == null || texto.isBlank();
    }
}
