package br.com.frota.DAO;

import br.com.frota.model.Timerote;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimerotaDAO extends ConexaoDB {
    private final String INSERT_Timerota_SQL = "INSERT INTO timerota (Funcionario_id, tarefa_rota_id) VALUES (?, ?)";
    private final String SELECT_Timerota_BY_ID = "SELECT * FROM timerota WHERE id = ?";
    private final String SELECT_ALL_Timerota = "SELECT * FROM timerota";
    private final String DELETE_Timerota_SQL = "DELETE FROM timerota WHERE id = ?";
    private final String UPDATE_Timerota_SQL = "UPDATE timerota SET Funcionario_id = ?, tarefa_rota_id = ? WHERE id = ?";
    private final String TOTAL = "SELECT count(1) FROM timerota";

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

    public void insertTimerota(Timerote entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_Timerota_SQL)) {
            preparedStatement.setInt(1, entidade.getFucionario_id());
            preparedStatement.setInt(2, entidade.getTarefarota_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Timerote selectTimerota(int id) {
        Timerote entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_Timerota_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int Funcionario_id = rs.getInt("Funcionario_id");
                int tarefa_rota_id = rs.getInt("tarefa_rota_id");
                entidade = new Timerote(id, Funcionario_id, tarefa_rota_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Timerote> selectAllTimerota() {
        List<Timerote> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_Timerota)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int Funcionario_id = rs.getInt("Funcionario_id");
                int tarefa_rota_id = rs.getInt("tarefa_rota_id");
                entidades.add(new Timerote(id, Funcionario_id, tarefa_rota_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTimerota(int id) throws SQLException, ClassNotFoundException {
        boolean rowDeleted;
        try (PreparedStatement statement = prepararSQL(DELETE_Timerota_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateTimerota(Timerote entidade) throws SQLException, ClassNotFoundException {
        boolean rowUpdated;
        try (PreparedStatement statement = prepararSQL(UPDATE_Timerota_SQL)) {
            statement.setInt(1, entidade.getFucionario_id());
            statement.setInt(2, entidade.getTarefarota_id());
            statement.setInt(3, entidade.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
