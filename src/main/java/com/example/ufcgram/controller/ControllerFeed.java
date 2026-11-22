package com.example.ufcgram.controller;

import com.example.ufcgram.model.Usuario;
import javafx.fxml.FXML;

import java.io.IOException;

public class ControllerFeed {
    private Usuario usuarioSessao;
    @FXML private ControllerHeaderFeed headerFeedController;
    @FXML private ControllerNavBar navBarController;
    @FXML private ControllerMainFeed mainFeedController;
    @FXML private ControllerUsuario usuarioController;
    @FXML private ControllerMensagem mensagemController;

    public void setContaSessao(Usuario usuarioSessao) throws IOException {
        this.usuarioSessao = usuarioSessao;
        System.out.printf("\nConta Sessao (Feed): %d", usuarioSessao.getMatricula());
        headerFeedController.setContaSessao(usuarioSessao);

        navBarController.setContaSessao(usuarioSessao);
        navBarController.setMainFeedController(mainFeedController);
        navBarController.setContaController(usuarioController);

        mainFeedController.alterarStatus();
    }

    public void abrirMensagemLogin() {
        mensagemController.exibir("Bem Vindo!", "Login realizado com sucesso.", "accent");
    }
}
