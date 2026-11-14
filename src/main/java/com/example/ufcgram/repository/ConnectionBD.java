package com.example.ufcgram.repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionBD {
    // Classe para fazer conexao com o banco de dados a partir de dados que estao contidos em database.properties
    private static Properties properties = new Properties();

    static {
        try (InputStream input = Files.newInputStream(Path.of("database.properties"))) {
            properties.load(input); // Abre o arquivo database.properties
        } catch (IOException e) {
            throw new RuntimeException("Erro ao acessar as informações para conexão do banco de dados: ", e);
        }
    }

    private static final String URL = properties.getProperty("db.url"); // Pega a informacao do db.url do arquivo database.properties
    private static final String USER =  properties.getProperty("db.user"); // Pega a informacao do db.user do arquivo database.properties
    private static final String PASSWORD =  properties.getProperty("db.password"); // Pega a informacao do db.password do arquivo database.properties


    public static Connection getConnnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD); // Realiza a conexao com o banco de dados, passando a url, usuario e a senha
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados " + e); // Caso a conexao de algum erro, é essa funcao retorna um erro
        }
    }
}
