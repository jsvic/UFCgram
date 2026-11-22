package com.example.ufcgram.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ControllerMainFeed {
    @FXML private VBox mainFeed;

    private boolean status;

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

    public boolean getStatus() {
        return status;
    }
}

