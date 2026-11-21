package com.example.ufcgram.controller;


import com.example.ufcgram.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerNavBar {

    @FXML private ControllerMainFeed mainFeedController;
    @FXML private ControllerConta contaController;

    @FXML private Button navButton;

    private Usuario usuarioSessao;

    public void setContaSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
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
            contaController.setContas(usuarioSessao, usuarioSessao);
        }
        mainFeedController.alterarStatus();
        contaController.alterarStatus();
        contaController.setContas(usuarioSessao, usuarioSessao);
    }
}
