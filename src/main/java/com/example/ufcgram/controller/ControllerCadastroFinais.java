package com.example.ufcgram.controller;

import com.example.ufcgram.model.Conta;
import com.example.ufcgram.service.Cadastro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerCadastroFinais {

    private Conta conta;

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @FXML
    private TextArea inDescricao; // Atributo que referencia o campo Descricao
    @FXML
    private PasswordField inSenha;  // Atributo que referencia o campo Senha
    @FXML
    private PasswordField inConfirmaSenha;  // Atributo que referencia o campo Confirmar Senha

    @FXML private ControllerMensagem mensagemController; // Cria um objeto para o Controller do Message

    @FXML
    public void abrirLogin(ActionEvent event) throws IOException {
        // Metodo que redireciona para a pagina de dados finais do cadastro

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/ufcgram/view/viewLogin.fxml"
        )
        ); // Abre o arquivo FXML

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        ControllerLogin controller = loader.getController(); // Armazena o controller do FXMl
        controller.abrirMesagemCadastro(); // Carrega a mensagem de criacao de conta na tela de login

        stage.getScene().setRoot(root); // Adiona o arquino na janela
    }

    @FXML
    public void abrirCadastroAcademico(ActionEvent event) throws IOException {
        // Meotodo chamado no botao de voltar, esse metodo redireciona para a tela de cadastro de dados academicos

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/example/ufcgram/view/viewCadastroAcademico.fxml"
                )
        );

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.getScene().setRoot(root);
    }

    @FXML
    public void limpar() {
        inDescricao.setText(""); // Retoma o valor do campo descricao ao valor inicial
        inSenha.setText(""); // Retoma o valor do campo senha ao valor inicial
        inConfirmaSenha.setText(""); // Retoma o valor do campo confirmar senha ao valor inicial
    }

    @FXML
    public void cadastrar(ActionEvent event) {
        // Metodo chamado ao clicar no botao "Cadastrar"

        String bio, senha, confirmarSenha;

        bio = inDescricao.getText(); // Pega o valor do campo Descricao
        senha = inSenha.getText();  // Pega o valor do campo Senha
        confirmarSenha = inConfirmaSenha.getText();  // Pega o valor do campo Confirmar Senha

        if (!Objects.equals(senha, confirmarSenha)) {
            System.out.println("Erro ao confirmar senha: as senhas não coincidem.");
            return;
        }

        if (this.conta == null) {
            System.out.println("Erro: objeto conta está nulo.");
            return;
        }

        try {
            Cadastro cadastroService = new Cadastro();

            cadastroService.cadastrarDados(
                    this.conta.getMatr(),
                    this.conta.getNome(),
                    senha,
                    this.conta.getEmail(),
                    this.conta.getIdade(),
                    this.conta.getCurso(),
                    this.conta.getCampus(),
                    bio,
                    this.conta.getSemestreInicial(),
                    this.conta.getGenero()
            );

            System.out.println("Processo de cadastro finalizado com sucesso!");

            abrirLogin(event);

        } catch (Exception e) {
            String[] msgError= e.getMessage().split(": "); // Seleciona apenas a mensagem necessária para o usuario
            mensagemController.exibir("Erro no Login", msgError[1], "danger"); // Exibe o componente de Mensage com o texto do erro
            System.out.println("Ocorreu um erro ao realizar o cadastro.");
        }
    }
}
