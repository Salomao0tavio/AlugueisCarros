package com.example.alugueiscarros.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mariadb://localhost:3306/sistema_aluguel?user=root&password=nova_senha";
    private static final String USER = "root";
    private static final String PASSWORD = "nova_senha";

    private static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    @FunctionalInterface
    public interface ConnectionCallback {
        void handle(boolean success, Connection connection);
    }

    public static void execute(ConnectionCallback callback) {
        try (Connection connection = openConnection()) {
            callback.handle(true, connection);
            closeConnection(connection);
        } catch (SQLException e) {
            callback.handle(false, null);
            e.printStackTrace();
        }
    }
}