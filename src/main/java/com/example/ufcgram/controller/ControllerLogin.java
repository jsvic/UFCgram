package com.example.ufcgram.controller;

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

public class ControllerLogin {

    @FXML private Spinner<Integer> inMatr;
    @FXML private PasswordField inSenha;

    @FXML private Label matrErrorLabel;
    @FXML private Label senhaErrorLabel;

    private static Login contaSessao = new Login();

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
    public void fazerLogin() {
        // A função chama contaSessao para validar o login e, caso retorne true, redireciona para o Feed, caso de false, apresentará mensagem de erro + label de erro

        String senha;
        int matr;

        matr = inMatr.getValue(); // Valor do campo matricula
        senha = inSenha.getText(); // Valor do campo senha

        try{
            contaSessao.login(matr, senha);
            System.out.print("\nLOGIN OK\n"); // print temporário
        } catch(Exception e) {
            System.out.printf("\nErro no login: %s\n",e.getMessage()); // print temporário
        }

    }
}