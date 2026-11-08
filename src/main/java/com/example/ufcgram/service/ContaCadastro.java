package com.example.ufcgram.service;

import com.example.ufcgram.model.Conta;

public class ContaCadastro {
    private Conta contaCadastro = new Conta();

    public Conta getContaCadastro() {
        // Com esse metodo, adicione as informacoes dos inputs por meio do set da classe Conta e chame ela em Cadastro para fazer o insert
        return contaCadastro;
    }
}
