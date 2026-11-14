package com.example.ufcgram.repository;

import com.example.ufcgram.model.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class ContaDAO {
    // Classe para ligar os valores de Conta que estao no BD

    public Conta getContabyMatr(int matr) {
        // Esse metodo busca a conta no banco de dados buscando pela matricula (pk)

        String sql = "SELECT * FROM Conta WHERE matr = ?";  // Sql para a busca

        try(Connection conn = ConnectionBD.getConnnection();  // Faz a conexao com o BD
            PreparedStatement stmt = conn.prepareStatement(sql) // Prepara o sql
        ) {
            stmt.setInt(1, matr);  // Substitui o "?" do SQL pela a matricula do parametro
            try(ResultSet rs = stmt.executeQuery()) {
                Conta conta = new Conta();
                while(rs.next()) {
                    conta.setMatr(rs.getInt("matr"));  // Armazena o valor da coluna matricula
                    conta.setNome(rs.getString("nome"));  // Armazena o valor da coluna Nome
                    conta.setSenha(rs.getString("senha"));  // Armazena o valor da coluna Senha
                    conta.setEmail(rs.getString("email"));  // Armazena o valor da coluna Email
                    conta.setIdade(rs.getInt("idade"));  // Armazena o valor da coluna Idade
                    conta.setCurso(rs.getString("curso")); // Armazena o valor da coluna Curso
                    conta.setCampus(rs.getString("campus"));  // Armazena o valor da coluna Campus
                    conta.setBio(rs.getString("bio")); ; // Armazena o valor da coluna Bio
                    conta.setSemestreInicial(rs.getString("semestreInicial"));  // Armazena o valor da coluna Semestre
                    conta.setGenero(rs.getString("genero"));  // Armazena o valor da coluna Genero
                }
                if(conta.getSenha() == null) {
                    throw new RuntimeException("Usuário não encontrado"); // Caso nao exista usuario com a matricula passada, retorna erro
                } else {
                    return conta; // Caso exista Conta com a matricula, retorna o objeto com as informacoes dela
                }
            }
        } catch(SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: ", e); // Caso o metodo getConnnection()
        }
    }


    //CREATE: é um metodo que cria um objeto de Conta no BD
    //obs: os parametros estao sem tipo pois o banco de dados ja possui os tipos nele (ao criar a tabela la)
    public void create(Conta conta) {

        //String com o comando em SQL
        String sql = "INSERT INTO Conta (matr, nome, senha, email, idade, " +
                "curso, campus, bio, semestreInicial, genero) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        //O try-with-resources abre a conexao com o banco de dados e prepara o comando SQL
        //O java fecha automaticamente a conexao e statement quando termina para evitar vazamento de memoria
        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            //preenche cada interrogacao (?) do SQL com os getters de Conta
            stmt.setInt(1, conta.getMatr());
            stmt.setString(2, conta.getNome());
            stmt.setString(3, conta.getSenha());
            stmt.setString(4, conta.getEmail());
            stmt.setInt(5, conta.getIdade());
            stmt.setString(6, conta.getCurso());
            stmt.setString(7, conta.getCampus());
            stmt.setString(8, conta.getBio());
            stmt.setString(9, conta.getSemestreInicial());
            stmt.setString(10, conta.getGenero());

            //Executa INSERT no banco de dados
            stmt.executeUpdate();

        } catch (Exception e) {
            //Se ocorrer, erro: a mensagem é encapsulada em um RuntimeException
            throw new RuntimeException("Erro ao criar conta", e);
        }
    }

}
