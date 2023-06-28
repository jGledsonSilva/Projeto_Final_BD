package br.com.frota.DAO;

import br.com.frota.model.Medicao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicaoDAO extends ConexaoDB {
    private final String INSERT_Medicao_SQL = "INSERT INTO medicao (mes, ano, datame_dicao, comsumo, medidor_id, time_rota_id) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SELECT_Medicao_BY_ID = "SELECT * FROM medicao WHERE id = ?";
    private final String SELECT_ALL_Medicao = "SELECT * FROM medicao";
    private final String DELETE_Medicao_SQL = "DELETE FROM medicao WHERE id = ?";
    private final String UPDATE_Medicao_SQL = "UPDATE medicao SET mes = ?, ano = ?, datame_dicao = ?, comsumo = ?, medidor_id = ?, time_rota_id = ? WHERE id = ?";
    private final String TOTAL = "SELECT count(1) FROM medicao";

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

    public void insertMedicao(Medicao entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_Medicao_SQL)) {
            preparedStatement.setInt(1, entidade.getMes());
            preparedStatement.setInt(2, entidade.getAno());
            preparedStatement.setDate(3, entidade.getDatamedicao());
            preparedStatement.setString(4, entidade.getComsumo());
            preparedStatement.setInt(5, entidade.getMedidor_id());
            preparedStatement.setInt(6, entidade.getTimerota_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Medicao selectMedicao(int id) {
        Medicao entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_Medicao_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int mes = rs.getInt("mes");
                int ano = rs.getInt("ano");
                java.sql.Date datamedicao = rs.getDate("datamedicao");
                String comsumo = rs.getString("comsumo");
                int medidor_id = rs.getInt("medidor_id");
                int timerota_id = rs.getInt("timerota_id");
                entidade = new Medicao(id, mes, ano, datamedicao, comsumo, medidor_id, timerota_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Medicao> selectAllMedicao() {
        List<Medicao> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_Medicao)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int mes = rs.getInt("mes");
                int ano = rs.getInt("ano");
                java.sql.Date datamedicao = rs.getDate("datamedicao");
                String comsumo = rs.getString("comsumo");
                int medidor_id = rs.getInt("medidor_id");
                int timerota_id = rs.getInt("timerota_id");
                entidades.add(new Medicao(id, mes, ano, datamedicao, comsumo, medidor_id, timerota_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteMedicao(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_Medicao_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateMedicao(Medicao entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_Medicao_SQL)) {
            preparedStatement.setInt(1, entidade.getMes());
            preparedStatement.setInt(2, entidade.getAno());
            preparedStatement.setDate(3, entidade.getDatamedicao());
            preparedStatement.setString(4, entidade.getComsumo());
            preparedStatement.setInt(5, entidade.getMedidor_id());
            preparedStatement.setInt(6, entidade.getTimerota_id());
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
