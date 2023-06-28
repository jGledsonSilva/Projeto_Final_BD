package br.com.frota.DAO;

import br.com.frota.model.Tarefarota;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefarotaDAO extends ConexaoDB {
    private final String INSERT_Tarefarota_SQL = "INSERT INTO tarefarota (observacao, datainicio, datafim, rota_id) VALUES (?, ?, ?, ?)";
    private final String SELECT_Tarefarota_BY_ID = "SELECT * FROM tarefarota WHERE id = ?";
    private final String SELECT_ALL_Tarefarota = "SELECT * FROM tarefarota";
    private final String DELETE_Tarefarota_SQL = "DELETE FROM tarefarota WHERE id = ?";
    private final String UPDATE_Tarefarota_SQL = "UPDATE tarefarota SET observacao = ?, datainicio = ?, datafim = ?, rota_id = ? WHERE id = ?";
    private final String TOTAL = "SELECT count(1) FROM tarefarota";

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

    public void insertTarefarota(Tarefarota entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_Tarefarota_SQL)) {
            preparedStatement.setString(1, entidade.getObservacao());
            preparedStatement.setDate(2, entidade.getDatainicio());
            preparedStatement.setDate(3, entidade.getDatafim());
            preparedStatement.setInt(4, entidade.getRota_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Tarefarota selectTarefarota(int id) {
        Tarefarota entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_Tarefarota_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String observacao = rs.getString("observacao");
                java.sql.Date datainicio = rs.getDate("datainicio");
                java.sql.Date datafim = rs.getDate("datafim");
                int rota_id = rs.getInt("rota_id");
                entidade = new Tarefarota(id, observacao, datainicio, datafim, rota_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Tarefarota> selectAllTarefarota() {
        List<Tarefarota> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_Tarefarota)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String observacao = rs.getString("observacao");
                java.sql.Date datainicio = rs.getDate("datainicio");
                java.sql.Date datafim = rs.getDate("datafim");
                int rota_id = rs.getInt("rota_id");
                entidades.add(new Tarefarota(id, observacao, datainicio, datafim, rota_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTarefarota(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_Tarefarota_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateTarefarota(Tarefarota entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_Tarefarota_SQL)) {
            preparedStatement.setString(1, entidade.getObservacao());
            preparedStatement.setDate(2, entidade.getDatainicio());
            preparedStatement.setDate(3, entidade.getDatafim());
            preparedStatement.setInt(4, entidade.getRota_id());
            preparedStatement.setInt(5, entidade.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }
}
