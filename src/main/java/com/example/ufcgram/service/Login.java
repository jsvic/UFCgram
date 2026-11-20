package com.example.ufcgram.service;

import com.example.ufcgram.model.Usuario;
import com.example.ufcgram.repository.ContaDAO;
import com.example.ufcgram.security.SecuritySenha;

public class Login {

    ContaDAO contaDAO = new ContaDAO(); //Chama a classe de interacao com o BD
    SecuritySenha security = new SecuritySenha(); //Chama a classe para o hash da senha

    public Usuario login(int matr, String senha) {

        //Busca o usuário pela matrícula no banco de dados.
        Usuario usuario = contaDAO.getContabyMatr(matr);

        //Verifica se a senha digitada corresponde ao hash armazenado.
        if (!security.checar(senha, usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        // Retorna o usuário caso a autenticação seja bem-sucedida.
        return usuario;
    }
}
