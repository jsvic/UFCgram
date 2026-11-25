package com.example.ufcgram.model;

//imports

import com.example.ufcgram.service.Cadastro;

//Classe com dados pessoais do aluno.

public class Aluno {
    protected int matricula;
    protected String nome;
    protected String email;
    protected int idade;
    protected String genero;

    public Aluno() {
        // Construtor sem parâmetro
    }

    public Aluno(int matr, String nome, String email, int idade, String genero) {
        // Construtor com parâmetro
        this.matricula = matr;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.genero = genero;
    }

    public void criarUsuario(Usuario usuario, String senha, String confirmarSenha) {
        Cadastro service = new Cadastro();
        service.criarUsuario(usuario, senha, confirmarSenha);
    }
    public void editarUsuario(Usuario usuario, String senha, String confirmarSenha) {

        Cadastro service = new Cadastro();
        service.editarUsuario(usuario, senha, confirmarSenha);

        // Atualiza os dados deste objeto após a edição bem-sucedida.
        copiarDados(usuario);
    }

    public void excluirUsuario(int matricula, boolean confirmacao) {
        // Modal de Excluir: so passa a matricula para o Service se o usuario marcou a confirmacao
        if (!confirmacao) {
            throw new RuntimeException("Confirmação necessária: Marque a opção de confirmação para excluir a conta");
        }

        Cadastro service = new Cadastro();
        service.excluirUsuario(matricula);
    }

    protected void copiarDados(Usuario outro) {
        // Copia os dados pessoais editaveis (a matricula nao muda). Usuario sobrescreve para copiar os demais
        this.nome = outro.getNome();
        this.email = outro.getEmail();
        this.idade = outro.getIdade();
        this.genero = outro.getGenero();
    }

    //Getters:

    public int getMatricula() {
        return matricula;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public int getIdade() {
        return idade;
    }
    public String getGenero() {
        return genero;
    }

    //Setters:

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
}
