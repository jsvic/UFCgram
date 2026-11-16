package com.example.ufcgram.controller;

import atlantafx.base.controls.ModalPane;
import atlantafx.base.layout.ModalBox;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class ControllerModalDeletarConta {
    // Classe para configurar o elemento modal de excluir conta

    @FXML private ModalPane modalPane; // Referencia o fundo do modal
    @FXML private ModalBox modalBox; // Referencia o conteudo do modal
    @FXML private CheckBox inConfirmar; // Referencia o campo de confirmacao do modal do deletar conta

    @FXML public void initialize() {
        // Metodo que executa assim que o FXMl é iniciado

        modalBox.setOnClose((e) -> modalPane.hide());
    }

    @FXML public void abrirModal() {
        // Metodo chamado pelo botao excluir conta

        modalPane.show(modalBox);
    }

    @FXML public void deletar() {
        // Metodo chamado pelo botao excluir no modal
        boolean confirmacao;

        confirmacao = inConfirmar.isSelected(); // Retorna true caso o usuario selecione e false caso contrario

        System.out.printf("\nCampo selecionado: %b\n", confirmacao);
    }
}
