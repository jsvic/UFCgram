package com.example.ufcgram.repository;

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
import java.util.Objects;

//Classe para ligar os valores de Publicacao que estao no BD
//Os dois tipos de publicação ficam na mesma tabela, diferenciados pela coluna "tipo" (TEXTO ou EVENTO).
//A coluna localEvento guarda o atributo local da classe PublicacaoEvento.

public class PublicacaoDAO {

    private String colunas = "idPublicacao, matriculaCriador, tipo, dataHoraCriacao, " +
            "texto, titulo, descricao, localEvento, dataEvento";

    //CREATE: salvar uma publicação de texto e guarda no objeto o id gerado pelo banco de dados

    public void criarTexto(PublicacaoTexto publicacao) {

        String sql = "INSERT INTO Publicacao (matriculaCriador, tipo, dataHoraCriacao, texto) " +
                "VALUES (?, 'TEXTO', ?, ?) " +
                "RETURNING idPublicacao";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, publicacao.getMatriculaCriador());
            stmt.setDate(2, Date.valueOf(publicacao.getDataHoraCriacao()));
            stmt.setString(3, publicacao.getTexto());

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                publicacao.setIdPublicacao(rs.getInt("idPublicacao"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar publicação: falha ao salvar no banco de dados", e);
        }
    }

    //CREATE: salvar uma publicação de evento e guarda no objeto o id gerado pelo banco de dados

    public void criarEvento(PublicacaoEvento publicacao) {

        String sql = "INSERT INTO Publicacao (matriculaCriador, tipo, dataHoraCriacao, " +
                "titulo, descricao, localEvento, dataEvento) " +
                "VALUES (?, 'EVENTO', ?, ?, ?, ?, ?) " +
                "RETURNING idPublicacao";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, publicacao.getMatriculaCriador());
            stmt.setDate(2, Date.valueOf(publicacao.getDataHoraCriacao()));
            stmt.setString(3, publicacao.getTitulo());
            stmt.setString(4, publicacao.getDescricao());
            stmt.setString(5, publicacao.getLocal());
            stmt.setDate(6, Date.valueOf(publicacao.getDataEvento()));

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                publicacao.setIdPublicacao(rs.getInt("idPublicacao"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar publicação: falha ao salvar no banco de dados", e);
        }
    }

    //UPDATE: atualizar o texto de uma publicação de texto (somente o criador pode editar)

    public void editarTexto(PublicacaoTexto publicacao) {

        String sql = "UPDATE Publicacao SET texto = ? " +
                "WHERE idPublicacao = ? AND matriculaCriador = ? AND tipo = 'TEXTO'";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, publicacao.getTexto());
            stmt.setInt(2, publicacao.getIdPublicacao());
            stmt.setInt(3, publicacao.getMatriculaCriador());

            int linhas = stmt.executeUpdate(); //Quantidade de tuplas atualizadas
            if (linhas == 0) {
                throw new RuntimeException("Erro ao editar publicação: Publicação não encontrada");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao editar publicação: falha ao atualizar no banco de dados", e);
        }
    }

    //UPDATE: atualizar os dados de uma publicação de evento (somente o criador pode editar)

    public void editarEvento(PublicacaoEvento publicacao) {

        String sql = "UPDATE Publicacao SET titulo = ?, descricao = ?, localEvento = ?, dataEvento = ? " +
                "WHERE idPublicacao = ? AND matriculaCriador = ? AND tipo = 'EVENTO'";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, publicacao.getTitulo());
            stmt.setString(2, publicacao.getDescricao());
            stmt.setString(3, publicacao.getLocal());
            stmt.setDate(4, Date.valueOf(publicacao.getDataEvento()));
            stmt.setInt(5, publicacao.getIdPublicacao());
            stmt.setInt(6, publicacao.getMatriculaCriador());

            int linhas = stmt.executeUpdate(); //Quantidade de tuplas atualizadas
            if (linhas == 0) {
                throw new RuntimeException("Erro ao editar publicação: Publicação não encontrada");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao editar publicação: falha ao atualizar no banco de dados", e);
        }
    }

    //DELETE: remover a publicação (somente o criador pode excluir).
    //As curtidas e os comentários da publicação são removidos em cascata

    public void excluir(int idPublicacao, int matriculaCriador) {

        String sql = "DELETE FROM Publicacao WHERE idPublicacao = ? AND matriculaCriador = ?";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPublicacao);
            stmt.setInt(2, matriculaCriador);

            int linhas = stmt.executeUpdate(); //Quantidade de tuplas removidas
            if (linhas == 0) {
                throw new RuntimeException("Erro ao excluir publicação: Publicação não encontrada");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir publicação: falha ao excluir no banco de dados", e);
        }
    }

    //SELECT: retornar a publicação que possui o id informado

    public Publicacao buscarPorId(int idPublicacao) {
        String sql = "SELECT " + colunas + " " +
                "FROM Publicacao " +
                "WHERE idPublicacao = ?";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPublicacao);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new RuntimeException("Erro ao buscar publicação: Publicação não encontrada");
                }
                return montarPublicacao(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar publicação: falha ao consultar o banco de dados", e);
        }
    }

    //SELECT: retornar as publicações criadas pelo usuário, da mais recente para a mais antiga

    public ArrayList<Publicacao> recuperarPorUsuario(int matriculaCriador) {

        String sql = "SELECT " + colunas + " " +
                "FROM Publicacao " +
                "WHERE matriculaCriador = ? " +
                "ORDER BY idPublicacao DESC";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matriculaCriador);

            try (ResultSet rs = stmt.executeQuery()) {
                return montarLista(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar publicações: falha ao consultar o banco de dados", e);
        }
    }

    //select: retornar as publicações mais recentes das contas que o usuario segue

    public ArrayList<Publicacao> recomendar(int matriculaUsuario, int limite) {

        String sql = "SELECT " + colunas + " " +
                "FROM Publicacao " +
                "WHERE matriculaCriador IN (SELECT matr_seguido FROM seguidores WHERE matr_seguidor = ?) " +
                "ORDER BY idPublicacao DESC " +
                "LIMIT ?";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matriculaUsuario);  //Contas seguidas por este usuario
            stmt.setInt(2, limite);            //Quantidade maxima de publicacoes

            try (ResultSet rs = stmt.executeQuery()) {
                return montarLista(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recomendar publicações: falha ao consultar o banco de dados", e);
        }
    }


    public ArrayList<Publicacao> recuperarPublicacoesCurtidas(int matr) {

        String sql = "select * from publicacao where idPublicacao in (select idPublicacao from curtida where matriculaUsuario = ?  ORDER BY dataHoraInteracao DESC)";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matr);

            try (ResultSet rs = stmt.executeQuery()) {
                return montarLista(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar curtidas: falha ao consultar o banco de dados", e);
        }
    }

    public ArrayList<Publicacao> recuperarPublicacoesComentadas(int matr) {

        String sql = "select * from publicacao where idPublicacao in (select idPublicacao from Comentario where matriculaUsuario = ?  ORDER BY dataHoraInteracao DESC)";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matr);

            try (ResultSet rs = stmt.executeQuery()) {
                return montarLista(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar curtidas: falha ao consultar o banco de dados", e);
        }
    }

    //Transformar todas as linhas do resultado em uma lista de publicações

    private ArrayList<Publicacao> montarLista(ResultSet rs) throws SQLException {
        ArrayList<Publicacao> publicacoes = new ArrayList<>();

        while (rs.next()) {
            publicacoes.add(montarPublicacao(rs));
        }
        return publicacoes;
    }

    //Montar a publicação da linha atual: PublicacaoEvento ou PublicacaoTexto, de acordo com a coluna "tipo"

    private Publicacao montarPublicacao(ResultSet rs) throws SQLException {
        int idPublicacao = rs.getInt("idPublicacao");
        int matriculaCriador = rs.getInt("matriculaCriador");
        LocalDate dataHoraCriacao = rs.getDate("dataHoraCriacao").toLocalDate();

        if (rs.getString("tipo").equals("EVENTO")) {
            return new PublicacaoEvento(
                    idPublicacao,
                    rs.getString("tipo"),
                    matriculaCriador,
                    dataHoraCriacao,
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getString("localEvento"),
                    rs.getDate("dataEvento").toLocalDate()
            );
        }
        return new PublicacaoTexto(
                idPublicacao,
                rs.getString("tipo"),
                matriculaCriador,
                dataHoraCriacao,
                rs.getString("texto"));
    }
}