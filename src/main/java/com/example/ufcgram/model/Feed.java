package com.example.ufcgram.model;

import com.example.ufcgram.repository.ContaDAO;
import com.example.ufcgram.service.Consulta;

import java.util.ArrayList;

public class Feed {
    private Usuario usuarioSessao;
    private ArrayList<Usuario> usuariosRecomendados = new ArrayList<>();
    private ArrayList<Publicacao> publicacoesRecomendadas = new ArrayList<>();

    //Construtor sem parâmetro:

    public Feed() {
    }

    // Construtor com parâmetro:

    public Feed(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
        recomendarContas();
    }

    public void recomendarContas(){
         usuariosRecomendados = new Consulta().recomendarContas(usuarioSessao.getMatricula());
    }

    public Usuario consultarUsuarioPorMatriucula(int matricula) { return new Consulta().buscarContaPorMatricula(matricula);}
    public ArrayList<Usuario> consultarUsuarioPorNome(String nome) { return new Consulta().buscarContaPorNome(nome);}

    public ArrayList<Usuario> getUsuariosRecomendados() { return  usuariosRecomendados; }

    //Pedir ao Service as publicações recomendadas (guarda em postsRecomendados e retorna a lista)

    public ArrayList<Publicacao> recomendarPublicacoes() {
        Consulta service = new Consulta();
        publicacoesRecomendadas = service.recomendarPublicacoes(usuarioSessao.getMatricula());
        return publicacoesRecomendadas;
    }

    //Buscar no banco de dados a publicação que possui o id informado

    public Publicacao buscarPublicacao(int idPublicacao) {
        Consulta service = new Consulta();
        return service.buscarPublicacao(idPublicacao);
    }

    //Retornar true se a publicação está na lista de publicações recomendadas

    public boolean consultarPublicacao(Publicacao publicacao) {
        for (Publicacao post : publicacoesRecomendadas) {
            if (post.getIdPublicacao() == publicacao.getIdPublicacao()) {
                return true;
            }
        }
        return false;
    }

    //Retornar true se o usuário está na lista de contas recomendadas

    public boolean consultarUsuario(Usuario usuario) {
        for (Usuario conta : usuariosRecomendados) {
            if (conta.getMatricula() == usuario.getMatricula()) {
                return true;
            }
        }
        return false;
    }

    public void adicionarPublicacao(Publicacao publicacao) {
        publicacoesRecomendadas.add(publicacao);
    }

    public void removerPublicacao(Publicacao publicacao) {
        publicacoesRecomendadas.removeIf(post -> post.getIdPublicacao() == publicacao.getIdPublicacao());
    }

    public void adicionarUsuario(Usuario usuario) {
        usuariosRecomendados.add(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        usuariosRecomendados.removeIf(conta -> conta.getMatricula() == usuario.getMatricula());
    }

    //Getters

    public int getMatriculaUsuario() {
        return usuarioSessao.getMatricula();
    }
    public ArrayList<Usuario> getContasRecomendados() {
        return usuariosRecomendados;
    }
    public ArrayList<Publicacao> getPostsRecomendados() {
        return publicacoesRecomendadas;
    }

    //Setters

    public void setContasRecomendados(ArrayList<Usuario> usuariosRecomendados) {
        this.usuariosRecomendados = usuariosRecomendados;
    }
    public void setPostsRecomendados(ArrayList<Publicacao> publicacaoRecomendadas) {
        this.publicacoesRecomendadas = publicacaoRecomendadas;
    }

}
