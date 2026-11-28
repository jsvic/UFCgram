package com.example.ufcgram.repository;

import com.example.ufcgram.model.Curtida;
import com.example.ufcgram.model.Publicacao;
import com.example.ufcgram.model.PublicacaoEvento;
import com.example.ufcgram.model.PublicacaoTexto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

//Classe para ligar os valores de Curtida que estao no BD

public class CurtidaDAO {

    //INSERT: salvar a curtida de um usuario em uma publicacao

    public void inserir(Curtida curtida) {

        String sql = "INSERT INTO Curtida (idPublicacao, matriculaUsuario, dataHoraInteracao) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, curtida.getIdPublicacao());
            stmt.setInt(2, curtida.getMatriculaUsuario());
            stmt.setDate(3, Date.valueOf(curtida.getDataHoraInteracao()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            String estado = (e.getSQLState() == null) ? "" : e.getSQLState();
            switch (estado) {
                case "23505": throw new RuntimeException("Erro ao curtir: Você já curtiu esta publicação", e);
                case "23503": throw new RuntimeException("Erro ao curtir: Publicação não encontrada", e);
                default:      throw new RuntimeException("Erro ao curtir: falha ao salvar no banco de dados", e);
            }
        }
    }

    //DELETE: remover a curtida de um usuario em uma publicacao

    public void remover(int idPublicacao, int matriculaUsuario) {

        String sql = "DELETE FROM Curtida WHERE idPublicacao = ? AND matriculaUsuario = ?";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPublicacao);
            stmt.setInt(2, matriculaUsuario);

            int linhas = stmt.executeUpdate(); //Quantidade de tuplas removidas
            if (linhas == 0) {
                throw new RuntimeException("Erro ao remover curtida: Curtida não encontrada");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover curtida: falha ao excluir no banco de dados", e);
        }
    }

    //RECUPERAR: retornar as curtidas feitas por um usuario, da mais recente para a mais antiga

    public ArrayList<Curtida> recuperarPorUsuario(int matriculaUsuario) {

        String sql = "SELECT idPublicacao, matriculaUsuario, dataHoraInteracao " +
                "FROM Curtida " +
                "WHERE matriculaUsuario = ? " +
                "ORDER BY dataHoraInteracao DESC, idPublicacao DESC";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matriculaUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                return montarLista(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar curtidas: falha ao consultar o banco de dados", e);
        }
    }

    //RECUPERAR: retorna as curtidas de uma publicacao

    public ArrayList<Curtida> recuperarPorPublicacao(int idPublicacao) {

        String sql = "SELECT idPublicacao, matriculaUsuario, dataHoraInteracao " +
                "FROM Curtida " +
                "WHERE idPublicacao = ? " +
                "ORDER BY dataHoraInteracao DESC, matriculaUsuario ASC";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPublicacao);

            try (ResultSet rs = stmt.executeQuery()) {
                return montarLista(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar curtidas: falha ao consultar o banco de dados", e);
        }
    }

    //Transformar todas as linhas do resultado em uma lista de curtidas

    private ArrayList<Curtida> montarLista(ResultSet rs) throws SQLException {
        ArrayList<Curtida> curtidas = new ArrayList<>();

        while (rs.next()) {
            curtidas.add(new Curtida(
                    rs.getInt("matriculaUsuario"),
                    rs.getInt("idPublicacao"),
                    rs.getDate("dataHoraInteracao").toLocalDate()
            ));
        }
        return curtidas;
    }
}
