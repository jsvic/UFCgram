package com.example.ufcgram.controller;


import com.example.ufcgram.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerNavBar {

    @FXML private ControllerMainFeed mainFeedController;
    @FXML private ControllerUsuario usuarioController;

    @FXML private Button navButton;

    private Usuario usuarioSessao;

    public void setContaSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }

    @FXML public void setMainFeedController(ControllerMainFeed controller) {
        mainFeedController = controller;
    }

    @FXML public void setContaController(ControllerUsuario controlller) {
        usuarioController = controlller;
    }

    @FXML
    public void abrirContaSessao() {
        if(usuarioController.getStatus()) {
            navButton.setText("Ver Conta");
        } else {
            navButton.setText("Feed");
            usuarioController.setContas(usuarioSessao, usuarioSessao);
        }
        mainFeedController.alterarStatus();
        usuarioController.alterarStatus();
        usuarioController.setContas(usuarioSessao, usuarioSessao);
    }
}
