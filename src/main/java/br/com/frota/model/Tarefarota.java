package br.com.frota.model;

import java.sql.Date;

import br.com.frota.DAO.RotaDAO;

public class Tarefarota extends GenericModel {
    private String observacao;
    private Date datainicio;
    private Date datafim;
    private Integer rota_id;

    static private RotaDAO rota = new RotaDAO();

    public Tarefarota(String observacao) {
        this.observacao = observacao;
    }

    public Tarefarota(Integer id, String observacao, Date datainicio, Date datafim, int rota_id) {
        this.observacao = observacao;
        this.datainicio = datainicio;
        this.datafim = datafim;
        super.setId(id);
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;   
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;   
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;   
    }

    public Integer getRota_id() {
        return rota_id;
    }

    public void setRota_id(Integer rota_id) {
        this.rota_id = rota_id;   
    }

    @Override
    public String toString() {
        return "Tarefarota {" +
                "id='" + this.getId() + "\'" +
                "observacao='" + observacao + "\'" +
                "datainicio='" + datainicio + "\'" +
                "datafim='" + datafim + "\'" +
                "rota_id='" + rota.selectRota(rota_id) + "\'" +
                '}';
    }
}
