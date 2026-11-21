package com.example.ufcgram.repository;

import com.example.ufcgram.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDAO {
    // Classe para ligar os valores de Conta que estao no BD

    public Usuario getContabyMatr(int matr) {
        // Esse metodo busca a conta no banco de dados buscando pela matricula (pk)

        String sql = "SELECT * FROM Conta WHERE matr = ?";  // Sql para a busca

        try(Connection conn = ConnectionBD.getConnnection();  // Faz a conexao com o BD
            PreparedStatement stmt = conn.prepareStatement(sql) // Prepara o sql
        ) {
            stmt.setInt(1, matr);  // Substitui o "?" do SQL pela a matricula do parametro
            try(ResultSet rs = stmt.executeQuery()) {
                Usuario usuario = new Usuario();
                while(rs.next()) {
                    usuario.setMatricula(rs.getInt("matr"));  // Armazena o valor da coluna matricula
                    usuario.setNome(rs.getString("nome"));  // Armazena o valor da coluna Nome
                    usuario.setSenha(rs.getString("senha"));  // Armazena o valor da coluna Senha
                    usuario.setEmail(rs.getString("email"));  // Armazena o valor da coluna Email
                    usuario.setIdade(rs.getInt("idade"));  // Armazena o valor da coluna Idade
                    usuario.setCurso(rs.getString("curso")); // Armazena o valor da coluna Curso
                    usuario.setCampus(rs.getString("campus"));  // Armazena o valor da coluna Campus
                    usuario.setBio(rs.getString("bio")); ; // Armazena o valor da coluna Bio
                    usuario.setSemestreInicial(rs.getString("semestreInicial"));  // Armazena o valor da coluna Semestre
                    usuario.setGenero(rs.getString("genero"));  // Armazena o valor da coluna Genero
                }
                if(usuario.getSenha() == null) {
                    throw new RuntimeException("Usuário não encontrado"); // Caso nao exista usuario com a matricula passada, retorna erro
                } else {
                    return usuario; // Caso exista Conta com a matricula, retorna o objeto com as informacoes dela
                }
            }
        } catch(SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: ", e); // Caso o metodo getConnnection()
        }
    }


    //CREATE: é um metodo que cria um objeto de Conta no BD
    //obs: os parametros estao sem tipo pois o banco de dados ja possui os tipos nele (ao criar a tabela la)
    public void create(Usuario usuario) {

        //String com o comando em SQL
        String sql = "INSERT INTO Conta (matr, nome, senha, email, idade, " +
                "curso, campus, bio, semestreInicial, genero) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        //O try-with-resources abre a conexao com o banco de dados e prepara o comando SQL
        //O java fecha automaticamente a conexao e statement quando termina para evitar vazamento de memoria
        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            //preenche cada interrogacao (?) do SQL com os getters de Conta
            stmt.setInt(1, usuario.getMatricula());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getEmail());
            stmt.setInt(5, usuario.getIdade());
            stmt.setString(6, usuario.getCurso());
            stmt.setString(7, usuario.getCampus());
            stmt.setString(8, usuario.getBio());
            stmt.setString(9, usuario.getSemestreInicial());
            stmt.setString(10, usuario.getGenero());

            //Executa INSERT no banco de dados
            stmt.executeUpdate();

        } catch (Exception e) {
            //Se ocorrer, erro: a mensagem é encapsulada em um RuntimeException
            throw new RuntimeException("Erro ao criar conta", e);
        }
    }
    //UPDATE: atualiza a tupla do usuario (identificada pela matricula) com as informacoes do objeto recebido
    public void update(Usuario usuario) {

        String sql = "UPDATE Conta SET nome = ?, senha = ?, email = ?, idade = ?, " +
                "curso = ?, campus = ?, bio = ?, semestreInicial = ?, genero = ? " +
                "WHERE matr = ?";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setInt(4, usuario.getIdade());
            stmt.setString(5, usuario.getCurso());
            stmt.setString(6, usuario.getCampus());
            stmt.setString(7, usuario.getBio());
            stmt.setString(8, usuario.getSemestreInicial());
            stmt.setString(9, usuario.getGenero());
            stmt.setInt(10, usuario.getMatricula()); //Matricula no WHERE (nao é alterada)

            int linhas = stmt.executeUpdate(); //Quantidade de tuplas atualizadas
            if (linhas == 0) {
                throw new RuntimeException("Erro ao editar conta: Usuário não encontrado");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao editar conta: falha ao atualizar no banco de dados", e);
        }
    }

    //DELETE: remove da tabela a conta que possui a matricula recebida
    public void delete(int matricula) {

        String sql = "DELETE FROM Conta WHERE matr = ?";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula); //Matricula no WHERE

            int linhas = stmt.executeUpdate(); //Quantidade de tuplas removidas
            if (linhas == 0) {
                throw new RuntimeException("Erro ao excluir conta: Usuário não encontrado");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir conta: falha ao excluir no banco de dados", e);
        }
    }

    //SEGUIR: insere em seguidores a relacao entre a conta que vai seguir e a conta a ser seguida
    public void seguir(int matriculaSeguidor, int matriculaSeguido) {

        String sql = "INSERT INTO seguidores (matr_seguidor, matr_seguido) VALUES (?, ?)";

        try (Connection conn = ConnectionBD.getConnnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matriculaSeguidor); //Conta que vai seguir
            stmt.setInt(2, matriculaSeguido);  //Conta a ser seguida

            stmt.executeUpdate();

        } catch (SQLException e) {
            // Traduz erros do Banco de Dados
            String estado = (e.getSQLState() == null) ? "" : e.getSQLState();
            switch (estado) {
                case "23505": throw new RuntimeException("Erro ao seguir: Você já segue essa conta", e);
                case "23503": throw new RuntimeException("Erro ao seguir: Conta não encontrada", e);
                case "23514": throw new RuntimeException("Erro ao seguir: Você não pode seguir a si mesmo", e);
                default:      throw new RuntimeException("Erro ao seguir: falha ao salvar no banco de dados", e);
            }
        }
    }
}
