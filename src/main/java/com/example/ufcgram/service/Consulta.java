package com.example.ufcgram.service;
import com.example.ufcgram.model.Comentario;
import com.example.ufcgram.model.Curtida;
import com.example.ufcgram.model.Publicacao;
import com.example.ufcgram.model.Usuario;
import com.example.ufcgram.repository.ComentarioDAO;
import com.example.ufcgram.repository.ContaDAO;
import com.example.ufcgram.repository.CurtidaDAO;
import com.example.ufcgram.repository.PublicacaoDAO;

import java.util.ArrayList;

//Classe responsável pelas consultas de usuários no feed

public class Consulta {
    private ContaDAO dao = new ContaDAO();
    private PublicacaoDAO publicacaoDAO = new PublicacaoDAO();
    private CurtidaDAO curtidaDAO = new CurtidaDAO();
    private ComentarioDAO comentarioDAO = new ComentarioDAO();

    //Instanciar para validar os dados antes de consultar o banco de dados
    private ContaCadastro contaCadastro = new ContaCadastro();
    private ValidacaoPublicacao validacao = new ValidacaoPublicacao();

    public ArrayList<Usuario> recomendarContas(int matricula) {

        int atributoMinimo = 1;
        int limiteContas = 10;

        contaCadastro.validarMatricula(matricula);

        //Buscar os dados do usuário (curso, campus e semestre inicial)
        Usuario usuario = dao.getContabyMatr(matricula);
        return dao.recomendar(matricula,
                usuario.getCurso(),
                usuario.getCampus(),
                usuario.getSemestreInicial(),
                atributoMinimo,
                limiteContas
        );
    }

    public Usuario buscarContaPorMatricula(int matricula) {
        return dao.getContabyMatr(matricula);
    }

    public ArrayList<Usuario> buscarContaPorNome(String nome) {
        return dao.getContabyNome(nome);
    }

    //Recomendar as publicações mais recentes das contas que o usuário segue

    public ArrayList<Publicacao> recomendarPublicacoes(int matricula) {

        //Regra da recomendação de publicações
        int limite = 20;  //Quantidade máxima de publicações recomendadas

        contaCadastro.validarMatricula(matricula);

        ArrayList<Publicacao> publicacoes = publicacaoDAO.recomendar(matricula, limite);
        carregarInteracoes(publicacoes);

        return publicacoes;
    }

    //Buscar uma publicação pelo id (com as suas curtidas e comentários)

    public Publicacao buscarPublicacao(int idPublicacao) {

        validacao.validarIdPublicacao(idPublicacao);

        Publicacao publicacao = publicacaoDAO.buscarPorId(idPublicacao);
        carregarInteracoes(publicacao);

        return publicacao;
    }

    //Recupera as publicações criadas pelo usuário (da mais recente para a mais antiga)

    public ArrayList<Publicacao> recuperarPublicacoes(int matricula) {

        contaCadastro.validarMatricula(matricula);

        ArrayList<Publicacao> publicacoes = publicacaoDAO.recuperarPorUsuario(matricula);
        carregarInteracoes(publicacoes);

        return publicacoes;
    }

    //Recuperar as curtidas feitas pelo usuário

    public ArrayList<Curtida> recuperarCurtidas(int matricula) {

        contaCadastro.validarMatricula(matricula);

        return curtidaDAO.recuperarPorUsuario(matricula);
    }

    //Recuperar os comentários feitos pelo usuário

    public ArrayList<Comentario> recuperarComentarios(int matricula) {

        contaCadastro.validarMatricula(matricula);

        return comentarioDAO.recuperarPorUsuario(matricula);
    }

    public ArrayList<Publicacao> recuperarPublicacoesCurtidas(int matr) {
        contaCadastro.validarMatricula(matr);

        ArrayList<Publicacao> publicacoes = publicacaoDAO.recuperarPublicacoesCurtidas(matr);

        carregarInteracoes(publicacoes);

        return publicacoes;
    }

    public ArrayList<Publicacao> recuperarPublicacoesComentadas(int matr) {
        contaCadastro.validarMatricula(matr);

        ArrayList<Publicacao> publicacoes = publicacaoDAO.recuperarPublicacoesComentadas(matr);

        carregarInteracoes(publicacoes);

        return publicacoes;
    }

    //Métodos intermediários
    //Carregar as curtidas e os comentários de uma publicação
    private void carregarInteracoes(Publicacao publicacao) {
        publicacao.setCurtidas(curtidaDAO.recuperarPorPublicacao(publicacao.getIdPublicacao()));
        publicacao.setComentarios(comentarioDAO.recuperarPorPublicacao(publicacao.getIdPublicacao()));
    }
    //Carregar as curtidas e os comentários de cada publicação da lista
    private void carregarInteracoes(ArrayList<Publicacao> publicacoes) {
        for (Publicacao publicacao : publicacoes) {
            carregarInteracoes(publicacao);
        }
    }
}

