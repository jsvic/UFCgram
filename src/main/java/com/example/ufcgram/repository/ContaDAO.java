package com.example.ufcgram.repository;

import com.example.ufcgram.model.Conta;
import org.postgresql.core.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class ContaDAO {
    public Conta getContabyMatr(int matr) {
        String sql = "SELECT * FROM Conta WHERE matr = ?";

        try(Connection conn = ConnectionBD.getConnnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matr);
            try(ResultSet rs = stmt.executeQuery()) {
                if(!rs.next()) {
                    throw new RuntimeException("Usuário não encontrado");
                }
                Conta conta = new Conta();
                while(rs.next()) {
                    conta.setMatr(rs.getInt("matr"));
                    conta.setNome(rs.getString("nome"));
                    conta.setSenha(rs.getString("senha"));
                    conta.setEmail(rs.getString("email"));
                    conta.setIdade(rs.getInt("idade"));
                    conta.setCurso(rs.getString("curso"));
                    conta.setCampus(rs.getString("campus"));
                    conta.setBio(rs.getString("bio"));
                    conta.setSemestreInicial(rs.getString("semestreInicial"));
                    conta.setGenero(rs.getString("genero"));
                }
                return conta;
            }
        } catch(SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: ", e);
        }
    }


    //CREATE: eh um metodo que cria um objeto de Conta no BD
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
            //Se ocorrer, erro: a mensagem eh encapsulada em um RuntimeException
            throw new RuntimeException("Erro ao criar conta", e);
        }
    }

}
