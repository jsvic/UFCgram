package com.example.ufcgram.controller;

import com.example.ufcgram.model.Conta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerFeed {
    private Conta contaSessao;
    @FXML private ControllerHeaderFeed headerFeedController;
    @FXML private ControllerNavBar navBarController;
    @FXML private ControllerMainFeed mainFeedController;
    @FXML private ControllerConta contaController;
    @FXML private ControllerMensagem mensagemController;

    public void setContaSessao(Conta contaSessao) {
        this.contaSessao = contaSessao;
        System.out.printf("\nConta Sessao (Feed): %d", contaSessao.getMatr());
        headerFeedController.setContaSessao(contaSessao);

        navBarController.setContaSessao(contaSessao);
        navBarController.setMainFeedController(mainFeedController);
        navBarController.setContaController(contaController);

        mainFeedController.alterarStatus();
    }

    public void abrirMensagemLogin() {
        mensagemController.exibir("Bem Vindo!", "Login realizado com sucesso.", "accent");
    }
}
