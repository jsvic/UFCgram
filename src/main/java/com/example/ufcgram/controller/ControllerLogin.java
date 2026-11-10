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

    @FXML private Spinner<Integer> inMatr; // Atributo que referência o campo Matricula
    @FXML private PasswordField inSenha; // Atributo que referência o campo Senha

    @FXML private Label matrErrorLabel; // Atributo que referência o Label para a mensagem de erro no campo Matricula
    @FXML private Label senhaErrorLabel; // Atributo que referência o Label para a mensagem de erro no campo Senha

    private static Login contaSessao = new Login(); // Cria uma instância da classe da session

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
        // Redireciona para a tela de cadastro para os dados pessoais

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/example/ufcgram/view/viewCadastroPessoais.fxml"
                )
        ); // Carrega o arquivo FXML

        Stage stage = (Stage) ((Node) event.getSource()) // Carrega a janela
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Insere a pagina na janela
    }

    @FXML
    public void abrirViewFeed(ActionEvent event) throws IOException {
        // Redireciona para a tela do Feed

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/example/ufcgram/view/viewFeed.fxml"
                )
        ); // Abre o novo FXML

        Stage stage = (Stage) ((Node) event.getSource()) // Carrega a janela
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root);  // Insere a pagina na janela
    }

    @FXML
    public void fazerLogin(ActionEvent event) {
        // Esse metodo é chamado ao o usuário clicar no botão "Login"
        String senha;
        int matr;

        matr = inMatr.getValue(); // Valor do campo matricula
        senha = inSenha.getText(); // Valor do campo senha

        try{
            contaSessao.login(matr, senha); // Chama o session e valida os dados
            System.out.print("\nLOGIN OK\n"); // print temporário
            abrirViewFeed(event); // Chama o metodo para redirecionar a tela
         } catch(Exception e) {
            System.out.printf("\nErro no login: %s\n",e.getMessage()); // print temporário
        }

    }
}