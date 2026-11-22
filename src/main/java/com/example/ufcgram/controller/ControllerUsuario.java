package com.example.ufcgram.controller;

import com.example.ufcgram.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class ControllerUsuario {

    @FXML private Label nomeConta;
    @FXML private Label cursoConta;
    @FXML private TextArea descricaoConta;
    @FXML private Label emailConta;
    @FXML private Label idadeConta;
    @FXML private Label generoConta;
    @FXML private Label matrConta;
    @FXML private Label campusConta;
    @FXML private Label semetreConta;

    @FXML private HBox usuario;
    @FXML private HBox btnGroup;

    private boolean status = false;

    private Usuario usuarioSessao;
    private Usuario usuarioPerfil;

    public void setContas(Usuario usuarioSessao, Usuario usuarioPerfil) {
        System.out.printf("\nConta Sessao (Conta): %d\n", usuarioSessao.getMatricula());
        this.usuarioSessao = usuarioSessao;
        this.usuarioPerfil = usuarioPerfil;

        mostarInfos();
    }

    @FXML public void alterarStatus() {
        status = !status;

        usuario.setManaged(status);
        usuario.setVisible(status);
    }

    @FXML public void abrir() {
        status = true;

        usuario.setManaged(status);
        usuario.setVisible(status);
    }

    @FXML public void fechar() {
        status = false;

        usuario.setManaged(status);
        usuario.setVisible(status);
    }

    private void mostarInfos() {
        nomeConta.setText(usuarioPerfil.getNome());
        cursoConta.setText(usuarioPerfil.getCurso());
        descricaoConta.setText(usuarioPerfil.getBio());
        emailConta.setText(usuarioPerfil.getEmail());
        idadeConta.setText(String.valueOf(usuarioPerfil.getIdade()));
        generoConta.setText(String.valueOf(usuarioPerfil.getGenero()));
        matrConta.setText(String.valueOf(usuarioPerfil.getMatricula()));
        campusConta.setText(usuarioPerfil.getCampus());
        semetreConta.setText(usuarioPerfil.getSemestreInicial());

        if(usuarioSessao.getMatricula() == usuarioPerfil.getMatricula()) {
            btnGroup.setVisible(false);
            btnGroup.setManaged(false);
        } else {
            btnGroup.setVisible(true);
            btnGroup.setManaged(true);
        }
    }

    public boolean getStatus() {
        return status;
    }
}
