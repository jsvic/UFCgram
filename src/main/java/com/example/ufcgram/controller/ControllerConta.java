package com.example.ufcgram.controller;

import com.example.ufcgram.model.Conta;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControllerConta {

    @FXML private Label nomeConta;
    @FXML private Label cursoConta;
    @FXML private TextArea descricaoConta;
    @FXML private Label emailConta;
    @FXML private Label idadeConta;
    @FXML private Label generoConta;
    @FXML private Label matrConta;
    @FXML private Label campusConta;
    @FXML private Label semetreConta;

    @FXML private HBox conta;
    @FXML private HBox btnGroup;

    private Conta contaSessao;
    private Conta contaPerfil;

    public void setContas(Conta contaSessao, Conta contaPerfil) {
        System.out.printf("\nConta Sessao (Conta): %d\n", contaSessao.getMatr());
        this.contaSessao = contaSessao;
        this.contaPerfil = contaPerfil;

        mostarInfos();
    }

    @FXML public void abrir() {
        conta.setManaged(true);
        conta.setVisible(true);
    }

    @FXML public void fechar() {
        conta.setManaged(false);
        conta.setVisible(false);
    }

    private void mostarInfos() {
        nomeConta.setText(contaPerfil.getNome());
        cursoConta.setText(contaPerfil.getCurso());
        descricaoConta.setText(contaPerfil.getBio());
        emailConta.setText(contaPerfil.getEmail());
        idadeConta.setText(String.valueOf(contaPerfil.getIdade()));
        generoConta.setText(String.valueOf(contaPerfil.getGenero()));
        matrConta.setText(String.valueOf(contaPerfil.getMatr()));
        campusConta.setText(contaPerfil.getCampus());
        semetreConta.setText(contaPerfil.getSemestreInicial());

        if(contaSessao.getMatr() == contaPerfil.getMatr()) {
            btnGroup.setVisible(false);
            btnGroup.setManaged(false);
        } else {
            btnGroup.setVisible(true);
            btnGroup.setManaged(true);
        }
    }
}
