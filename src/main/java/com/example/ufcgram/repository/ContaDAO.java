package com.example.ufcgram.repository;

import com.example.ufcgram.model.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    // Crie um metodo que receba uma Conta e faça um insert com as informacoes dela
}
