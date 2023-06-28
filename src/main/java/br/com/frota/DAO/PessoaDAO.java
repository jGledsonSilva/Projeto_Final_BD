package br.com.frota.DAO;

import br.com.frota.model.Pessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO extends ConexaoDB {
    private static final String INSERT = "INSERT INTO pessoa (nome, cpf, cnpj, tipopessoa_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE pessoa SET nome = ?, cpf = ?, cnpj = ?, tipopessoa_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM pessoa WHERE id = ?";
    private static final String SELECT = "SELECT * FROM pessoa WHERE id = ?";
    private static final String Total = "SELECT count(1) FROM pessoa";
    private static final String SELECT_ALL = "SELECT * FROM pessoa";

    public Integer count() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = prepararSQL(Total)) {
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

    public void insert(Pessoa entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT)) {
            preparedStatement.setString(1, entidade.getNome());
            preparedStatement.setString(2, entidade.getCpf());
            preparedStatement.setString(3, entidade.getCnpj());
            preparedStatement.setInt(4, entidade.getTipopessoa_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Pessoa select(int id) {
        Pessoa entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String cnpj = rs.getString("cnpj");
                int tipopessoa_id = rs.getInt("tipopessoa_id");
                entidade = new Pessoa(id, nome, cpf, cnpj, tipopessoa_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public static List<Pessoa> selectAll() {
        List<Pessoa> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String cnpj = rs.getString("cnpj");
                int tipopessoa_id = rs.getInt("tipopessoa_id");
                entidades.add(new Pessoa(id, nome, cpf, cnpj, tipopessoa_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean delete(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepararSQL(DELETE)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean update(Pessoa entidade) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE)) {
            preparedStatement.setString(1, entidade.getNome());
            preparedStatement.setString(2, entidade.getCpf());
            preparedStatement.setString(3, entidade.getCnpj());
            preparedStatement.setInt(4, entidade.getTipopessoa_id());
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
