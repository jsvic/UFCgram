package com.example.ufcgram.service;

import com.example.ufcgram.model.Conta;
import com.example.ufcgram.repository.ContaDAO;
import com.example.cursojavafx.security.SecuritySenha;

import java.util.Objects;

public class Login {

    ContaDAO contaDAO = new ContaDAO();
    Sessao sessao = new Sessao();
    SecuritySenha security = new SecuritySenha();

    public boolean login(int matr, String senha) {
        Conta conta;

        conta = contaDAO.getContabyMatr(matr);

        if(conta != null) {
            //if(security.checar(senha, conta.getSenha())) {

              if(Objects.equals(senha, conta.getSenha())){
                sessao.adicionarConta(conta);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
