package com.example.ufcgram.repository;

import com.example.ufcgram.model.Comentario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Acessar os dados da tabela Comentario (um comentário por usuário em cada publicação).

public class ComentarioDAO {

    //INSERT: salva o comentario de um usuario em uma publicacao

    public void inserir(Comentario comentario) {

        String sql = "INSERT INTO comentario (idPublicacao, matriculaUsuario, dataHoraInteracao, texto) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, comentario.getIdPublicacao());
            stmt.setInt(2, comentario.getMatriculaUsuario());
            stmt.setDate(3, Date.valueOf(comentario.getDataHoraInteracao()));
            stmt.setString(4, comentario.getTexto());

            stmt.executeUpdate();

        } catch (SQLException e) {
            String estado = (e.getSQLState() == null) ? "" : e.getSQLState();
            switch (estado) {
                case "23505": throw new RuntimeException("Erro ao comentar: Você já comentou nesta publicação, edite o seu comentário", e);
                case "23503": throw new RuntimeException("Erro ao comentar: Publicação não encontrada", e);
                default:      throw new RuntimeException("Erro ao comentar: falha ao salvar no banco de dados", e);
            }
        }
    }

    //UPDATE: atualiza o texto do comentario de um usuario em uma publicacao

    public void editar(Comentario comentario) {

        String sql = "UPDATE Comentario SET texto = ? WHERE idComentario = ?";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comentario.getTexto());
            stmt.setInt(2, comentario.getIdComentario());

            int linhas = stmt.executeUpdate(); //Quantidade de tuplas atualizadas
            if (linhas == 0) {
                throw new RuntimeException("Erro ao editar comentário: Comentário não encontrado");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao editar comentário: falha ao atualizar no banco de dados", e);
        }
    }

    //DELETE: remove o comentario de um usuario em uma publicacao

    public void remover(int idComentario) {

        String sql = "DELETE FROM Comentario WHERE idComentario = ?";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idComentario);

            int linhas = stmt.executeUpdate(); //Quantidade de tuplas removidas
            if (linhas == 0) {
                throw new RuntimeException("Erro ao remover comentário: Comentário não encontrado");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover comentário: falha ao excluir no banco de dados", e);
        }
    }

    //SELECT: retornar os comentarios feitos por um usuario, do mais recente para o mais antigo

    public ArrayList<Comentario> recuperarPorUsuario(int matriculaUsuario) {

        String sql = "SELECT idPublicacao, matriculaUsuario, dataHoraInteracao, texto " +
                "FROM comentario " +
                "WHERE matriculaUsuario = ? " +
                "ORDER BY dataHoraInteracao DESC, idPublicacao DESC";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matriculaUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                return montarLista(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar comentários: falha ao consultar o banco de dados", e);
        }
    }

    //SELECT: retornar os comentarios de uma publicação

    public ArrayList<Comentario> recuperarPorPublicacao(int idPublicacao) {

        String sql = "SELECT * " +
                "FROM comentario " +
                "WHERE idPublicacao = ? " +
                "ORDER BY dataHoraInteracao DESC, matriculaUsuario ASC";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPublicacao);

            try (ResultSet rs = stmt.executeQuery()) {
                return montarLista(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar comentários: falha ao consultar o banco de dados", e);
        }
    }

    //Transformar as linhas do resultado em uma lista de comentarios:

    private ArrayList<Comentario> montarLista(ResultSet rs) throws SQLException {
        ArrayList<Comentario> comentarios = new ArrayList<>();

        while (rs.next()) {
            comentarios.add(new Comentario(
                    rs.getInt("idComentario"),
                    rs.getInt("matriculaUsuario"),
                    rs.getInt("idPublicacao"),
                    rs.getDate("dataHoraInteracao").toLocalDate(),
                    rs.getString("texto")
            ));
        }
        return comentarios;
    }
}
