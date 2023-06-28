package br.com.frota.DAO;

import br.com.frota.model.Poste;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class posteDAO extends ConexaoDB {
    private static final String INSERT_POSTE_SQL = "INSERT INTO poste (latitude, longitude, codigo, observacao) VALUES (?, ?, ?, ?);";
    private static final String SELECT_POSTE_BY_ID = "SELECT id, latitude, longitude, codigo, observacao FROM poste WHERE id = ?";
    private static final String SELECT_ALL_POSTE = "SELECT * FROM poste;";
    private static final String DELETE_POSTE_SQL = "DELETE FROM poste WHERE id = ?;";
    private static final String UPDATE_POSTE_SQL = "UPDATE poste SET latitude = ?, longitude = ?, codigo = ?, observacao = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM poste;";
    
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

    public void insertPoste(Poste entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_POSTE_SQL)) {
            preparedStatement.setString(1, entidade.getLatitude());
            preparedStatement.setString(2, entidade.getLongitude());
            preparedStatement.setString(3, entidade.getCodigo());
            preparedStatement.setString(4, entidade.getObservacao());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Poste selectPoste(int id) {
        Poste entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_POSTE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String latitude = rs.getString("latitude");
                String longitude = rs.getString("longitude");
                String codigo = rs.getString("codigo");
                String observacao = rs.getString("observacao");
                entidade = new Poste(id, latitude, longitude, codigo, observacao);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Poste> selectAllPoste() {
        List<Poste> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_POSTE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String latitude = rs.getString("latitude");
                String longitude = rs.getString("longitude");
                String codigo = rs.getString("codigo");
                String observacao = rs.getString("observacao");
                entidades.add(new Poste(id, latitude, longitude, codigo, observacao));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deletePoste(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_POSTE_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updatePoste(Poste entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_POSTE_SQL)) {
            preparedStatement.setString(1, entidade.getLatitude());
            preparedStatement.setString(2, entidade.getLongitude());
            preparedStatement.setString(3, entidade.getCodigo());
            preparedStatement.setString(4, entidade.getObservacao());
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
