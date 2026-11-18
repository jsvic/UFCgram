package com.example.ufcgram.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ControllerMainFeed {
    @FXML private VBox mainFeed;

    @FXML public void abrir() {
        mainFeed.setManaged(true);
        mainFeed.setVisible(true);
    }

    @FXML public void fechar() {
        mainFeed.setManaged(false);
        mainFeed.setVisible(false);
    }
}

