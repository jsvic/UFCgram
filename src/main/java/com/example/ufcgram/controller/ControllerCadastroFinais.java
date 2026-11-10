package com.example.ufcgram.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

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

        System.out.printf("\nInformações:\nbio: %s\nsenha: %s\nconfirmar senha: %s", bio, senha, confirmarSenha);

    }
}
