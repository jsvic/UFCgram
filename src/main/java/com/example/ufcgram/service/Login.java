package com.example.ufcgram.service;

import com.example.ufcgram.model.Conta;
import com.example.ufcgram.repository.ContaDAO;
import com.example.ufcgram.security.SecuritySenha;

import java.util.Objects;

public class Login {
    // Classe para fazer o Login do usuario

    ContaDAO contaDAO = new ContaDAO();
    Sessao sessao = new Sessao();
    com.example.ufcgram.security.SecuritySenha security = new SecuritySenha();

    public void login(int matr, String senha) {
        // Metodo chamado no Controller do Login

        //if(security.checar(senha, conta.getSenha())) {

        try {
            Conta conta = contaDAO.getContabyMatr(matr); // Pega a conta no BD, passando a matricula
            if(conta != null) {
                System.out.printf("\nMatr: %d\nSenha: %s\ngetSenha: %s", matr, senha, conta.getSenha());
                if(Objects.equals(senha, conta.getSenha())){ // Verifica se a senha esta correta
                    sessao.adicionarConta(conta);
                    // No futuro esse metodo retornara a Conta para adicionar em sessao
                } else {
                    throw new RuntimeException("Senha incorreta"); // Caso a senha esteja errada
                }
            } else {
                throw new RuntimeException("Usuário não encontrado"); // Caso nao exista usuario com essa matricula
            }
        } catch (Exception e) {
            throw new RuntimeException(e); // Caso de erro de conexao no BD
        }

    }

}
