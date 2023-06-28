package br.com.frota.DAO;

import br.com.frota.model.Rota;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RotaDAO extends ConexaoDB {
    private final String INSERT_Rota_SQL = "INSERT INTO rota (descricao) VALUES (?)";
    private final String SELECT_Rota_BY_ID = "SELECT * FROM rota WHERE id = ?";
    private final String SELECT_ALL_Rota = "SELECT * FROM rota";
    private final String DELETE_Rota_SQL = "DELETE FROM rota WHERE id = ?";
    private final String UPDATE_Rota_SQL = "UPDATE rota SET descricao = ? WHERE id = ?";
    private final String TOTAL = "SELECT count(1) FROM rota";

    public Integer count() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = prepararSQL(TOTAL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public void insertRota(Rota entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_Rota_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Rota selectRota(int id) {
        Rota entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_Rota_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                entidade = new Rota(id, descricao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Rota> selectAllRota() {
        List<Rota> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_Rota)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                entidades.add(new Rota(id, descricao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteRota(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_Rota_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateRota(Rota entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_Rota_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.setInt(2, entidade.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }
}
