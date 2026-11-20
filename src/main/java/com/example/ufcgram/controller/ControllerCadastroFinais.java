package com.example.ufcgram.controller;

import com.example.ufcgram.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCadastroFinais {

    private Usuario usuario;

    public void setConta(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    private TextArea inDescricao; // Atributo que referencia o campo Descricao
    @FXML
    private PasswordField inSenha;  // Atributo que referencia o campo Senha
    @FXML
    private PasswordField inConfirmaSenha;  // Atributo que referencia o campo Confirmar Senha

    @FXML
    private ControllerMensagem mensagemController; // Cria um objeto para o Controller do Message

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

        try {
            // Verifica se o objeto Usuario foi recebido.
            if (this.usuario == null) {
                throw new RuntimeException(
                        "Não foi possível concluir o cadastro. Volte e preencha os dados novamente."
                );
            }
            //Adiciona a descrição ao usuário.
            this.usuario.setBio(bio);

            //Encaminha os dados para o Model e, posteriormente, para o Service.
            this.usuario.criarUsuario(this.usuario, senha, confirmarSenha);
            System.out.println("Processo de cadastro finalizado com sucesso!");
            abrirLogin(event);

        } catch (Exception e) {
            mensagemController.exibir(
                    "Erro no Cadastro",
                    extrairMensagem(e),
                    "danger"
            );
            System.out.println("Ocorreu um erro ao realizar o cadastro.");
        }
    }
    //Metodo de extração de mensagem
    private String extrairMensagem(Exception e) {

        String textoCompleto = e.getMessage();
        if (textoCompleto == null) {
            return "Erro desconhecido";
        }
        String[] partes = textoCompleto.split(": ");
        if (partes.length < 2) {
            return textoCompleto;
        }
        return partes[1];
    }
}
