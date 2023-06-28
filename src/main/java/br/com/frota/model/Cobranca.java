package br.com.frota.model;

import br.com.frota.DAO.TarifaDAO;

public class Cobranca extends GenericModel {
    private String mesreferencia;
    private String anoreferencia;
    private Integer tarifa_id;

    static private TarifaDAO tarifa = new TarifaDAO();

    public Cobranca(String mesreferencia, String anoreferencia, int tarifa_id) {
        this.mesreferencia = mesreferencia;
        this.anoreferencia = anoreferencia;
        this.tarifa_id = tarifa_id;
    }

    public Cobranca(Integer id, String mesreferencia, String anoreferencia, int tarifa_id) {
        this.mesreferencia = mesreferencia;
        this.anoreferencia = anoreferencia;
        this.tarifa_id = tarifa_id;
        super.setId(id);
    }

    public String getMesreferencia() {
        return mesreferencia;
    }

    public void setMesreferencia(String mesreferencia) {
        this.mesreferencia = mesreferencia;   
    }

    public String getAnoreferencia() {
        return anoreferencia;
    }

    public void setAnoreferencia(String anoreferencia) {
        this.anoreferencia = anoreferencia;   
    }

    public Integer getTarifa_id() {
        return tarifa_id;
    }

    public void setTarifa_id(Integer tarifa_id) {
        this.tarifa_id = tarifa_id;   
    }

    @Override
    public String toString() {
        return "Cobranca {" +
                "id='" + this.getId() + "\'" +
                "mes_referencia='" + mesreferencia + "\'" +
                "ano_referencia='" + anoreferencia + "\'" +
                "tarifa_id='" + tarifa.selectTarifa(tarifa_id) + "\'" +
                '}';
    }
}
