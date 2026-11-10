package com.example.ufcgram.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCadastroPessoais {
    @FXML private TextField inNome; // Atributo que referencia o campo Nome
    @FXML private TextField inEmail; // Atributo que referencia o campo Email
    @FXML private ChoiceBox<String> inGenero; // Atributo que referencia o campo Genero
    @FXML private Spinner<Integer> inIdade; // Atributo que referencia o campo Idade

    @FXML private Label nomeErrorLabel; // Atributo que referencia o Label de mensagem de erro Nome
    @FXML private Label emailErrorLabel; // Atributo que referencia o Label de mensagem de erro Email
    @FXML private Label generoErrorLabel; // Atributo que referencia o Label de mensagem de erro Genero
    @FXML private Label idadeErrorLabel; // Atributo que referencia o Label de mensagem de erro Idade

    @FXML
    public void initialize() {
        // Metodo que é chamado automaticamente ao abrir o FXML

        inIdade.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        10,
                        100

                )
        ); // Configura o campo Idade

        inGenero.getItems().addAll(
                "Masculino",
                "Feminino",
                "Não Binário"
        ); // Configura o campo Genero
    }

    @FXML
    public void abrirCadastroAcademico(ActionEvent event) throws IOException {
        // Redireciona para a tela de cadastro dos dados academico

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/example/ufcgram/view/viewCadastroAcademico.fxml"
                )
        ); // Carrega o arquivo FXML

        Stage stage = (Stage) ((Node) event.getSource()) // Carrega a janela
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Insere a pagina na janela
    }

    @FXML
    public void continuarCadastro(ActionEvent event) throws IOException {
        // Metodo chamado ao clicar em "Avançar"

        String nome, email, genero;
        int idade;

        nome = inNome.getText(); // Pega o valor do campo Nome
        email = inEmail.getText(); // Pega o valor do campo Email
        genero = inGenero.getValue(); // Pega o valor do campo Genero
        idade = inIdade.getValue(); // Pega o valor do campo Idade

        System.out.printf("\nInformações:\nnome: %s\nemail: %s\ngenero: %s\nidade: %d\n", nome, email, genero, idade);
        abrirCadastroAcademico(event); // Chama o metodo para redirecionar a tela

    }
}

