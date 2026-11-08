package com.example.ufcgram.controller;

import com.example.ufcgram.model.Conta;
import com.example.ufcgram.service.Cadastro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.Objects;

public class ControllerCadastroFinais {

    private Conta conta;

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @FXML
    private TextArea inDescricao;
    @FXML
    private PasswordField inSenha;
    @FXML
    private PasswordField inConfirmaSenha;

    @FXML
    public void cadastrar(ActionEvent event) throws IOException {
            String bio, senha, confirmarSenha;

            bio = inDescricao.getText();
            senha = inSenha.getText();
            confirmarSenha = inConfirmaSenha.getText();

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

            } catch (Exception e) {
                System.out.println("Ocorreu um erro ao realizar o cadastro.");
            }
    }
}
