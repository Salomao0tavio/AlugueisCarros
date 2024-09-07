package com.example.alugueiscarros.services;

import com.example.alugueiscarros.database.DatabaseManager;
import com.example.alugueiscarros.models.VeiculoModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoService {

    @FunctionalInterface
    public interface VeiculoCallback {
        void onResult(boolean success, String message);
    }

    @FunctionalInterface
    public interface QueryVeiculoCallback {
        void onResult(boolean success, List<VeiculoModel> veiculos);
    }

    // Adiciona um novo veículo
    public static void addVeiculo(VeiculoModel veiculo, VeiculoCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
                return;
            }

            String sql = "INSERT INTO Veiculo (matricula, ano, marca, modelo, placa) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                connection.setAutoCommit(false);
                stmt.setString(1, veiculo.getMatricula());
                stmt.setInt(2, veiculo.getAno());
                stmt.setString(3, veiculo.getMarca());
                stmt.setString(4, veiculo.getModelo());
                stmt.setString(5, veiculo.getPlaca());
                stmt.executeUpdate();
                connection.commit();
                callback.onResult(true, "Veículo adicionado com sucesso.");
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                callback.onResult(false, "Erro ao adicionar veículo: " + e.getMessage());
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Atualiza um veículo existente
    public static void updateVeiculo(VeiculoModel veiculo, VeiculoCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
                return;
            }

            String sql = "UPDATE Veiculo SET matricula = ?, ano = ?, marca = ?, modelo = ?, placa = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, veiculo.getMatricula());
                stmt.setInt(2, veiculo.getAno());
                stmt.setString(3, veiculo.getMarca());
                stmt.setString(4, veiculo.getModelo());
                stmt.setString(5, veiculo.getPlaca());
                stmt.setInt(6, veiculo.getIdVeiculo());
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    callback.onResult(false, "Nenhum veículo atualizado, possível ID inexistente.");
                } else {
                    callback.onResult(true, "Veículo atualizado com sucesso.");
                }
            } catch (SQLException e) {
                callback.onResult(false, "Erro ao atualizar veículo: " + e.getMessage());
            }
        });
    }

    // Deleta um veículo
    public static void deleteVeiculo(int veiculoId, VeiculoCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, "Falha ao conectar ao banco de dados.");
                return;
            }

            String sql = "DELETE FROM Veiculo WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, veiculoId);
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    callback.onResult(false, "Nenhum veículo deletado, possível ID inexistente.");
                } else {
                    callback.onResult(true, "Veículo deletado com sucesso.");
                }
            } catch (SQLException e) {
                callback.onResult(false, "Erro ao deletar veículo: " + e.getMessage());
            }
        });
    }

    // Lista todos os veículos
    public static void listVeiculos(QueryVeiculoCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, null);
                return;
            }

            String sql = "SELECT * FROM Veiculo";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                List<VeiculoModel> veiculos = new ArrayList<>(rs.getFetchSize());
                while (rs.next()) {
                    VeiculoModel veiculo = new VeiculoModel();
                    veiculo.setIdVeiculo(rs.getInt("id"));
                    veiculo.setMatricula(rs.getString("matricula"));
                    veiculo.setAno(rs.getInt("ano"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculos.add(veiculo);
                }
                callback.onResult(true, veiculos);
            } catch (SQLException e) {
                callback.onResult(false, null);
            }
        });
    }

    // Obtém um único veículo pelo ID
    public static void getVeiculo(int veiculoId, QueryVeiculoCallback callback) {
        DatabaseManager.execute((success, connection) -> {
            if (!success) {
                callback.onResult(false, null);
                return;
            }

            String sql = "SELECT * FROM Veiculo WHERE idVeiculo = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, veiculoId);
                ResultSet rs = stmt.executeQuery();
                List<VeiculoModel> veiculos = new ArrayList<>();
                if (rs.next()) {
                    VeiculoModel veiculo = new VeiculoModel();
                    veiculo.setIdVeiculo(rs.getInt("idVeiculo"));
                    veiculo.setMatricula(rs.getString("matricula"));
                    veiculo.setAno(rs.getInt("ano"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculos.add(veiculo);
                }
                callback.onResult(true, veiculos);
            } catch (SQLException e) {
                callback.onResult(false, null);
            }
        });
    }
}

