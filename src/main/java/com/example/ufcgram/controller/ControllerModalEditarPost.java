package com.example.ufcgram.controller;

import atlantafx.base.controls.ModalPane;
import atlantafx.base.layout.ModalBox;
import com.example.ufcgram.model.Publicacao;
import com.example.ufcgram.model.PublicacaoEvento;
import com.example.ufcgram.model.PublicacaoTexto;
import com.example.ufcgram.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class ControllerModalEditarPost {

    @FXML
    private ModalPane modalPane; // Referencia o fundo do modal
    @FXML private ModalBox modalBox; // Referencia o conteudo do modal

    @FXML private VBox formEvento;
    @FXML private VBox formTexto;

    @FXML
    TextArea inTexto;
    @FXML
    TextField inTitulo;
    @FXML TextField inLocal;
    @FXML DatePicker inData;
    @FXML TextArea inDescricao;

    //Controller do componente Mensagem
    @FXML private ControllerMensagem mensagemController;

    private Usuario usuarioSessao;
    private Publicacao publicacao;

    @FXML public void initialize() {
        // Metodo que executa assim que o FXMl é iniciado

        modalBox.setOnClose((e) -> modalPane.hide());
    }

    public void setContaSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }

    public void setControllers(ControllerMensagem mensagemController) {
        this.mensagemController = mensagemController;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
        mostrarInfo();
        if(Objects.equals(publicacao.getTipo(), "TEXTO")) {
            mostrarFormTexto();
        } else {
            mostrarFormEvento();
        }
    }

    @FXML public void abrirModal() {
        // Metodo chamado pelo botao excluir conta

        modalPane.show(modalBox);
    }

    private String extrairMensagem(Exception e) {
        // Transforma "Titulo: mensagem" (do java) em apenas "mensagem" para mostrar ao usuario

        String textoCompleto = e.getMessage();

        if (textoCompleto == null) {
            return "Erro desconhecido";
        }

        String[] partes = textoCompleto.split(": ");

        if (partes.length < 2) {
            return textoCompleto;
        }

        return partes[1];
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

    @FXML public void editar() throws IOException {
        if(Objects.equals(publicacao.getTipo(), "TEXTO")) {
            editarTexto();
        } else {
            editarEvento();
        }

        modalPane.hide();
    }

    @FXML public void editarEvento() {
        String titulo, local, descricao;
        LocalDate data;

        titulo = inTitulo.getText();
        local = inLocal.getText();
        descricao = inDescricao.getText();
        data = inData.getValue();

        try {
            PublicacaoEvento evento = (PublicacaoEvento) publicacao;
            evento.setTitulo(titulo);
            evento.setLocal(local);
            evento.setDescricao(descricao);
            evento.setDataEvento(data);

            publicacao.editarPublicacao();
            mensagemController.exibir("Editado", "Publicação Evento Editado", "accent");
            mostrarInfo();
        } catch (Exception e) {
            mensagemController.exibir("Erro", extrairMensagem(e), "danger");
        }


        System.out.printf("\nTitulo: %s\nLocal: %s\nDescicao: %s\nData: %s\n", titulo, local, descricao, data);
    }

    @FXML public void editarTexto() {

        try {
            PublicacaoTexto texto = (PublicacaoTexto) publicacao;
            texto.setTexto(inTexto.getText());
            texto.editarPublicacao();
            mensagemController.exibir("Editado", "Publicação Texto Editado", "accent");
            mostrarInfo();
        } catch (Exception e) {
            mensagemController.exibir("Erro", extrairMensagem(e), "danger");
        }
    }

    @FXML public void mostrarInfo() {
        if(Objects.equals(publicacao.getTipo(), "TEXTO")) {
            PublicacaoTexto texto = (PublicacaoTexto) publicacao;
            inTexto.setText(texto.getTexto());
        } else {
            PublicacaoEvento evento = (PublicacaoEvento)  publicacao;
            inTitulo.setText(evento.getTitulo());
            inLocal.setText(evento.getLocal());
            inData.setValue(evento.getDataHoraCriacao());
            inDescricao.setText(evento.getDescricao());
        }
    }
}