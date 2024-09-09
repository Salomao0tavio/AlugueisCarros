package com.example.alugueiscarros.services;

import com.example.alugueiscarros.database.DatabaseManager;
import com.example.alugueiscarros.models.ContratoModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratoService {

    @FunctionalInterface
    public interface ContratoCallback {
        void onResult(boolean success, String message);
    }

    @FunctionalInterface
    public interface QueryContratoCallback {
        void onResult(boolean success, List<ContratoModel> contratos);
    }

    // Adiciona um novo contrato
    public static void addContrato(ContratoModel contrato, ContratoCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
                return;
            }

            String sql = "INSERT INTO Contrato (tipo, dataInicio, dataFim, valor, agente_id, pedido_id) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                connection.setAutoCommit(false);
                stmt.setString(1, contrato.getTipo());
                stmt.setDate(2, new java.sql.Date(contrato.getDataInicio().getTime()));
                stmt.setDate(3, new java.sql.Date(contrato.getDataFim().getTime()));
                stmt.setDouble(4, contrato.getValor());
                stmt.setInt(5, contrato.getAgenteId());
                stmt.setInt(6, contrato.getPedidoId());
                stmt.executeUpdate();
                connection.commit();
                callback.onResult(true, "Contrato adicionado com sucesso.");
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                callback.onResult(false, "Erro ao adicionar contrato: " + e.getMessage());
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Atualiza um contrato existente
    public static void updateContrato(ContratoModel contrato, ContratoCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
                return;
            }

            String sql = "UPDATE Contrato SET tipo = ?, dataInicio = ?, dataFim = ?, valor = ?, agente_id = ?, pedido_id = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, contrato.getTipo());
                stmt.setDate(2, new java.sql.Date(contrato.getDataInicio().getTime()));
                stmt.setDate(3, new java.sql.Date(contrato.getDataFim().getTime()));
                stmt.setDouble(4, contrato.getValor());
                stmt.setInt(5, contrato.getAgenteId());
                stmt.setInt(6, contrato.getPedidoId());
                stmt.setInt(7, contrato.getId());
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    callback.onResult(false, "Nenhum contrato atualizado, possível ID inexistente.");
                } else {
                    callback.onResult(true, "Contrato atualizado com sucesso.");
                }
            } catch (SQLException e) {
                callback.onResult(false, "Erro ao atualizar contrato: " + e.getMessage());
            }
        });
    }

    // Deleta um contrato
    public static void deleteContrato(int contratoId, ContratoCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
                return;
            }

            String sql = "DELETE FROM Contrato WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, contratoId);
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    callback.onResult(false, "Nenhum contrato deletado, possível ID inexistente.");
                } else {
                    callback.onResult(true, "Contrato deletado com sucesso.");
                }
            } catch (SQLException e) {
                callback.onResult(false, "Erro ao deletar contrato: " + e.getMessage());
            }
        });
    }

    // Lista todos os contratos
    public static void listContratos(QueryContratoCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, null);
                return;
            }

            String sql = "SELECT * FROM Contrato";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                List<ContratoModel> contratos = new ArrayList<>(rs.getFetchSize());
                while (rs.next()) {
                    ContratoModel contrato = new ContratoModel();
                    contrato.setId(rs.getInt("id"));
                    contrato.setTipo(rs.getString("tipo"));
                    contrato.setDataInicio(rs.getDate("dataInicio"));
                    contrato.setDataFim(rs.getDate("dataFim"));
                    contrato.setValor(rs.getDouble("valor"));
                    contrato.setAgenteId(rs.getInt("agente_id"));
                    contrato.setPedidoId(rs.getInt("pedido_id"));
                    contratos.add(contrato);
                }
                callback.onResult(true, contratos);
            } catch (SQLException e) {
                callback.onResult(false, null);
            }
        });
    }

    
    public static void getContrato(int contratoId, QueryContratoCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, null);
                return;
            }

            String sql = "SELECT * FROM Contrato WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, contratoId);
                ResultSet rs = stmt.executeQuery();
                List<ContratoModel> contratos = new ArrayList<>();
                if (rs.next()) {
                    ContratoModel contrato = new ContratoModel();
                    contrato.setId(rs.getInt("id"));
                    contrato.setTipo(rs.getString("tipo"));
                    contrato.setDataInicio(rs.getDate("dataInicio"));
                    contrato.setDataFim(rs.getDate("dataFim"));
                    contrato.setValor(rs.getDouble("valor"));
                    contrato.setAgenteId(rs.getInt("agente_id"));
                    contrato.setPedidoId(rs.getInt("pedido_id"));
                    contratos.add(contrato);
                }
                callback.onResult(true, contratos);
            } catch (SQLException e) {
                callback.onResult(false, null);
            }
        });
    }
}
