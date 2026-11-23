package com.example.ufcgram.model;

import com.example.ufcgram.repository.ContaDAO;
import com.example.ufcgram.service.Consulta;

import java.util.ArrayList;

public class Feed {
    private Usuario usuarioSessao;
    private ArrayList<Usuario> usuariosRecomendados;
    private ArrayList<Publicacao> publicacaoRecomendadas;

    public Feed(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
        recomendarContas();
    }

    public void recomendarContas(){
         usuariosRecomendados = new Consulta().recomendarContas(usuarioSessao);
    }

    public Usuario consultarUsuarioPorMatriucula(int matricula) { return new Consulta().buscarContaPorMatricula(matricula);}
    public ArrayList<Usuario> consultarUsuarioPorNome(String nome) { return new Consulta().buscarContaPorNome(nome);}

    public ArrayList<Usuario> getUsuariosRecomendados() { return  usuariosRecomendados; }


}
