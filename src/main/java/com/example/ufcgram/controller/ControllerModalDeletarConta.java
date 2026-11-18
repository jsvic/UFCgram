package com.example.ufcgram.controller;

import atlantafx.base.controls.ModalPane;
import atlantafx.base.layout.ModalBox;
import com.example.ufcgram.model.Conta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerModalDeletarConta {
    // Classe para configurar o elemento modal de excluir conta

    @FXML private ModalPane modalPane; // Referencia o fundo do modal
    @FXML private ModalBox modalBox; // Referencia o conteudo do modal
    @FXML private CheckBox inConfirmar; // Referencia o campo de confirmacao do modal do deletar conta

    private Conta contaSessao;

    @FXML public void initialize() {
        // Metodo que executa assim que o FXMl é iniciado

        modalBox.setOnClose((e) -> modalPane.hide());
    }

    public void setContaSessao(Conta contaSessao) {
        this.contaSessao = contaSessao;
    }

    @FXML
    public void abrirLogin(ActionEvent event) throws IOException {
        // Redereciona para o Login

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewLogin.fxml"
        )
        ); // Abre o arquivo FXMl

        Parent root = loader.load();

        System.out.printf("\nConta Sessao (Modal Deletar): %d", contaSessao.getMatr());
        ControllerLogin controller = loader.getController(); // Pega o Controller do FXML
        controller.abrirMensagemExcluir(); // Passa o objeto Conta para o Controller

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Adicionar o FXMl à janela
    }

    @FXML public void abrirModal() {
        // Metodo chamado pelo botao excluir conta

        modalPane.show(modalBox);
    }

    @FXML public void deletar(ActionEvent event) throws IOException {
        // Metodo chamado pelo botao excluir no modal
        boolean confirmacao;

        confirmacao = inConfirmar.isSelected(); // Retorna true caso o usuario selecione e false caso contrario

        System.out.printf("\nCampo selecionado: %b\n", confirmacao);
        abrirLogin(event);
    }
}
