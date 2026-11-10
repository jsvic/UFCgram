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
    private static Properties properties = new Properties();

    static {
        try (InputStream input = Files.newInputStream(Path.of("database.properties"))) {
            properties.load(input); // Abre o arquivo e pega os dados para o Banco de Dados
        } catch (IOException e) {
            throw new RuntimeException("Erro ao acessar as informações para conexão do banco de dados: ", e);
        }
    }

    private static final String URL = properties.getProperty("db.url");
    private static final String USER =  properties.getProperty("db.user");
    private static final String PASSWORD =  properties.getProperty("db.password");


    public static Connection getConnnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD); // Passas as informacoes para a conexao
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados " + e);
        }
    }
}
