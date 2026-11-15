package com.example.ufcgram.service;

import com.example.ufcgram.model.Conta;
import com.example.ufcgram.repository.ContaDAO;
import com.example.ufcgram.security.SecuritySenha;

import java.util.Objects;

public class Login {

    ContaDAO contaDAO = new ContaDAO(); // Chama a classe de interacao com o BD
    SecuritySenha security = new SecuritySenha(); // Chama a classe para o hash da senha

    public Conta login(int matr, String senha) {
        Conta conta;

        //if(security.checar(senha, conta.getSenha())) {

        try {
            conta = contaDAO.getContabyMatr(matr); // Pega os dados da conta no BD
            if(Objects.equals(senha, conta.getSenha())){ // Verifica se a senha digita é a correta
                return conta;  // Caso esteja tudo correto, retorna a Conta para iniciar a sessao
            } else {
                throw new RuntimeException("Senha incorreta"); // Caso a senha esteja errada
            }
        } catch (Exception e) {
            throw new RuntimeException(e); // Caso de algum erro no BD
        }

    }

}
