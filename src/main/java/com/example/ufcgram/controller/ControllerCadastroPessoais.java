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
    @FXML private TextField inNome; // Atributo que referencia o campo nome
    @FXML private TextField inEmail; // Atributo que referencia o campo email
    @FXML private ComboBox<String> inGenero; // Atributo que referencia o campo genero
    @FXML private Spinner<Integer> inIdade; // Atributo que referencia o campo idade

    @FXML private ControllerMensagem mensagemController; // Cria um objeto para o Controller do Message

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

        mensagemController.ocultar();
    }

    @FXML
    public void abrirCadastroAcademico(ActionEvent event, Conta conta) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewCadastroAcademico.fxml"
        )
        ); // Abre o arquivo FXMl

        Parent root = loader.load();

        ControllerCadastroAcademico controller = loader.getController(); // Pega o Controller do FXML
        controller.setConta(conta); // Passa o objeto Conta para o Controller

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Adicionar o FXMl à janela
    }

    @FXML
    public void abrirLogin(ActionEvent event) throws IOException {
        // Meotodo chamado no botao de voltar, esse metodo redireciona para a tela de Login

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewLogin.fxml"
        )
        ); // Abre o arquivo FXMl

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root); // Adicionar o FXMl à janela
    }

    @FXML
    public void limpar() {
        // Metodo chamada ao clicar no botao "limpar"

        inNome.setText("");
        inEmail.setText("");
        inGenero.setValue("");
        inIdade.getValueFactory().setValue(10);
    }

    @FXML
    public void continuarCadastro(ActionEvent event) throws IOException {
        // Metodo chamado ao clicar em "Avançar"

        String nome, email, genero;
        int idade;

        nome = inNome.getText(); // Pega o valor do campo Nome
        email = inEmail.getText();  // Pega o valor do campo Email
        genero = inGenero.getValue();  // Pega o valor do campo Genero
        idade = inIdade.getValue();  // Pega o valor do campo Idade

        Conta conta = new Conta();
        conta.setNome(nome);
        conta.setEmail(email);
        conta.setGenero(genero);
        conta.setIdade(idade);

        try {
            System.out.printf("\nInformações:\nnome: %s\nemail: %s\ngenero: %s\nidade: %d\n", nome, email, genero, idade);
            abrirCadastroAcademico(event, conta);
        } catch (Exception e) {
            String[] msgError= e.getMessage().split(": "); // Seleciona apenas a mensagem necessária para o usuario
            mensagemController.exibir("Erro no Login", msgError[1], "danger"); // Exibe o componente de Mensage com o texto do erro
        }

    }
}

