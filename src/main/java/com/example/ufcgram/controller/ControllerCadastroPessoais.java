package com.example.ufcgram.controller;

import com.example.ufcgram.model.Conta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCadastroPessoais {
    @FXML private TextField inNome;
    @FXML private TextField inEmail;
    @FXML private ChoiceBox<String> inGenero;
    @FXML private Spinner<Integer> inIdade;

    @FXML private Label nomeErrorLabel;
    @FXML private Label emailErrorLabel;
    @FXML private Label generoErrorLabel;
    @FXML private Label idadeErrorLabel;

    @FXML
    public void initialize() {
        inIdade.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        10,
                        100

                )
        );

        inGenero.getItems().addAll(
                "Masculino",
                "Feminino",
                "Não Binário"
        );
    }

    @FXML
    public void abrirCadastroAcademico(ActionEvent event, Conta conta) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewCadastroAcademico.fxml"
                )
        );
        Parent root = loader.load();

        ControllerCadastroAcademico controller = loader.getController();
        controller.setConta(conta);

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root);
    }

    @FXML
    public void continuarCadastro(ActionEvent event) throws IOException {
        String nome, email, genero;
        int idade;

        nome = inNome.getText();
        email = inEmail.getText();
        genero = inGenero.getValue();
        idade = inIdade.getValue();

        Conta conta = new Conta();
        conta.setNome(nome);
        conta.setEmail(email);
        conta.setGenero(genero);
        conta.setIdade(idade);

        System.out.printf("\nInformações:\nnome: %s\nemail: %s\ngenero: %s\nidade: %d\n", nome, email, genero, idade);
        abrirCadastroAcademico(event, conta);

    }
}

