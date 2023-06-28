package br.com.frota.DAO;

import br.com.frota.model.Tipopessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipopessoaDAO extends ConexaoDB {
    private static final String INSERT_TIPOPESSOA_SQL = "INSERT INTO tipopessoa (descricao) VALUES (?);";
    private static final String SELECT_TIPOPESSOA_BY_ID = "SELECT id, descricao FROM tipopessoa WHERE id = ?";
    private static final String SELECT_ALL_TIPOPESSOA = "SELECT * FROM tipopessoa;";
    private static final String DELETE_TIPOPESSOA_SQL = "DELETE FROM tipopessoa WHERE id = ?;";
    private static final String UPDATE_TIPOPESSOA_SQL = "UPDATE tipopessoa SET descricao = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM tipopessoa;";

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

    public void insertTipopessoa(Tipopessoa entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_TIPOPESSOA_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Tipopessoa selectTipopessoa(int id) {
        Tipopessoa entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_TIPOPESSOA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                entidade = new Tipopessoa(id, descricao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Tipopessoa> selectAllTipopessoa() {
        List<Tipopessoa> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_TIPOPESSOA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                entidades.add(new Tipopessoa(id, descricao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTipopessoa(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_TIPOPESSOA_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateTipopessoa(Tipopessoa entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_TIPOPESSOA_SQL)) {
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
