package br.com.frota.DAO;

import br.com.frota.model.Cobranca;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CobrancaDAO extends ConexaoDB{
    private final String INSERT_Cobranca_SQL = "INSERT INTO cobranca (mes_referencia, ano_referencia, tarifa_id) VALUES (?, ?, ?)";
    private final String SELECT_Cobranca_BY_ID = "SELECT * FROM cobranca WHERE id = ?";
    private final String SELECT_ALL_Cobranca = "SELECT * FROM cobranca";
    private final String DELETE_Cobranca_SQL = "DELETE FROM cobranca WHERE id = ?";
    private final String UPDATE_Cobranca_SQL = "UPDATE cobranca SET mes_referencia = ?, ano_referencia = ?, tarifa_id = ? WHERE id = ?";
    private final String TOTAL = "SELECT count(1) FROM cobranca";

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

    public void insertCobranca(Cobranca entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_Cobranca_SQL)) {
            preparedStatement.setString(1, entidade.getMesreferencia());
            preparedStatement.setString(2, entidade.getAnoreferencia());
            preparedStatement.setInt(3, entidade.getTarifa_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Cobranca selectCobranca(int id) {
        Cobranca entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_Cobranca_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String mesreferencia = rs.getString("mes_referencia");
                String anoreferencia = rs.getString("ano_referencia");
                int tarifa_id = rs.getInt("tarifa_id");
                entidade = new Cobranca(id, mesreferencia, anoreferencia, tarifa_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Cobranca> selectAllCobranca() {
        List<Cobranca> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_Cobranca)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String mesreferencia = rs.getString("mes_referencia");
                String anoreferencia = rs.getString("ano_referencia");
                int tarifa_id = rs.getInt("tarifa_id");
                entidades.add(new Cobranca(id, mesreferencia, anoreferencia, tarifa_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteCobranca(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_Cobranca_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateCobranca(Cobranca entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_Cobranca_SQL)) {
            preparedStatement.setString(1, entidade.getMesreferencia());
            preparedStatement.setString(2, entidade.getAnoreferencia());
            preparedStatement.setInt(3, entidade.getTarifa_id());
            preparedStatement.setInt(4, entidade.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }
}
