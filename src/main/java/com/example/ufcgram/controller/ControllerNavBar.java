package com.example.ufcgram.controller;


import com.example.ufcgram.model.Feed;
import com.example.ufcgram.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ControllerNavBar {

    @FXML private ControllerMainFeed mainFeedController;
    @FXML private ControllerUsuario usuarioController;
    @FXML private ControllerCriarPublicacao createPostController;

    @FXML private Button navButton;
    @FXML private VBox usuarioList;
    @FXML private Button btnCriarPublicacao;

    private Usuario usuarioSessao;
    private Usuario usuarioPefil;
    private Feed feed;

    @FXML TextField inNome;

    @FXML public void setContaSessao(Usuario usuarioSessao) throws IOException {

        this.usuarioSessao = usuarioSessao;
        feed = new Feed(usuarioSessao);
        listarUsuarios(feed.getUsuariosRecomendados());
    }

    @FXML public void setMainFeedController(ControllerMainFeed controller) {
        mainFeedController = controller;
    }

    @FXML public void setContaController(ControllerUsuario controlller) {
        usuarioController = controlller;
    }

    @FXML public void setCreatePostController(ControllerCriarPublicacao controlller) {
        createPostController = controlller;
    }

    @FXML
    public void abrirFeed() {
        navButton.setText("Feed");
        btnCriarPublicacao.setText("Criar Publicação");
        usuarioController.fechar();
        createPostController.fechar();
        mainFeedController.abrir();
    }

    public void abrirConta() throws IOException {
        navButton.setText("Feed");
        btnCriarPublicacao.setText("Criar Publicação");
        mainFeedController.fechar();
        createPostController.fechar();
        usuarioController.abrir();
        usuarioController.setContas(usuarioSessao, usuarioPefil);
    }

    @FXML
    public void abrirContaSessao() throws IOException {
        usuarioPefil = usuarioSessao;

        if(usuarioController.getStatus()) {
            // Se tiver Aberto
            navButton.setText("Ver Conta");
            mainFeedController.abrir();
            usuarioController.fechar();
        } else {
            // Se tiver Fechado
            abrirConta();
        }

    }

    @FXML
    public void abrirContaPerfil(MouseEvent event) throws IOException {
          Node elemento = (Node) event.getSource();

        int id = Integer.parseInt(elemento.getId());

        usuarioPefil = feed.consultarUsuarioPorMatriucula(id);

        abrirConta();
    }

    @FXML
    public void abrirCriarPublicacao() {
        if(createPostController.getStatus()) {
            // se tiver Aberto
            btnCriarPublicacao.setText("Criar Publicação");
            mainFeedController.abrir();
            createPostController.fechar();
        } else {
            // se tiver Fechado
            btnCriarPublicacao.setText("Feed");
            navButton.setText("Ver Conta");
            mainFeedController.fechar();
            usuarioController.fechar();
            createPostController.abrir();
            createPostController.setUsuarioSessao(usuarioSessao);
            createPostController.setControllerNavbar(this);
        }
    }

    @FXML public void listarUsuarios(ArrayList<Usuario> usuariosList) throws IOException {
        String itemUsuario;
        usuarioList.getChildren().clear();
        for(Usuario usuario: usuariosList) {
            itemUsuario = """
                        <?import javafx.scene.layout.HBox?>
                        <?import javafx.scene.layout.Region?>
                        <?import javafx.scene.control.Label?>
                        <?import javafx.scene.control.Button?>
                        <HBox xmlns="http://javafx.com/javafx" xmlns:fx="http://javafx.com/fxml" fx:id="%d" styleClass="usuarioItem">
                            <Label text="%s" styleClass="title-4"/>
                            <Region HBox.hgrow="ALWAYS" />
                            <Button text="Seguir" fx:id="btnSeguir" styleClass="accent"/>
                            <Button text="Seguindo" fx:id="btnDeixarSeguir" styleClass="ordinary"/>
                        </HBox>
                    """.formatted(usuario.getMatricula(), usuario.getNome());
            FXMLLoader loader = new FXMLLoader();

            Parent item = loader.load(
                    new ByteArrayInputStream(itemUsuario.getBytes(StandardCharsets.UTF_8))
            );

            Button btnSeguir = (Button) loader.getNamespace().get("btnSeguir");
            Button btnDeixarSeguir = (Button) loader.getNamespace().get("btnDeixarSeguir");

            if(usuarioSessao.consultarSeguindo(usuario)) {
                btnSeguir.setManaged(false);
                btnSeguir.setVisible(false);
            } else {
                btnDeixarSeguir.setManaged(false);
                btnDeixarSeguir.setVisible(false);
            }

            item.setOnMouseClicked(event -> {
                try {
                    abrirContaPerfil(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            btnSeguir.setOnMouseClicked(event -> {
                try {
                    seguir(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            btnDeixarSeguir.setOnMouseClicked(event -> deixarSeguir(event));
            usuarioList.getChildren().add(item);
        }
    }

    @FXML
    private void seguir(MouseEvent event) throws IOException {
        Node elemento = (Node) event.getSource();

        Button btnSeguir = (Button) elemento.getParent().lookup("#btnSeguir");
        Button btnDeixarSeguir = (Button) elemento.getParent().lookup("#btnDeixarSeguir");

        btnSeguir.setVisible(false);
        btnSeguir.setManaged(false);
        btnDeixarSeguir.setVisible(true);
        btnDeixarSeguir.setManaged(true);

        int id = Integer.parseInt(elemento.getParent().getId());

        usuarioSessao.seguir(id);

        if(usuarioController.getStatus()) {
            abrirConta();
        }
    }

    @FXML
    private void deixarSeguir(MouseEvent event) {
        Node elemento = (Node) event.getSource();

        Button btnSeguir = (Button) elemento.getParent().lookup("#btnSeguir");
        Button btnDeixarSeguir = (Button) elemento.getParent().lookup("#btnDeixarSeguir");

        btnSeguir.setVisible(true);
        btnSeguir.setManaged(true);
        btnDeixarSeguir.setVisible(false);
        btnDeixarSeguir.setManaged(false);

        int id = Integer.parseInt(elemento.getParent().getId());

        // ArrayList<Usuario> usuarios = feed.buscarUsuarioPorMatr(id); // é uma boa criar um metodo no Feed buscarUsuario que  receba uma matricula e chame um service (ou um DAO) que retorne um ArrayLisy<Usuarios>, useo LIKE na consulta sql
        usuarioSessao.deixarSeguir(id);
    }

    @FXML private void buscar() throws IOException {
        listarUsuarios(feed.consultarUsuarioPorNome(inNome.getText()));
    }

    @FXML private void contasRecomendadas() throws IOException {
        listarUsuarios(feed.getUsuariosRecomendados());
    }
}
