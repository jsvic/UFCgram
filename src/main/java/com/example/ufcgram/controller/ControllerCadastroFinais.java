package com.example.ufcgram.controller;

import com.example.ufcgram.model.Conta;
import com.example.ufcgram.service.Cadastro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.Objects;

public class ControllerCadastroFinais {

    @FXML private TextArea inDescricao; // Atributo que referencia o campo Descricao
    @FXML private PasswordField inSenha; // Atributo que referencia o campo Senha
    @FXML private PasswordField inConfirmaSenha; // Atributo que referencia o campo Confirmar Senha

    @FXML
    public void cadastrar() {
        // Metodo chamado ao clicar no botao "Cadastrar"

        String bio, senha, confirmarSenha;

        bio = inDescricao.getText(); // Pega o valor do campo Descricao
        senha = inSenha.getText(); // Pega o valor do campo Senha
        confirmarSenha = inConfirmaSenha.getText(); // Pega o valor do campo Confirmar Senha

                System.out.println("Processo de cadastro finalizado com sucesso!");

            } catch (Exception e) {
                System.out.println("Ocorreu um erro ao realizar o cadastro.");
            }
    }
}
