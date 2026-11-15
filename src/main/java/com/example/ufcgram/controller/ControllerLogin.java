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

        mensagemController.ocultar();
    }

    @FXML
    public void limpar() {
        // Metodo chamada ao clicar no botao "limpar"

        inMatr.getValueFactory().setValue(100000); // Retoma o valor do campo matricula ao valor inicial
        inSenha.setText(""); // Retoma o valor do campo senha ao valor inicial
    }

    @FXML
    public void abrirViewCadastro(ActionEvent event) throws IOException {
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
    public void abrirViewFeed(ActionEvent event) throws IOException {
        // Redireciona para a tela de feed

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/example/ufcgram/view/viewFeed.fxml"
                )
        );

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root);
    }

    @FXML
    public void fazerLogin(ActionEvent event) {
        // A função chama contaSessao para validar o login e, caso retorne true, redireciona para o Feed, caso de false, apresentará mensagem de erro + label de erro

        String senha;
        int matr;

        matr = inMatr.getValue(); // Valor do campo matricula
        senha = inSenha.getText(); // Valor do campo senha

        conta.setMatr(matr); // Inserindo a matrícula no objeto Conta
        conta.setSenha(senha); // Inserindo a senha no objeto Conta

        try{
            conta.fazerLogin(); // Chamando o metodo de criar conta
            abrirViewFeed(event); // Caso a matricula e a senha estejam corretas, é redirecionado para a tela do Feed
        } catch(Exception e) {
            String[] msgError= e.getMessage().split(": "); // Seleciona apenas a mensagem necessária para o usuario
            mensagemController.exibir("Erro no Login", msgError[1], "danger"); // Exibe o componente de Mensage com o texto do erro
        }

    }
}