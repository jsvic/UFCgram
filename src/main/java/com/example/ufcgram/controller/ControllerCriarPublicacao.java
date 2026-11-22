package com.example.ufcgram.controller;

import com.example.ufcgram.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class ControllerCriarPublicacao {
    @FXML private VBox createPublicacao;
    @FXML private VBox formEvento;
    @FXML private VBox formTexto;

    @FXML ToggleGroup inTipo;
    @FXML RadioButton rdTexto;
    @FXML RadioButton rdEvento;
    @FXML TextArea inTexto;
    @FXML TextField inTitulo;
    @FXML TextField inLocal;
    @FXML DatePicker inData;
    @FXML TextArea inDescricao;


    private Usuario usuarioSessao;

    private boolean status = false;

    @FXML public void alterarStatus() {
        status = !status;

        createPublicacao.setManaged(status);
        createPublicacao.setVisible(status);
    }

    public void fechar() {
        status = false;

        createPublicacao.setManaged(status);
        createPublicacao.setVisible(status);
    }

    public void abrir() {
        status = true;

        createPublicacao.setManaged(status);
        createPublicacao.setVisible(status);
    }

    @FXML public void mostrarForm() {
        Toggle toggle = inTipo.getSelectedToggle();

        if(toggle != null) {
            RadioButton opcao = (RadioButton) toggle;
            if(opcao == rdEvento) {
                mostrarFormEvento();
            } else {
                mostrarFormTexto();
            }
        }
    }

    @FXML public void mostrarFormTexto() {
        formEvento.setManaged(false);
        formEvento.setVisible(false);
        formTexto.setManaged(true);
        formTexto.setVisible(true);
    }

    @FXML public void mostrarFormEvento() {
        formTexto.setManaged(false);
        formTexto.setVisible(false);
        formEvento.setManaged(true);
        formEvento.setVisible(true);
    }

    @FXML public void limpar() {
        inTexto.setText("");
        inTitulo.setText("");
        inLocal.setText("");
        inData.setValue(null);
        inDescricao.setText("");
    }

    @FXML public void criar() {
        Toggle toggle = inTipo.getSelectedToggle();
        if(toggle != null) {
            RadioButton opcao = (RadioButton) toggle;
            if(opcao == rdEvento) {
                criarEvento();
            } else {
                criarTexto();
            }
        }
    }

    @FXML public void criarEvento() {
        String titulo, local, descricao;
        LocalDate data;

        titulo = inTitulo.getText();
        local = inLocal.getText();
        descricao = inDescricao.getText();
        data = inData.getValue();

        System.out.printf("\nTitulo: %s\nLocal: %s\nDescicao: %s\nData: %s\n", titulo, local, descricao, data);
    }

    @FXML public void criarTexto() {
        String texto;

        texto = inTexto.getText();

        System.out.printf("\nTexto: %s\n", texto);
    }


    public boolean getStatus() {
        return status;
    }

    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }
}
