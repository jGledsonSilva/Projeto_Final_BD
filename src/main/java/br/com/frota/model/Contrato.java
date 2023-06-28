package br.com.frota.model;

import br.com.frota.DAO.ClienteDAO;

import java.sql.Date;

import br.com.frota.DAO.ClasseDAO;
import br.com.frota.DAO.MedidorDAO;

public class Contrato extends GenericModel{
    private String descricao;
    private Integer cliente_id;
    private Integer classe_id;
    private Integer medidor_id;
    private Date datainicio;
    private Date datacriacao;

    static private ClienteDAO cliente = new ClienteDAO();
    static private ClasseDAO classe = new ClasseDAO();
    static private MedidorDAO medidor = new MedidorDAO();

    public Contrato(String descricao) {
        this.descricao = descricao;
    }
    
    public Contrato(Integer id, String descricao, int cliente_id, int classe_id, int medidor_id, Date datainicio, Date datacriacao) {
        this.descricao = descricao;
        this.datainicio = datainicio;
        this.datacriacao = datacriacao;
        super.setId(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;   
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;   
    }

    public Integer getClasse_id() {
        return classe_id;
    }

    public void setClasse_id(Integer classe_id) {
        this.classe_id = classe_id;   
    }

    public Integer getMedidor_id() {
        return medidor_id;
    }

    public void setMedidor_id(Integer medidor_id) {
        this.medidor_id = medidor_id;   
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;   
    }

    public Date getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;   
    }

    @Override
    public String toString() {
        return "Contrato {" +
                "id='" + this.getId() + "\'" +
                "descricao='" + descricao + "\'" +
                "cliente_id='" + cliente.selectCliente(cliente_id) + "\'" +
                "classe_id='" + classe.selectClasse(classe_id) + "\'" +
                "medidor_id='" + medidor.selectMedidor(medidor_id) + "\'" +
                "datainicio='" + datainicio + "\'" +
                "datacriacao='" + datacriacao + "\'" +
                '}';
    }
}
