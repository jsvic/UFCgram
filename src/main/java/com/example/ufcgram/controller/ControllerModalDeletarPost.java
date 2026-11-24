package com.example.ufcgram.controller;

import atlantafx.base.controls.ModalPane;
import atlantafx.base.layout.ModalBox;
import com.example.ufcgram.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerModalDeletarPost {

    @FXML
    private ModalPane modalPane; // Referencia o fundo do modal
    @FXML private ModalBox modalBox; // Referencia o conteudo do modal
    @FXML private CheckBox inConfirmar; // Referencia o campo de confirmacao do modal do deletar conta

    //Controller do componente Mensagem
    @FXML private ControllerMensagem mensagemController;

    private Usuario usuarioSessao;

    @FXML public void initialize() {
        // Metodo que executa assim que o FXMl é iniciado

        modalBox.setOnClose((e) -> modalPane.hide());
    }

    public void setContaSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }

    @FXML public void abrirModal() {
        // Metodo chamado pelo botao excluir conta

        modalPane.show(modalBox);
    }

    @FXML public void deletar(ActionEvent event) throws IOException {
        // Metodo chamado pelo botao excluir no modal
        boolean confirmacao;

        confirmacao = inConfirmar.isSelected(); // Retorna true caso o usuario selecione e false caso contrario

        try {
            //Chama o Model, que verifica a confirmacao e passa a matricula para o Service
            usuarioSessao.excluirUsuario(usuarioSessao.getMatricula(), confirmacao);
        } catch (Exception e) {
            //Retorna o resultado (erro) pelo mensagemController
            mensagemController.exibir("Erro ao Excluir", extrairMensagem(e), "danger");
            return; // A conta nao foi excluida: continua no modal
        }

        modalPane.hide();
    }

    private String extrairMensagem(Exception e) {
        // Transforma "Titulo: mensagem" (do java) em apenas "mensagem" para mostrar ao usuario

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
