package br.com.frota.DAO;

import br.com.frota.model.Classe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClasseDAO extends ConexaoDB {
    private final String INSERT_Classe_SQL = "INSERT INTO classe (descricao, tipofase_id) VALUES (?, ?)";
    private final String SELECT_Classe_BY_ID = "SELECT * FROM classe WHERE id = ?";
    private final String SELECT_ALL_Classe = "SELECT * FROM classe";
    private final String DELETE_Classe_SQL = "DELETE FROM classe WHERE id = ?";
    private final String UPDATE_Classe_SQL = "UPDATE classe SET descricao = ?, tipofase_id = ? WHERE id = ?";
    private final String TOTAL = "SELECT count(1) FROM classe";

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

    public void insertClasse(Classe entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_Classe_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.setInt(2, entidade.getTipofase_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Classe selectClasse(int id) {
        Classe entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_Classe_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                int tipofase_id = rs.getInt("tipofase_id");
                entidade = new Classe(id, descricao, tipofase_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Classe> selectAllClasse() {
        List<Classe> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_Classe)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                int tipofase_id = rs.getInt("tipofase_id");
                entidades.add(new Classe(id, descricao, tipofase_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteClasse(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_Classe_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateClasse(Classe entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_Classe_SQL)) {
            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.setInt(2, entidade.getTipofase_id());
            preparedStatement.setInt(3, entidade.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }
}
