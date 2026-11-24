package com.example.ufcgram.controller;

import com.example.ufcgram.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.Serial;

public class ControllerUsuario {

    @FXML private Label nomeConta;
    @FXML private Label cursoConta;
    @FXML private Label seguidores;
    @FXML private Label seguindos;
    @FXML private TextArea descricaoConta;
    @FXML private Label emailConta;
    @FXML private Label idadeConta;
    @FXML private Label generoConta;
    @FXML private Label matrConta;
    @FXML private Label campusConta;
    @FXML private Label semetreConta;

    @FXML private HBox usuario;
    @FXML private Button btnSeguir;
    @FXML private Button btnDeixarSeguir;

    private boolean status = false;

    private Usuario usuarioSessao;
    private Usuario usuarioPerfil;

    @FXML ControllerModalDeletarPost modalExcluir;
    @FXML ControllerModalEditarPost modalEditar;
    @FXML ControllerModalComentarios modalComentarios;

    public void setContas(Usuario usuarioSessao, Usuario usuarioPerfil) {
        System.out.printf("\nConta Sessao (Conta): %d\n", usuarioSessao.getMatricula());
        this.usuarioSessao = usuarioSessao;
        this.usuarioPerfil = usuarioPerfil;

        mostarInfos();
    }

    public void setControllerModals(ControllerModalDeletarPost controllerDeletar, ControllerModalEditarPost controllerEditar, ControllerModalComentarios controllerComentarios) {
        modalExcluir = controllerDeletar;
        modalEditar = controllerEditar;
        modalComentarios = controllerComentarios;
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

    @FXML public void seguir() {
        usuarioSessao.seguir(usuarioPerfil.getMatricula());
        btnDeixarSeguir.setVisible(true);
        btnDeixarSeguir.setManaged(true);
        btnSeguir.setVisible(false);
        btnSeguir.setManaged(false);
    }

    @FXML public void deixarSeguir() {
        usuarioSessao.deixarSeguir(usuarioPerfil.getMatricula());
        btnDeixarSeguir.setVisible(false);
        btnDeixarSeguir.setManaged(false);
        btnSeguir.setVisible(true);
        btnSeguir.setManaged(true);
    }

    private void mostarInfos() {
        usuarioPerfil.atualizarDados();

        nomeConta.setText(usuarioPerfil.getNome());
        cursoConta.setText(usuarioPerfil.getCurso());
        seguidores.setText(String.format("Seguidores: %d", usuarioPerfil.getSeguidores().size()));
        seguindos.setText(String.format("Seguindos: %d", usuarioPerfil.getSeguindos().size()));
        descricaoConta.setText(usuarioPerfil.getBio());
        emailConta.setText(usuarioPerfil.getEmail());
        idadeConta.setText(String.valueOf(usuarioPerfil.getIdade()));
        generoConta.setText(String.valueOf(usuarioPerfil.getGenero()));
        matrConta.setText(String.valueOf(usuarioPerfil.getMatricula()));
        campusConta.setText(usuarioPerfil.getCampus());
        semetreConta.setText(usuarioPerfil.getSemestreInicial());

        if(usuarioSessao.getMatricula() == usuarioPerfil.getMatricula()) {
            btnSeguir.setVisible(false);
            btnSeguir.setManaged(false);
            btnDeixarSeguir.setVisible(false);
            btnDeixarSeguir.setManaged(false);
        } else {
            if(usuarioSessao.consultarSeguindo(usuarioPerfil)) {
                btnDeixarSeguir.setVisible(true);
                btnDeixarSeguir.setManaged(true);
                btnSeguir.setVisible(false);
                btnSeguir.setManaged(false);
            } else {
                btnDeixarSeguir.setVisible(false);
                btnDeixarSeguir.setManaged(false);
                btnSeguir.setVisible(true);
                btnSeguir.setManaged(true);
            }
        }
    }

    @FXML public void abrirModalExcluir() {
        modalExcluir.abrirModal();
    }
    @FXML public void abrirModalEditar() {
        modalEditar.abrirModal();
    }
    @FXML public void abrirModalComentarios() {
        modalComentarios.abrirModal();
    }


    public boolean getStatus() {
        return status;
    }
}
