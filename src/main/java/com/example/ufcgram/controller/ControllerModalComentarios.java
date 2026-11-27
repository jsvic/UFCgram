package com.example.ufcgram.controller;

import atlantafx.base.controls.ModalPane;
import atlantafx.base.layout.ModalBox;
import com.example.ufcgram.model.Comentario;
import com.example.ufcgram.model.Feed;
import com.example.ufcgram.model.Publicacao;
import com.example.ufcgram.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ControllerModalComentarios {

    @FXML private TextArea inComentario;

    @FXML
    private ModalPane modalPane; // Referencia o fundo do modal
    @FXML private ModalBox modalBox; // Referencia o conteudo do modal
    @FXML private VBox comentariosList; // Referencia o campo de confirmacao do modal do deletar conta

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

    @FXML public void abrirModal(Publicacao publicacao) throws IOException {
        // Metodo chamado pelo botao excluir conta

        modalPane.show(modalBox);
        this.publicacao = publicacao;
        listarComentario(publicacao.getComentarios());
    }

    @FXML
    public void listarComentario(ArrayList<Comentario> comentarios) throws IOException {
        String itemComentario;
        comentariosList.getChildren().clear();
        for(Comentario comentario: comentarios) {
            itemComentario = """
                      <?import javafx.scene.control.*?>
                      <?import javafx.scene.layout.*?>
                      <?import atlantafx.base.controls.ModalPane?>
                      <?import atlantafx.base.layout.ModalBox?>
                      <?import atlantafx.base.controls.Card?>
                      
                        <VBox xmlns="http://javafx.com/javafx" xmlns:fx="http://javafx.com/fxml" fx:id="%d" styleClass="contentComentario">
                            <HBox>
                               <Label text="%s" styleClass="text-bold"/>
                               <Region HBox.hgrow="ALWAYS" />
                               <Label text="%s" styleClass="text-bold"/>
                            </HBox>
                       <TextArea text="%s"
                                 editable="false"
                                 wrapText="true"
                                 styleClass="textPost"/>
                        </VBox>
                    """.formatted(comentario.getMatriculaUsuario(),
                    new Feed().consultarUsuarioPorMatriucula(comentario.getMatriculaUsuario()).getNome(),
                    comentario.getDataHoraInteracao().toString(), comentario.getTexto());
            FXMLLoader loader = new FXMLLoader();

            Parent item = loader.load(
                    new ByteArrayInputStream(itemComentario.getBytes(StandardCharsets.UTF_8))
            );

            comentariosList.getChildren().add(item);
        }
    }

    @FXML
    public void comentar() throws IOException {
        new Comentario(usuarioSessao.getMatricula(), inComentario.getText()).comentarPublicacao(publicacao);
        listarComentario(publicacao.getComentarios());
        limpar();
    }

    @FXML
    public void limpar() {
        inComentario.setText("");
    }
}