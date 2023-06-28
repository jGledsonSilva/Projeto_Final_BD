package br.com.frota.model;

import java.sql.Date;

import br.com.frota.DAO.MedidorDAO;
import br.com.frota.DAO.TimerotaDAO;

public class Medicao extends GenericModel {
    private int mes;
    private int ano;
    private Date datamedicao;
    private String comsumo;
    private Integer medidor_id;
    private Integer timerota_id;

    static private MedidorDAO medidor = new MedidorDAO();
    static private TimerotaDAO timerota = new TimerotaDAO();

    public Medicao(int mes, int ano, Date datamedicao, String comsumo) {
        this.mes = mes;
        this.ano = ano;
        this.datamedicao = datamedicao;
        this.comsumo = comsumo;
    }

    public Medicao(Integer id, int mes, int ano, Date datamedicao, String comsumo, int medidor_id, int timerota_id) {
        this.mes = mes;
        this.ano = ano;
        this.datamedicao = datamedicao;
        this.comsumo = comsumo;
        super.setId(id);
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;   
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;   
    }

    public Date getDatamedicao() {
        return datamedicao;
    }

    public void setDatamedicao(Date datamedicao) {
        this.datamedicao = datamedicao;   
    }

    public String getComsumo() {
        return comsumo;
    }

    public void setComsumo(String comsumo) {
        this.comsumo = comsumo;   
    }

    public Integer getMedidor_id() {
        return medidor_id;
    }

    public void setMedidor_id(Integer medidor_id) {
        this.medidor_id = medidor_id;   
    }

    public Integer getTimerota_id() {
        return timerota_id;
    }

    public void setTimerota_id(Integer timerota_id) {
        this.timerota_id = timerota_id;   
    }

    @Override
    public String toString() {
        return "Medicao {" +
                "id='" + this.getId() + "\'" +
                "mes='" + mes + "\'" +
                "ano='" + ano + "\'" +
                "data_medicao='" + datamedicao + "\'" +
                "comsumo='" + comsumo + "\'" +
                "medidor_id='" + medidor.selectMedidor(medidor_id) + "\'" +
                "time_rota_id='" + timerota.selectTimerota(timerota_id) + "\'" +
                '}';
    }
}
