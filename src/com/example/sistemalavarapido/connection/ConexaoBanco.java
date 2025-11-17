package com.example.sistemalavarapido.connection;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class ConexaoBanco {
    // Variáveis finais e estáticas
    private static final String URL;
    private static final String USUARIO;
    private static final String SENHA;

    // Bloco estático para inicialização segura
    static {
        try {
            Dotenv dotenv = Dotenv.load();
            URL = dotenv.get("DB_URL");
            USUARIO = dotenv.get("DB_USER");
            SENHA = dotenv.get("DB_PASSWORD");
        } catch (Exception e) {
            // Lança um erro claro se o .env ou a variável não for encontrada
            System.err.println("ERRO: Não foi possível carregar as variáveis de ambiente do .env");
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        // Agora, se o bloco estático inicializou com sucesso, a conexão será feita
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}