package com.example.ufcgram.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

public class ControllerCadastroFinais {

    @FXML private TextArea inDescricao;
    @FXML private PasswordField inSenha;
    @FXML private PasswordField inConfirmaSenha;

    @FXML
    public void cadastrar() {
        String bio, senha, confirmarSenha;

        bio = inDescricao.getText();
        senha = inSenha.getText();
        confirmarSenha = inConfirmaSenha.getText();

        System.out.printf("\nInformações:\nbio: %s\nsenha: %s\nconfirmar senha: %s", bio, senha, confirmarSenha);

    }
}
