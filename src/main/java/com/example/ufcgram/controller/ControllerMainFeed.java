package com.example.ufcgram.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ControllerMainFeed {
    @FXML private VBox mainFeed;
    @FXML private ControllerModalComentarios modalComentarios;

    private boolean status = true;

    @FXML public void setController(ControllerModalComentarios modalComentarios) {
        this.modalComentarios = modalComentarios;
    }

    @FXML public void alterarStatus() {
        status = !status;

        mainFeed.setManaged(status);
        mainFeed.setVisible(status);
    }

    @FXML public void fechar() {
        status = false;

        mainFeed.setManaged(status);
        mainFeed.setVisible(status);
    }

    @FXML public void abrir() {
        status = true;

        mainFeed.setManaged(status);
        mainFeed.setVisible(status);
    }

    public boolean getStatus() {
        return status;
    }
}

