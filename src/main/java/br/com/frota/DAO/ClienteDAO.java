package br.com.frota.DAO;

import br.com.frota.model.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends ConexaoDB {
    private final String INSERT_Cliente_SQL = "INSERT INTO cliente (num_documento, num_cliente, pessoa_id) VALUES (?, ?, ?)";
    private final String SELECT_Cliente_BY_ID = "SELECT * FROM cliente WHERE id = ?";
    private final String SELECT_ALL_Cliente = "SELECT * FROM cliente";
    private final String DELETE_Cliente_SQL = "DELETE FROM cliente WHERE id = ?";
    private final String UPDATE_Cliente_SQL = "UPDATE cliente SET num_documento = ?, num_cliente = ?, pessoa_id = ? WHERE id = ?";
    private final String TOTAL = "SELECT count(1) FROM cliente";

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

    public void insertCliente(Cliente entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_Cliente_SQL)) {
            preparedStatement.setString(1, entidade.getNumdocumento());
            preparedStatement.setString(2, entidade.getNumcliente());
            preparedStatement.setInt(3, entidade.getPessoa_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente selectCliente(int id) {
        Cliente entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_Cliente_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String numdocumento = rs.getString("num_documento");
                String numcliente = rs.getString("num_cliente");
                int pessoa_id = rs.getInt("pessoa_id");
                entidade = new Cliente(id, numdocumento, numcliente, pessoa_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Cliente> selectAllCliente() {
        List<Cliente> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_Cliente)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String numdocumento = rs.getString("num_documento");
                String numcliente = rs.getString("num_cliente");
                int pessoa_id = rs.getInt("pessoa_id");
                entidades.add(new Cliente(id, numdocumento, numcliente, pessoa_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteCliente(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE_Cliente_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateCliente(Cliente entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_Cliente_SQL)) {
            preparedStatement.setString(1, entidade.getNumdocumento());
            preparedStatement.setString(2, entidade.getNumcliente());
            preparedStatement.setInt(3, entidade.getPessoa_id());
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
