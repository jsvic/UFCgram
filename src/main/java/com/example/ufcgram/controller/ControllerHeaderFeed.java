package com.example.ufcgram.controller;

import com.example.ufcgram.model.Conta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ControllerHeaderFeed {
    private Conta contaSessao;

    public void setContaSessao(Conta contaSessao) {
        System.out.printf("\nConta Sessao (HeaderFeed): %d", contaSessao.getMatr());
        this.contaSessao = contaSessao;
    }

    @FXML
    public void abrirConfiguracao(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewConfiguracao.fxml"
        )
        ); // Abre o arquivo FXMl

        Parent root = loader.load();

        System.out.printf("\nConta Sessao (HeaderFeed): %d", contaSessao.getMatr());
        ControllerConfiguracao controller = loader.getController(); // Pega o Controller do FXML
        controller.setContaSessao(contaSessao); // Passa o objeto Conta para o Controller

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Adicionar o FXMl à janela
    }

    @FXML
    public void abrirFeed(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewFeed.fxml"
        )
        ); // Abre o arquivo FXMl

        Parent root = loader.load();

        ControllerFeed controller = loader.getController(); // Pega o Controller do FXML
        controller.setContaSessao(contaSessao); // Passa o objeto Conta para o Controller

        Stage stage = (Stage) ((Node) mouseEvent.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Adicionar o FXMl à janela
    }
}
