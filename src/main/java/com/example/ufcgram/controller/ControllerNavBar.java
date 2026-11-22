package com.example.ufcgram.controller;


import com.example.ufcgram.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ControllerNavBar {

    @FXML private ControllerMainFeed mainFeedController;
    @FXML private ControllerUsuario usuarioController;

    @FXML private Button navButton;
    @FXML private VBox usuarioList;

    private Usuario usuarioSessao;

    @FXML public void setContaSessao(Usuario usuarioSessao) throws IOException {

        this.usuarioSessao = usuarioSessao;
        listarUsuarios();
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

    @FXML public void listarUsuarios() throws IOException {
        String itemUsuario = "";
        for(Usuario usuario: usuarioSessao.recomendarContas()) {
            itemUsuario = """
                        <?import javafx.scene.layout.HBox?>
                        <?import javafx.scene.layout.Region?>
                        <?import javafx.scene.control.Label?>
                        <?import javafx.scene.control.Button?>
                        <HBox xmlns="http://javafx.com/javafx" xmlns:fx="http://javafx.com/fxml" fx:id="%d" styleClass="usuarioItem">
                            <Label text="%s" styleClass="title-4"/>
                            <Region HBox.hgrow="ALWAYS" />
                            <Button text="Seguir" fx:id="btnSeguir" styleClass="accent"/>
                            <Button text="Seguindo" fx:id="btnDeixarSeguir" styleClass="ordinary"  visible="false" managed="false"/>
                        </HBox>
                    """.formatted(usuario.getMatricula(), usuario.getNome());
            FXMLLoader loader = new FXMLLoader();

            Parent item = loader.load(
                    new ByteArrayInputStream(itemUsuario.getBytes(StandardCharsets.UTF_8))
            );

            Button btnSeguir = (Button) loader.getNamespace().get("btnSeguir");
            Button btnDeixarSeguir = (Button) loader.getNamespace().get("btnDeixarSeguir");

            item.setOnMouseClicked(event -> abrirConta(event));
            btnSeguir.setOnMouseClicked(event -> seguir(event));
            usuarioList.getChildren().add(item);
        }
    }

    @FXML
    public void abrirConta(MouseEvent event) {
        Usuario usuario = new Usuario();

        Node elemento = (Node) event.getSource();

        int id = Integer.parseInt(elemento.getId());

        for(Usuario item:  usuarioSessao.recomendarContas()) {
            if(item.getMatricula() == id) {
                usuario = item;
                break;
            }
        }

        navButton.setText("Feed");
        mainFeedController.fechar();
        usuarioController.abrir();
        usuarioController.setContas(usuarioSessao, usuario);
    }

    @FXML
    private void seguir(MouseEvent event) {
        Node elemento = (Node) event.getSource();

        int id = Integer.parseInt(elemento.getParent().getId());

        // ArrayList<Usuario> usuarios = feed.buscarUsuarioPorMatr(id); // é uma boa criar um metodo no Feed buscarUsuario que  receba uma matricula e chame um service (ou um DAO) que retorne um ArrayLisy<Usuarios>, useo LIKE na consulta sql
        usuarioSessao.seguirUsuario(usuarioSessao.getMatricula(), id);

    }
}
