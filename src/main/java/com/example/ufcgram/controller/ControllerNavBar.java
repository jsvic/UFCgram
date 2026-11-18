package com.example.ufcgram.controller;


import com.example.ufcgram.model.Conta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerNavBar {

    @FXML private ControllerMainFeed mainFeedController;
    @FXML private ControllerConta contaController;

    private Conta contaSessao;

    public void setContaSessao(Conta contaSessao) {
        this.contaSessao = contaSessao;
    }

    @FXML public void setMainFeedController(ControllerMainFeed controller) {
        mainFeedController = controller;
    }

    @FXML public void setContaController(ControllerConta controlller) {
        contaController = controlller;
    }

    @FXML
    public void abrirContaSessao() {
/*
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/parcials/conta.fxml"
        )
        ); // Abre o arquivo FXMl

        Parent root = loader.load();

        System.out.printf("\nConta Sessao (HeaderFeed): %d", contaSessao.getMatr());
        ControllerConta controller = loader.getController(); // Pega o Controller do FXML
        controller.setContaSessao(contaSessao); // Passa o objeto Conta para o Controller
        controller.setContaPerfil(contaSessao);

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Adicionar o FXMl à janela
    }
 */

        mainFeedController.fechar();
        contaController.abrir();
        contaController.setContas(contaSessao, contaSessao);
    }
}
