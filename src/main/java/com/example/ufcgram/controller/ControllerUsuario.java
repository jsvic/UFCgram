package com.example.ufcgram.controller;

import atlantafx.base.controls.Card;
import com.example.ufcgram.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serial;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

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

    @FXML private VBox postList;
    @FXML private HBox usuario;
    @FXML private Button btnSeguir;
    @FXML private Button btnDeixarSeguir;
    @FXML private Button listPostCriados;
    @FXML private Button listPostCurtidos;
    @FXML private Button listPostsComentados;

    private boolean status = false;
    private String listPost;

    @FXML TextField inPesquisa;

    private Usuario usuarioSessao;
    private Usuario usuarioPerfil;
    private Feed feed;

    @FXML ControllerModalDeletarPost modalExcluir;
    @FXML ControllerModalEditarPost modalEditar;
    @FXML ControllerModalComentarios modalComentarios;
    @FXML ControllerNavBar controllerNavBar;
    @FXML private ControllerMensagem mensagemController;

    public void setControllerModals(ControllerModalDeletarPost controllerDeletar, ControllerModalEditarPost controllerEditar, ControllerModalComentarios controllerComentarios, ControllerNavBar controllerNavBar, ControllerMensagem controllerMensagem) {
        modalExcluir = controllerDeletar;
        modalEditar = controllerEditar;
        modalComentarios = controllerComentarios;
        this.controllerNavBar = controllerNavBar;
        this.mensagemController = controllerMensagem;
    }

    public void setContas(Usuario usuarioSessao, Usuario usuarioPerfil) throws IOException {
        System.out.printf("\nConta Sessao (Conta): %d\nConta perfil: %d\n", usuarioSessao.getMatricula(), usuarioPerfil.getMatricula());
        this.usuarioSessao = usuarioSessao;
        this.usuarioPerfil = usuarioPerfil;
        feed = new Feed(usuarioSessao);

        mostarInfos();
        listarPublicacoes(usuarioPerfil.recuperarPublicacoes());
    }

    @FXML public void seguir(int matr) throws IOException {
        usuarioSessao.seguir(matr);
        listarPublicacoes(usuarioPerfil.recuperarPublicacoes());
        controllerNavBar.listarUsuarios(feed.getUsuariosRecomendados());
        mostarInfos();
        btnDeixarSeguir.setVisible(true);
        btnDeixarSeguir.setManaged(true);
        btnSeguir.setVisible(false);
        btnSeguir.setManaged(false);
    }

    @FXML public void deixarSeguir(int matr) throws IOException {
        usuarioSessao.deixarSeguir(matr);
        listarPublicacoes(usuarioPerfil.recuperarPublicacoes());
        controllerNavBar.listarUsuarios(feed.getUsuariosRecomendados());
        mostarInfos();
        btnDeixarSeguir.setVisible(false);
        btnDeixarSeguir.setManaged(false);
        btnSeguir.setVisible(true);
        btnSeguir.setManaged(true);
    }

    @FXML public void listarPublicacoes(ArrayList<Publicacao> publicacoesList) throws IOException {
        String itemPublicacao;
        postList.getChildren().clear();
        for(Publicacao publicacao: publicacoesList) {

            if(Objects.equals(publicacao.getTipo(), "TEXTO")) {
                PublicacaoTexto texto = (PublicacaoTexto) publicacao;
                itemPublicacao = """
                        <?import javafx.scene.layout.*?>
                        <?import javafx.scene.control.*?>
                        <?import atlantafx.base.controls.Card?>
                         <Card fx:id="%d" xmlns:fx="http://javafx.com/fxml/1" styleClass="postTexto">
                           <header>
                               <VBox styleClass="postHeader">
                                   <HBox styleClass="postHeaderInfos">
                                       <Label text="Criado por: %s" fx:id="criador" styleClass="title-3"/>
                                       <Region HBox.hgrow="ALWAYS" />
                                       <Label text="%s" styleClass="text-lighter"/>
                                       <MenuButton fx:id="configPost" managed="false" visible="false" text="⋮">
                                            <items>
                                             <MenuItem text="Editar" fx:id="editar"/>
                                             <MenuItem text="Excluir" fx:id="excluir"/>
                                            </items>
                                       </MenuButton>
                                   </HBox>
                                   <HBox>
                                       <Button text="Seguir" fx:id="btnSeguir" managed="false" visible="false" styleClass="accent"/>
                                       <Button text="Seguindo" fx:id="btnDeixarSeguir" managed="false" visible="false" styleClass="ordinary"/>
                                   </HBox>
                                   <Separator/>
                               </VBox>
                           </header>
                           <body>
                               <VBox>
                                   <TextArea text="%s"
                                             editable="false"
                                             wrapText="true"
                                             styleClass="postConteudo"/>
                               </VBox>
    
                           </body>
                           <footer>
                               <VBox>
                                   <Separator/>
                                   <Label text="%d curtidas  %d comentarios"  fx:id="qtdPost" styleClass="text-bold"/>
                                   <HBox fx:id="buttonGroup" styleClass="buttonGroup">
                                          <Button text="Curtir" fx:id="curtir" styleClass="accent"/>
                                          <Button text="Curtido" fx:id="removerCurtir" managed="false" visible="false" styleClass="ordinary"/>
                                          <Button text="Comentar" fx:id="comentar" styleClass="ordinary"/>
                                   </HBox>
                               </VBox>
                           </footer>
                        </Card>
                    """.formatted(texto.getIdPublicacao(),
                        feed.consultarUsuarioPorMatriucula(texto.getMatriculaCriador()).getNome(),
                        texto.getDataHoraCriacao().toString() ,texto.getTexto(), texto.totalCurtidas(),
                        texto.totalComentarios());
            } else {
                PublicacaoEvento evento = (PublicacaoEvento) publicacao;
                itemPublicacao = """
                        <?import javafx.scene.layout.*?>
                        <?import javafx.scene.control.*?>
                        <?import atlantafx.base.controls.Card?>
                         <Card fx:id="%d" xmlns:fx="http://javafx.com/fxml/1" styleClass="postEvento">
                              <header>
                                  <VBox styleClass="postHeader">
                                      <HBox>
                                          <Label text="%s" styleClass="title-3"/>
                                          <Region HBox.hgrow="ALWAYS" />
                                          <MenuButton fx:id="configPost" managed="false" visible="false" text="⋮">
                                          <items>
                                             <MenuItem text="Editar" fx:id="editar"/>
                                             <MenuItem text="Excluir" fx:id="excluir"/>
                                          </items>
                                          </MenuButton>
                                      </HBox>
                                      <HBox styleClass="postBtnSeguir">
                                          <Label text="Criado por: %s" fx:id="criador" styleClass="text-bold"/>
                                          <Button text="Seguir" fx:id="btnSeguir" managed="false" visible="false" styleClass="accent"/>
                                          <Button text="Seguindo" fx:id="btnDeixarSeguir" managed="false" visible="false" styleClass="ordinary"/>
                                          <Region HBox.hgrow="ALWAYS" />
                                          <Label text="%s" styleClass="text-lighter"/>
                                      </HBox>
                                      <Separator/>
                                  </VBox>
                              </header>
    
                              <body>
                                  <VBox>
                                      <TextArea text="%s"
                                                editable="false"
                                                wrapText="true"
                                                styleClass="postConteudo"/>
    
                                      <HBox styleClass="postEventoInfos">
                                          <Label text="Local: %s"/>
                                          <Region HBox.hgrow="ALWAYS" />
                                          <Label text="Data do Evento: %s"/>
                                      </HBox>
                                  </VBox>
    
                              </body>
                              <footer>
                                  <VBox>
                                      <Separator/>
                                      <Label text="%d curtidas  %d comentarios"  fx:id="qtdPost" styleClass="text-bold"/>
                                      <HBox fx:id="buttonGroup" styleClass="buttonGroup">
                                          <Button text="Curtir" fx:id="curtir" managed="false" visible="false" styleClass="accent"/>
                                          <Button text="Curtido" fx:id="removerCurtir" managed="false" visible="false" styleClass="ordinary"/>
                                          <Button text="Comentar" fx:id="comentar" styleClass="ordinary"/>
                                      </HBox>
                                  </VBox>
                              </footer>
                            </Card>
                    """.formatted(evento.getIdPublicacao(),  evento.getTitulo(),
                        feed.consultarUsuarioPorMatriucula(evento.getMatriculaCriador()).getNome(),
                        evento.getDataHoraCriacao().toString(),
                        evento.getDescricao(), evento.getLocal(), evento.getDataEvento().toString(),
                        evento.totalCurtidas(), evento.totalCurtidas());
            }

            FXMLLoader loader = new FXMLLoader();

            System.out.printf("\n======\npublicacao id: %d\n=======\n", publicacao.getIdPublicacao());

            Parent item = loader.load(
                    new ByteArrayInputStream(
                            itemPublicacao.getBytes(StandardCharsets.UTF_8)
                    )
            );

            Button btnSeguir = (Button) loader.getNamespace().get("btnSeguir");
            Button btnDeixarSeguir = (Button) loader.getNamespace().get("btnDeixarSeguir");
            Button curtir = (Button) loader.getNamespace().get("curtir");
            Button removerCurtir = (Button) loader.getNamespace().get("removerCurtir");
            Button comentar = (Button) loader.getNamespace().get("comentar");
            MenuButton configPost = (MenuButton) loader.getNamespace().get("configPost");
            MenuItem editar = (MenuItem) loader.getNamespace().get("editar");
            MenuItem excluir = (MenuItem) loader.getNamespace().get("excluir");

            Usuario usuarioCriador = feed.consultarUsuarioPorMatriucula(publicacao.getMatriculaCriador());

            if(usuarioCriador.getMatricula() == usuarioSessao.getMatricula()) {
                btnSeguir.setManaged(false);
                btnSeguir.setVisible(false);
                btnDeixarSeguir.setManaged(false);
                btnDeixarSeguir.setVisible(false);
                configPost.setManaged(true);
                configPost.setVisible(true);
            } else {
                configPost.setManaged(false);
                configPost.setVisible(false);
                if (usuarioSessao.consultarSeguindo(usuarioCriador)) {
                    btnSeguir.setManaged(false);
                    btnSeguir.setVisible(false);
                    btnDeixarSeguir.setManaged(true);
                    btnDeixarSeguir.setVisible(true);
                } else {
                    btnSeguir.setManaged(true);
                    btnSeguir.setVisible(true);
                    btnDeixarSeguir.setManaged(false);
                    btnDeixarSeguir.setVisible(false);
                }

            }

            if(publicacao.verificarCurtida(usuarioSessao)) {
                curtir.setManaged(false);
                curtir.setVisible(false);
                removerCurtir.setManaged(true);
                removerCurtir.setVisible(true);
            } else {
                curtir.setManaged(true);
                curtir.setVisible(true);
                removerCurtir.setManaged(false);
                removerCurtir.setVisible(false);
            }


            btnSeguir.setOnAction(event -> {
                try {
                    seguirCriador(publicacao.getMatriculaCriador());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            btnDeixarSeguir.setOnAction(event -> {
                try {
                    deixarSeguircriador(publicacao.getMatriculaCriador());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            editar.setOnAction(event -> abrirModalEditar(publicacao));
            excluir.setOnAction(event -> abrirModalExcluir(publicacao));
            curtir.setOnAction(event -> curtir(event, publicacao));
            removerCurtir.setOnAction(event -> removerCurtir(event, publicacao));
            comentar.setOnAction(event -> {
                try {
                    abrirModalComentarios(publicacao);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            postList.getChildren().add(item);
        }
    }

    @FXML
    private void seguirCriador(int matr) throws IOException {
        seguir(matr);
    }

    @FXML
    private void deixarSeguircriador(int matr) throws IOException {
        deixarSeguir(matr);
    }

    @FXML
    public void curtir(ActionEvent event, Publicacao publicacao) {

        new Curtida(usuarioSessao.getMatricula()).curtirPublicacao(publicacao);

        Node elemento = (Node) event.getSource();
        Button curtir = (Button) elemento.getParent().lookup("#curtir");
        Button removerCurtir = (Button) elemento.getParent().lookup("#removerCurtir");

        curtir.setManaged(false);
        curtir.setVisible(false);
        removerCurtir.setManaged(true);
        removerCurtir.setVisible(true);
    }

    @FXML
    public void removerCurtir(ActionEvent event, Publicacao publicacao) {
        new Curtida(usuarioSessao.getMatricula()).removerCurtida(publicacao);

        Node elemento = (Node) event.getSource();
        Button curtir = (Button) elemento.getParent().lookup("#curtir");
        Button removerCurtir = (Button) elemento.getParent().lookup("#removerCurtir");

        curtir.setManaged(true);
        curtir.setVisible(true);
        removerCurtir.setManaged(false);
        removerCurtir.setVisible(false);
    }

    @FXML public void abrirModalExcluir(Publicacao publicacao) {
        modalExcluir.abrirModal();
        modalExcluir.setPublicacao(publicacao);
        modalExcluir.setContaSessao(usuarioSessao);
        modalExcluir.setControllers(mensagemController);
    }
    @FXML public void abrirModalEditar(Publicacao publicacao) {

        modalEditar.abrirModal();
        modalEditar.setPublicacao(publicacao);
        modalEditar.setContaSessao(usuarioSessao);
        modalEditar.setControllers(mensagemController);
    }

    @FXML
    public void abrirModalComentarios(Publicacao publicacao) throws IOException {

        modalComentarios.abrirModal(publicacao, usuarioSessao);
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

    @FXML public void seguirPerfil() throws IOException {
        seguir(usuarioPerfil.getMatricula());
    }

    @FXML public void deixarSeguirPerfil() throws IOException {
        deixarSeguir(usuarioPerfil.getMatricula());

    }

    @FXML
    public void listarCurtidos() throws IOException {
        listPost = "CURTIDOS";
        listarPublicacoes(usuarioSessao.recuperarPublicacoesCurtidas());
    }

    @FXML
    public void listarComentadas() throws IOException {
        listPost = "COMENTADOS";
        listarPublicacoes(usuarioSessao.recuperarPublicacoesComentadas());
    }

    @FXML
    public void listarCriados() throws IOException {
        listPost = "CRIADOS";
        listarPublicacoes(usuarioSessao.recuperarPublicacoes());
    }

    @FXML
    public void buscarPublicacoes() throws IOException {
        ArrayList<Publicacao> publicacoes = new ArrayList<>();

        if(Objects.equals(listPost, "CRIADOS")) {
            publicacoes = usuarioSessao.recuperarPublicacoes();
        } else if(Objects.equals(listPost, "COMENTADOS")){
            publicacoes = usuarioSessao.recuperarPublicacoesComentadas();
        } else {
            publicacoes = usuarioSessao.recuperarPublicacoesCurtidas();
        }

        listarPublicacoes(buscar(publicacoes, inPesquisa.getText()));
    }

    public ArrayList<Publicacao>  buscar(ArrayList<Publicacao> publicacoes, String busca) {
        ArrayList<Publicacao> publicacoesBuscadas = new ArrayList<>();
        PublicacaoEvento evento;
        PublicacaoTexto texto;
        for(Publicacao publicacao : publicacoes) {
            if(Objects.equals(publicacao.getTipo(), "TEXTO")) {
                texto = (PublicacaoTexto) publicacao;

                if(texto.getTexto().toLowerCase().startsWith(busca.toLowerCase())) {
                    publicacoesBuscadas.add(publicacao);
                }
            } else {
                evento = (PublicacaoEvento) publicacao;

                if(evento.getTitulo().toLowerCase().startsWith(busca.toLowerCase())) {
                    publicacoesBuscadas.add(publicacao);
                }
            }
        }

        return publicacoesBuscadas;
    }


    private void mostarInfos() {
        System.out.printf("\nQtd seguidores 1: %d\nQtd seguindos 1: %d\n", usuarioPerfil.getSeguidores().size(), usuarioPerfil.getSeguindos().size());

        usuarioPerfil.atualizarDados();

        System.out.printf("\nQtd seguidores 2: %d\nQtd seguindos 2: %d\n", usuarioPerfil.getSeguidores().size(), usuarioPerfil.getSeguindos().size());

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
            listPostCriados.setManaged(true);
            listPostCriados.setVisible(true);
            listPostCurtidos.setManaged(true);
            listPostCurtidos.setVisible(true);
            listPostsComentados.setManaged(true);
            listPostsComentados.setVisible(true);
        } else {
            listPostCriados.setManaged(false);
            listPostCriados.setVisible(false);
            listPostCurtidos.setManaged(false);
            listPostCurtidos.setVisible(false);
            listPostsComentados.setManaged(false);
            listPostsComentados.setVisible(false);

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

    public boolean getStatus() {
        return status;
    }
}
