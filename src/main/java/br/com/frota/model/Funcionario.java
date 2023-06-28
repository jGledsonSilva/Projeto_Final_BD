package br.com.frota.model;

import br.com.frota.DAO.PessoaDAO;

public class Funcionario extends GenericModel {
    private String codigofuncional;
    private Integer pessoa_id;
    static private PessoaDAO pessoa = new PessoaDAO();

    public Funcionario(String codigofuncional) {
        this.codigofuncional = codigofuncional;
    }

    public Funcionario(Integer id, String codigofuncional) {
        this.codigofuncional = codigofuncional;
        super.setId(id);
    }

    public String getCodigofuncional() {
        return codigofuncional;
    }

    public void setCodigofuncional(String codigofuncional) {
        this.codigofuncional = codigofuncional;   
    }

    @Override
    public String toString() {
        return "Funcionario {" +
                "id='" + this.getId() + "\'" +
                "codigofuncional='" + codigofuncional + "\'" +
                "pessoa_id='" + pessoa.select(pessoa_id) + "\'" +
                '}';
    }

    public int getPessoa_id() {
        return 0;
    }
}
