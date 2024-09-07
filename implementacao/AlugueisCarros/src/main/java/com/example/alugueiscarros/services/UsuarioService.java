package com.example.alugueiscarros.services;

import com.example.alugueiscarros.database.DatabaseManager;
import com.example.alugueiscarros.models.UsuarioModel;

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

    // Adiciona um novo usuário
    public static void addUsuario(UsuarioModel usuario, UsuarioCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
                return;
            }

            String sql = "INSERT INTO Usuario (nome, rg, cpf, endereco, profissao) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                connection.setAutoCommit(false);
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getRg());
                stmt.setString(3, usuario.getCpf());
                stmt.setString(4, usuario.getEndereco());
                stmt.setString(5, usuario.getProfissao());
                stmt.executeUpdate();
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

    // Atualiza um usuário existente
    public static void updateUsuario(UsuarioModel usuario, UsuarioCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
                return;
            }

            String sql = "UPDATE Usuario SET nome = ?, rg = ?, cpf = ?, endereco = ?, profissao = ? WHERE idUsuario = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getRg());
                stmt.setString(3, usuario.getCpf());
                stmt.setString(4, usuario.getEndereco());
                stmt.setString(5, usuario.getProfissao());
                stmt.setInt(6, Integer.parseInt(usuario.getIdUsuario()));
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

    // Deleta um usuário
    public static void deleteUsuario(String usuarioId, UsuarioCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
                return;
            }

            String sql = "DELETE FROM Usuario WHERE idUsuario = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, Integer.parseInt(usuarioId));
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
                return;
            }

            String sql = "SELECT * FROM Usuario";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                List<UsuarioModel> usuarios = new ArrayList<>(rs.getFetchSize());
                while (rs.next()) {
                    UsuarioModel usuario = new UsuarioModel();
                    usuario.setIdUsuario(String.valueOf(rs.getInt("idUsuario")));
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


    public static void getUsuario(String usuarioId, QueryUsuarioCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, null);
                return;
            }

            String sql = "SELECT * FROM Usuario WHERE idUsuario = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, Integer.parseInt(usuarioId));
                ResultSet rs = stmt.executeQuery();
                List<UsuarioModel> usuarios = new ArrayList<>();
                if (rs.next()) {
                    UsuarioModel usuario = new UsuarioModel();
                    usuario.setIdUsuario(String.valueOf(rs.getInt("idUsuario")));
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
