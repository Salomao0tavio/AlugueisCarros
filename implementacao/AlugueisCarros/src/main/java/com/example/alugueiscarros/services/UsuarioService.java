package com.example.alugueiscarros.services;


import com.example.alugueiscarros.database.DatabaseManager;
import com.example.alugueiscarros.models.*;
import com.example.alugueiscarros.services.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    @FunctionalInterface
    public interface UsuarioCallback {
        void onResult(boolean success, String message);
    }

    @FunctionalInterface
    public interface QueryUsuarioCallback {
        void onResult(boolean success, List<UsuarioModel> usuarios);
    }

    public static void addUsuario(UsuarioModel usuario, UsuarioCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
            }

            try {
                connection.setAutoCommit(false);
                String sql = "INSERT INTO usuario (idUsuario, nome, rg, cpf, endereco, profissao) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, usuario.getIdUsuario());
                    stmt.setString(2, usuario.getNome());
                    stmt.setString(3, usuario.getRg());
                    stmt.setString(4, usuario.getCpf());
                    stmt.setString(5, usuario.getEndereco());
                    stmt.setString(6, usuario.getProfissao());
                    stmt.executeUpdate();
                }
                connection.commit();
                callback.onResult(true, "Usuário adicionado com sucesso.");
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                callback.onResult(false, "Erro ao adicionar usuário: " + e.getMessage());
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void updateUsuario(UsuarioModel usuario, UsuarioCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
            }

            String sql = "UPDATE usuario SET nome = ?, rg = ?, cpf = ?, endereco = ?, profissao = ? WHERE idUsuario = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getRg());
                stmt.setString(3, usuario.getCpf());
                stmt.setString(4, usuario.getEndereco());
                stmt.setString(5, usuario.getProfissao());
                stmt.setString(6, usuario.getIdUsuario());
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    callback.onResult(false, "Nenhum usuário atualizado, possível ID inexistente.");
                } else {
                    callback.onResult(true, "Usuário atualizado com sucesso.");
                }
            } catch (SQLException e) {
                callback.onResult(false, "Erro ao atualizar usuário: " + e.getMessage());
            }
        });
    }

    public static void deleteUsuario(String usuarioId, UsuarioCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
            }
            String sql = "DELETE FROM usuario WHERE idUsuario = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, usuarioId);
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    callback.onResult(false, "Nenhum usuário deletado, possível ID inexistente.");
                } else {
                    callback.onResult(true, "Usuário deletado com sucesso.");
                }
            } catch (SQLException e) {
                callback.onResult(false, "Erro ao deletar usuário: " + e.getMessage());
            }
        });
    }

    public static void listUsuarios(QueryUsuarioCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, null);
            }
            String sql = "SELECT * FROM usuario";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                List<UsuarioModel> usuarios = new ArrayList<>(rs.getFetchSize());
                while (rs.next()) {
                    UsuarioModel usuario = new UsuarioModel();
                    usuario.setIdUsuario(rs.getString("idUsuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setRg(rs.getString("rg"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setEndereco(rs.getString("endereco"));
                    usuario.setProfissao(rs.getString("profissao"));
                    usuarios.add(usuario);
                }
                callback.onResult(true, usuarios);
            } catch (SQLException e) {
                callback.onResult(false, null);
            }
        });
    }
}