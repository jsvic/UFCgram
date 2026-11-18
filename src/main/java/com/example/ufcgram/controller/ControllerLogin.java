package com.example.ufcgram.controller;

import com.example.ufcgram.model.Conta;
import com.example.ufcgram.service.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ControllerLogin {
    // Classe para a pagina de login

    @FXML private Spinner<Integer> inMatr; // Referencia o campo matricula
    @FXML private PasswordField inSenha; // Referencia o campo senha

    @FXML private Label matrErrorLabel; // Mensagem de erro do campo matricula
    @FXML private Label senhaErrorLabel; // Mensagem de erro do campo senha

    @FXML private ControllerMensagem mensagemController; // Cria um objeto para o Controller do Message

    private final Conta conta = new Conta(); // Cria um objeto do model Conta

    @FXML
    public void initialize() {
        // Função que executa toda vez que a view executa

        inMatr.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        100000,       // Mínimo
                        999999, // Máximo
                        100000 // Valor padrão

                )
        );
    }

    public void abrirMesagemCadastro() {
        // Caso a tela de login seja aberta apos a criacao de um conta, é adiciona uma mensagem de confirmacao da criacao da conta

        mensagemController.exibir("Conta Criada", "Cadastro feito com sucesso.", "accent");
    }

    public void abrirMensagemExcluir() {
        // Caso a tela de login seja aberta apos a excluir
        mensagemController.exibir("Conta Excluida", "Conta deletada com sucesso.", "accent");
    }

    public void abrirMensagemSair() {
        // Caso a tela de login seja aberta apos a sair
        mensagemController.exibir("Sessão Encerrada", "Conta encerrada com sucesso.", "accent");
    }

    @FXML
    public void limpar() {
        // Metodo chamada ao clicar no botao "limpar"

        inMatr.getValueFactory().setValue(100000); // Retoma o valor do campo matricula ao valor inicial
        inSenha.setText(""); // Retoma o valor do campo senha ao valor inicial
    }

    @FXML
    public void abrirCadastro(ActionEvent event) throws IOException {
        // Redireciona para a tela de cadastro

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/example/ufcgram/view/viewCadastroPessoais.fxml"
                )
        );

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root);
    }

    @FXML
    public void abrirFeed(ActionEvent event, Conta conta) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewFeed.fxml"
        )
        ); // Abre o arquivo FXMl

        Parent root = loader.load();

        ControllerFeed controller = loader.getController(); // Pega o Controller do FXML
        controller.setContaSessao(conta); // Passa o objeto Conta para o Controller
        controller.abrirMensagemLogin(); // Abre uma mensagem para informar que o login foi feito

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Adicionar o FXMl à janela
    }

    @FXML
    public void fazerLogin(ActionEvent event) {
        // A função chama contaSessao para validar o login e, caso retorne true, redireciona para o Feed, caso de false, apresentará mensagem de erro + label de erro

        String senha;
        int matr;
        Conta contaSessao;

        matr = inMatr.getValue(); // Valor do campo matricula
        senha = inSenha.getText(); // Valor do campo senha

        conta.setMatr(matr); // Inserindo a matrícula no objeto Conta
        conta.setSenha(senha); // Inserindo a senha no objeto Conta

        try{
            contaSessao = conta.fazerLogin(); // Chamando o metodo de criar conta
            abrirFeed(event, contaSessao); // Caso a matricula e a senha estejam corretas, é redirecionado para a tela do Feed
        } catch(Exception e) {
            System.out.print("\nEntrei no catch\nMensagem: ");
            e.printStackTrace();
            if(!(e.getCause() instanceof IllegalArgumentException)) {
                mensagemController.exibir("Erro no Login", e.getMessage().split(": ")[1], "danger"); // Exibe o componente de Mensage com o texto do erro
            }
        }

    }
}