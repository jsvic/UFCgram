package com.example.ufcgram.controller;


import com.example.ufcgram.model.Conta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerNavBar {

    @FXML private ControllerMainFeed mainFeedController;
    @FXML private ControllerConta contaController;

    @FXML private Button navButton;

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
        if(contaController.getStatus()) {
            navButton.setText("Ver Conta");
        } else {
            navButton.setText("Feed");
            contaController.setContas(contaSessao, contaSessao);
        }
        mainFeedController.alterarStatus();
        contaController.alterarStatus();
        contaController.setContas(contaSessao, contaSessao);
    }
}
