package br.com.frota.model;

import br.com.frota.DAO.TipopessoaDAO;

public class Pessoa extends GenericModel {
    private String nome;
    private String cpf;
    private String cnpj;
    private Integer tipopessoa_id;
    static private TipopessoaDAO Tipopessoa = new TipopessoaDAO();

    public Pessoa(String nome, String cpf, String cnpj, Integer tipopessoa_id) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.tipopessoa_id = tipopessoa_id;
    }

    public Pessoa(String nome, String cpf, Integer tipopessoa_id) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipopessoa_id = tipopessoa_id;
    }

    public Pessoa(Integer id, String nome, String cpf, String cnpj, Integer tipopessoa_id) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.tipopessoa_id = tipopessoa_id;
        super.setId(id);
    }

    public Pessoa(Integer id, String nome, String cpf, Integer tipopessoa_id) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipopessoa_id = tipopessoa_id;
        super.setId(id);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getTipopessoa_id() {
        return tipopessoa_id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setcpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTipopessoa_id(Integer tipopessoa_id) {
        this.tipopessoa_id = tipopessoa_id;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString(){
        return "Pessoa {" +
                "id='" + this.getId() + "\'" +
                "nome='" + nome + "\'" +
                "cpf='" + cpf + "\'" +
                "cnpj='" + cnpj + "\'" +
                "tipopessoa_id='" + Tipopessoa.selectTipopessoa(tipopessoa_id)+ "\'" +
                '}';
    }
}
