package br.com.frota.model;

import br.com.frota.DAO.PessoaDAO;

public class Cliente extends GenericModel {
    private String numdocumento;
    private String numcliente;
    private Integer pessoa_id;
    static private PessoaDAO pessoa = new PessoaDAO();

    public Cliente(String numdocumento, String numcliente, Integer pessoa_id) {
        this.numdocumento = numdocumento;
        this.numcliente = numcliente;
        this.pessoa_id = pessoa_id;
    }

    public Cliente(Integer id, String numdocumento, String numcliente, Integer pessoa_id) {
        this.numdocumento = numdocumento;
        this.numcliente = numcliente;
        this.pessoa_id = pessoa_id;
        super.setId(id);
    }

    public String getNumdocumento() {
        return numdocumento;
    }

    public String getNumcliente() {
        return numcliente;
    }

    public Integer getPessoa_id() {
        return pessoa_id;
    }

    public void setNumdocumento(String numdocumento) {
        this.numdocumento = numdocumento;
    }

    public void setNumcliente(String numcliente) {
        this.numcliente = numcliente;
    }   

    public void setPessoa_id(Integer pessoa_id) {
        this.pessoa_id = pessoa_id;
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "id='" + this.getId() + "\'" +
                "num_documento='" + numdocumento + "\'" +
                "num_cliente='" + numcliente + "\'" +
                "pessoa_id='" + pessoa.select(pessoa_id)+ "\'" +
                '}';
    }
}
