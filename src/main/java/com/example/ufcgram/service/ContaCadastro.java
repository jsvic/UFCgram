//Import das bibliotecas

package com.example.ufcgram.service;
import com.example.ufcgram.model.Conta;

//Classe responsável por preencher o objeto Conta com as informações do controller

public class ContaCadastro {
    //instancia do objeto vazio que sera preenchido por metodos
    private Conta contaCadastro;

    //metodo que recebe e preenche informacoes primitivas e usa setters de Conta
    public Conta popularDados(int matr, String nome, String senha, String email, int idade, String curso,
                              String campus, String bio, String semestreInicial, String genero){

        //o objeto sera preenchido e validado
        contaCadastro = new Conta();

        contaCadastro.setMatr(matr);
        contaCadastro.setNome(nome);
        contaCadastro.setSenha(senha);
        contaCadastro.setEmail(email);
        contaCadastro.setIdade(idade);
        contaCadastro.setCurso(curso);
        contaCadastro.setCampus(campus);
        contaCadastro.setBio(bio);
        contaCadastro.setSemestreInicial(semestreInicial);
        contaCadastro.setGenero(genero);

        //Validacoes serao inseridas aqui

        return contaCadastro;
    }
}
