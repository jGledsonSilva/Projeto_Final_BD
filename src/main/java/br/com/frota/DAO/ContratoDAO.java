package br.com.frota.DAO;

import br.com.frota.model.Contrato;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO extends ConexaoDB {
    private final String INSERT_Contrato_SQL = "INSERT INTO contrato (descricao, cliente_id, rota_id, classe_id, datainicio, datacriacao) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SELECT_Contrato_BY_ID = "SELECT * FROM contrato WHERE id = ?";
    private final String SELECT_ALL_Contrato = "SELECT * FROM contrato";
    private final String DELETE_Contrato_SQL = "DELETE FROM contrato WHERE id = ?";
    private final String UPDATE_Contrato_SQL = "UPDATE contrato SET descricao = ?, cliente_id = ?, rota_id = ?, classe_id = ?, datainicio = ?, datacriacao = ? WHERE id = ?";
    private final String TOTAL = "SELECT count(1) FROM contrato";

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

    public void insertContrato(Contrato entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_Contrato_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.setInt(2, entidade.getCliente_id());
            preparedStatement.setInt(3, entidade.getClasse_id());
            preparedStatement.setInt(4, entidade.getClasse_id());
            preparedStatement.setDate(5, entidade.getDatainicio());
            preparedStatement.setDate(6, entidade.getDatacriacao());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Contrato selectContrato(int id) {
        Contrato entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_Contrato_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                int cliente_id = rs.getInt("cliente_id");
                int rota_id = rs.getInt("rota_id");
                int classe_id = rs.getInt("classe_id");
                java.sql.Date datainicio = rs.getDate("datainicio");
                java.sql.Date datacriacao = rs.getDate("datacriacao");
                entidade = new Contrato(id, descricao, cliente_id, rota_id, classe_id, datainicio, datacriacao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }
    
    public List<Contrato> selectAllContrato() {
        List<Contrato> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_Contrato)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                int cliente_id = rs.getInt("cliente_id");
                int rota_id = rs.getInt("rota_id");
                int classe_id = rs.getInt("classe_id");
                java.sql.Date datainicio = rs.getDate("datainicio");
                java.sql.Date datacriacao = rs.getDate("datacriacao");
                entidades.add(new Contrato(id, descricao, cliente_id, rota_id, classe_id, datainicio, datacriacao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteContrato(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_Contrato_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateContrato(Contrato entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_Contrato_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.setInt(2, entidade.getCliente_id());
            preparedStatement.setInt(3, entidade.getClasse_id());
            preparedStatement.setInt(4, entidade.getClasse_id());
            preparedStatement.setDate(5, entidade.getDatainicio());
            preparedStatement.setDate(6, entidade.getDatacriacao());
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
