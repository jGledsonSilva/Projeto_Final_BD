package br.com.frota.DAO;

import br.com.frota.model.Funcionario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO extends ConexaoDB {
    private final String INSERT_FUNCIONARIO_SQL = "INSERT INTO funcionario (codigofuncional, pessoa_id) VALUES (?, ?)";
    private final String SELECT_FUNCIONARIO_BY_ID = "SELECT * FROM funcionario WHERE id = ?";
    private final String SELECT_ALL_FUNCIONARIO = "SELECT * FROM funcionario";
    private final String DELETE_FUNCIONARIO_SQL = "DELETE FROM funcionario WHERE id = ?";
    private final String UPDATE_FUNCIONARIO_SQL = "UPDATE funcionario SET codigofuncional = ?, pessoa_id = ? WHERE id = ?";
    

    public void insertFuncionario(Funcionario entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_FUNCIONARIO_SQL)) {
            preparedStatement.setString(1, entidade.getCodigofuncional());
            preparedStatement.setInt(2, entidade.getPessoa_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Funcionario selectFuncionario(int fucionario_id) {
        Funcionario entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_FUNCIONARIO_BY_ID)) {
            preparedStatement.setInt(1, fucionario_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String codigoFuncional = rs.getString("codigo_funcional");
                //Integer pessoaId = rs.getInt("pessoa_id");
                entidade = new Funcionario(fucionario_id, codigoFuncional);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Funcionario> selectAllFuncionario() {
        List<Funcionario> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_FUNCIONARIO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String codigoFuncional = rs.getString("codigo_funcional");
                //Integer pessoaId = rs.getInt("pessoa_id");
                entidades.add(new Funcionario(id, codigoFuncional));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteFuncionario(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_FUNCIONARIO_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateFuncionario(Funcionario entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_FUNCIONARIO_SQL)) {
            preparedStatement.setString(1, entidade.getCodigofuncional());
            preparedStatement.setInt(2, entidade.getPessoa_id());
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
