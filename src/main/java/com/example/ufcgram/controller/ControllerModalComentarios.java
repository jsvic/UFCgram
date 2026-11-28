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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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

    @FXML public void abrirModal(Publicacao publicacao, Usuario usuarioSessao) throws IOException {
        // Metodo chamado pelo botao excluir conta

        this.publicacao = publicacao;
        this.usuarioSessao = usuarioSessao;
        listarComentario(publicacao.getComentarios());
        System.out.printf("\n\npublicacao id (abrirModal): %d\nmatr autor: %d\n\n", publicacao.getIdPublicacao(), publicacao.getMatriculaCriador());
        modalPane.show(modalBox);
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
                            <HBox styleClass= "comentarioHeader">
                               <Label text="%s" styleClass="text-bold"/>
                               <Region HBox.hgrow="ALWAYS" />
                               <Label text="%s" styleClass="text-bold"/>
                               <MenuButton fx:id="configComentario" managed="false" visible="false" text="⋮">
                                          <items>
                                             <MenuItem text="Editar" fx:id="editar"/>
                                             <MenuItem text="Excluir" fx:id="excluir"/>
                                          </items>
                               </MenuButton>
                            </HBox>
                        <HBox styleClass="editPost">
                           <TextArea text="%s"
                                     editable="false"
                                     wrapText="true"
                                     fx:id="textComentario"
                                     styleClass="textPost"/>
                           <VBox styleClass="buttonGroup">
                                <Button text="Editar" fx:id="btnEditar" managed="false" visible="false" styleClass="accent"/>
                                <Button text="Cancelar" fx:id="btnCancelar" managed="false" visible="false" styleClass="danger"/>
                          </VBox>
                       </HBox>
                        </VBox>
                    """.formatted(comentario.getIdComentario(),
                    new Feed().consultarUsuarioPorMatriucula(comentario.getMatriculaUsuario()).getNome(),
                    comentario.getDataHoraInteracao().toString(), comentario.getTexto());
            FXMLLoader loader = new FXMLLoader();

            Parent item = loader.load(
                    new ByteArrayInputStream(itemComentario.getBytes(StandardCharsets.UTF_8))
            );

            System.out.printf("\n\nlista comentarios\nPublicacao id: %d\nComentario id: %d\nTexto comentario: %s\n\n", publicacao.getIdPublicacao(),comentario.getIdComentario(), comentario.getTexto());

            MenuButton configComentario = (MenuButton) loader.getNamespace().get("configComentario");
            Button btnEditar = (Button) loader.getNamespace().get("btnEditar");
            Button btnCancelar = (Button) loader.getNamespace().get("btnCancelar");
            MenuItem editar = (MenuItem) loader.getNamespace().get("editar");
            MenuItem excluir = (MenuItem) loader.getNamespace().get("excluir");
            TextArea textComentario = (TextArea) loader.getNamespace().get("textComentario");

            if(usuarioSessao.getMatricula() == comentario.getMatriculaUsuario()) {
                configComentario.setVisible(true);
                configComentario.setManaged(true);
            }

            editar.setOnAction(event -> habilitarEdicao(textComentario, btnEditar, btnCancelar));
            excluir.setOnAction(event -> {
                try {
                    excluirComentario(comentario);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            btnEditar.setOnAction(event -> editarComentario(textComentario, comentario, btnEditar, btnCancelar));
            btnCancelar.setOnAction(event -> desabilitarEdicao(comentario, textComentario, btnEditar, btnCancelar));
            comentariosList.getChildren().add(item);
        }
    }

    @FXML
    public void comentar() throws IOException {
        Comentario comentario = new Comentario(usuarioSessao.getMatricula(), inComentario.getText());
        comentario.comentarPublicacao(publicacao);
        System.out.printf("\n\ncomentario id novo (comentar): %d\n\n", comentario.getIdComentario());

        listarComentario(publicacao.getComentarios());
        limpar();
    }

    @FXML
    public void habilitarEdicao(TextArea textComentario, Button editar, Button cancelar) {

        textComentario.setEditable(true);
        textComentario.setStyle("-fx-background-color:-color-bg-default;");
        editar.setVisible(true);
        editar.setManaged(true);
        cancelar.setVisible(true);
        cancelar.setManaged(true);
    }

    @FXML
    public void desabilitarEdicao(Comentario comentario, TextArea textComentario, Button editar, Button cancelar) {
        System.out.printf("\n\ncomentario id (desabilitar): %d\n\n", comentario.getIdComentario());

        textComentario.setEditable(false);
        textComentario.setStyle("-fx-background-color:-color-bg-subtle;");
        textComentario.setText(comentario.getTexto());
        editar.setManaged(false);
        editar.setVisible(false);
        cancelar.setManaged(false);
        cancelar.setVisible(false);
    }

    @FXML
    public void editarComentario(TextArea textComentario, Comentario comentario, Button editar, Button cancelar) {
        System.out.printf("\n\ncomentario id (editarcomentario): %d\n\n", comentario.getIdComentario());

        comentario.setTexto(textComentario.getText());
        comentario.editarComentario(publicacao);

        desabilitarEdicao(comentario, textComentario, editar, cancelar);
    }

    @FXML
    public void excluirComentario(Comentario comentario) throws IOException {
        System.out.printf("\n\ncomentario id (excluir): %d\n\n", comentario.getIdComentario());
        listarComentario(comentario.removerComentario(publicacao).getComentarios());
    }

    @FXML
    public void limpar() {
        inComentario.setText("");
    }
}