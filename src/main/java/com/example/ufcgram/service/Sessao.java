package com.example.ufcgram.service;

public class Sessao {

    private static Conta contaSessao = new Conta();

    public void adicionarConta(Conta conta){
        contaSessao = conta;
    }

    public Conta getConta() {return contaSessao;}

    public void removerContaSessao() {
        contaSessao = null;
    }
}