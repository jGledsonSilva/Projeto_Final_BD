package br.com.frota.model;

import br.com.frota.DAO.RotaDAO;
import br.com.frota.DAO.posteDAO;

public class Medidor extends GenericModel {
    private String descricao;
    private Integer rota_id;
    private Integer poste_id;

    static private RotaDAO rota = new RotaDAO();
    static private posteDAO poste = new posteDAO();

    public Medidor(String descricao, Integer rota_id, Integer poste_id) {
        this.descricao = descricao;
        this.rota_id = rota_id;
        this.poste_id = poste_id;
    }

    public Medidor(Integer id, String descricao, int rota_id, int poste_id) {
        this.descricao = descricao;
        this.rota_id = rota_id;
        this.poste_id = poste_id;
        super.setId(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;   
    }

    public Integer getRota_id() {
        return rota_id;
    }

    public void setRota_id(Integer rota_id) {
        this.rota_id = rota_id;   
    }

    public Integer getPoste_id() {
        return poste_id;
    }

    public void setPoste_id(Integer poste_id) {
        this.poste_id = poste_id;   
    }

    @Override
    public String toString() {
        return "Medidor {" +
                "id='" + this.getId() + "\'" +
                "descricao='" + descricao + "\'" +
                "rota_id='" + rota.selectRota(rota_id) + "\'" +
                "poste_id='" + poste.selectPoste(poste_id) + "\'" +
                '}';
    }
}
