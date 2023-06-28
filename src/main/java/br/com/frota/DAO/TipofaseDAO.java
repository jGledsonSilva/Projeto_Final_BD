package br.com.frota.DAO;

import br.com.frota.model.Tipofase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipofaseDAO extends ConexaoDB {
    private static final String INSERT_TIPOFASE_SQL = "INSERT INTO tipofase (descricao) VALUES (?);";
    private static final String SELECT_TIPOFASE_BY_ID = "SELECT id, descricao FROM tipofase WHERE id = ?";
    private static final String SELECT_ALL_TIPOFASE = "SELECT * FROM tipofase;";
    private static final String DELETE_TIPOFASE_SQL = "DELETE FROM tipofase WHERE id = ?;";
    private static final String UPDATE_TIPOFASE_SQL = "UPDATE tipofase SET descricao = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM tipofase;";

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

    public void insertTipofase(Tipofase entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_TIPOFASE_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Tipofase selectTipofase(int id) {
        Tipofase entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_TIPOFASE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                entidade = new Tipofase(id, descricao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Tipofase> selectAllTipofase() {
        List<Tipofase> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_TIPOFASE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                entidades.add(new Tipofase(id, descricao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTipofase(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_TIPOFASE_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateTipofase(Tipofase entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_TIPOFASE_SQL)) {
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
