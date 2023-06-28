package br.com.frota.DAO;

import br.com.frota.model.Tarifa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarifaDAO extends ConexaoDB {
    private final String INSERT_Tarifa_SQL = "INSERT INTO tarifa (taxa, classe_id, lei, data_inicio, data_fim, aliquota_ICMS) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SELECT_Tarifa_BY_ID = "SELECT * FROM tarifa WHERE id = ?";
    private final String SELECT_ALL_Tarifa = "SELECT * FROM tarifa";
    private final String DELETE_Tarifa_SQL = "DELETE FROM tarifa WHERE id = ?";
    private final String UPDATE_Tarifa_SQL = "UPDATE tarifa SET taxa = ?, classe_id = ?, lei = ?, data_inicio = ?, data_fim = ?, aliquota_ICMS = ? WHERE id = ?";
    private final String TOTAL = "SELECT count(1) FROM tarifa";

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

    public void insertTarifa(Tarifa entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_Tarifa_SQL)) {
            preparedStatement.setString(1, entidade.getTaxa());
            preparedStatement.setInt(2, entidade.getClasse_id());
            preparedStatement.setString(3, entidade.getLei());
            preparedStatement.setDate(4, entidade.getDatainicio());
            preparedStatement.setDate(5, entidade.getDatafim());
            preparedStatement.setString(6, entidade.getAliquotaicms());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Tarifa selectTarifa(int id) {
        Tarifa entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_Tarifa_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String taxa = rs.getString("taxa");
                int classe_id = rs.getInt("classe_id");
                String lei = rs.getString("lei");
                java.sql.Date data_inicio = rs.getDate("data_inicio");
                java.sql.Date data_fim = rs.getDate("data_fim");
                String aliquota_ICMS = rs.getString("aliquota_ICMS");
                entidade = new Tarifa(id, taxa, classe_id, lei, data_inicio, data_fim, aliquota_ICMS);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Tarifa> selectAllTarifa() {
        List<Tarifa> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_Tarifa)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String taxa = rs.getString("taxa");
                int classe_id = rs.getInt("classe_id");
                String lei = rs.getString("lei");
                java.sql.Date data_inicio = rs.getDate("data_inicio");
                java.sql.Date data_fim = rs.getDate("data_fim");
                String aliquota_ICMS = rs.getString("aliquota_ICMS");
                entidades.add(new Tarifa(id, taxa, classe_id, lei, data_inicio, data_fim, aliquota_ICMS));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteTarifa(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_Tarifa_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateTarifa(Tarifa entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_Tarifa_SQL)) {
            preparedStatement.setString(1, entidade.getTaxa());
            preparedStatement.setInt(2, entidade.getClasse_id());
            preparedStatement.setString(3, entidade.getLei());
            preparedStatement.setDate(4, entidade.getDatainicio());
            preparedStatement.setDate(5, entidade.getDatafim());
            preparedStatement.setString(6, entidade.getAliquotaicms());
            preparedStatement.setInt(7, entidade.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }
}
