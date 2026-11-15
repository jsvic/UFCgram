package com.example.ufcgram.controller;

import com.example.ufcgram.model.Conta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerConfiguracao {
    private Conta contaSessao;

    @FXML private ControllerHeaderFeed headerFeedController;

    public void setContaSessao(Conta contaSessao) {
        this.contaSessao = contaSessao;
        headerFeedController.setContaSessao(contaSessao);
        System.out.printf("\nConta Sessao (Configuracao): %d", contaSessao.getMatr());
    }

    @FXML
    public void abrirFeed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewFeed.fxml"
        )
        ); // Abre o arquivo FXMl

        Parent root = loader.load();

        System.out.printf("\nConta Sessao (Configuracao): %d", contaSessao.getMatr());
        ControllerFeed controller = loader.getController(); // Pega o Controller do FXML
        controller.setContaSessao(contaSessao); // Passa o objeto Conta para o Controller

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Adicionar o FXMl à janela
    }
}
