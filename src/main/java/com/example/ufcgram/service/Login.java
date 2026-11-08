package com.example.ufcgram.service;

import com.example.ufcgram.model.Conta;
import com.example.ufcgram.repository.ContaDAO;
import com.example.cursojavafx.security.SecuritySenha;

import java.util.Objects;

public class Login {

    ContaDAO contaDAO = new ContaDAO();
    Sessao sessao = new Sessao();
    SecuritySenha security = new SecuritySenha();

    public void login(int matr, String senha) {
        Conta conta;

        //if(security.checar(senha, conta.getSenha())) {

        try {
            conta = contaDAO.getContabyMatr(matr);
            if(Objects.equals(senha, conta.getSenha())){
                try {
                    sessao.adicionarConta(conta);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException("Senha incorreta");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
