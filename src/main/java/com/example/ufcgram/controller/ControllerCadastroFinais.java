package com.example.ufcgram.controller;

import com.example.ufcgram.model.Conta;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

public class ControllerCadastroFinais {

    private Conta conta;

    public void setConta(Conta conta){
        this.conta = conta;
    }

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
