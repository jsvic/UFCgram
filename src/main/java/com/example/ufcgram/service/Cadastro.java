//Import de bibliotecas

package com.example.ufcgram.service;
import com.example.ufcgram.model.Conta;
import com.example.ufcgram.repository.ContaDAO;

//Classe responsável por unir a montagem feita em ContaCadastro com o salvamento no banco de dados

public class Cadastro {
    public void cadastrarDados(int matr, String nome, String senha, String email, int idade, String curso,
                               String campus, String bio, String semestreInicial, String genero){
    //instanciar para popular os dados
    ContaCadastro contaService = new ContaCadastro();
    //Instanciar para se comunicar com o banco de dados
    ContaDAO dao = new ContaDAO();
    //preencher dados
    contaService.popularDados(matr, nome, senha, email, idade, curso, campus, bio, semestreInicial, genero);
    //pega o objeto pronto
    Conta contaCadastrada = contaService.getContaCadastro();
    //tentativa de salvar no banco de dados
    try {
        dao.create(contaCadastrada);
        System.out.println("Cadastro realizado com sucesso!");
    }
    //se der erro ou o banco de dados recusar, mensagem de erro
    catch (Exception e) {
        throw new RuntimeException("Erro no service: erro ao cadastrar conta", e);
        }
    }
}
