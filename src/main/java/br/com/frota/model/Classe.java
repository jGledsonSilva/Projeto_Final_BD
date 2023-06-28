package br.com.frota.model;

import br.com.frota.DAO.TipofaseDAO;

public class Classe extends GenericModel {
    private String descricao;
    private Integer tipofase_id;
    static private TipofaseDAO tipofase = new TipofaseDAO();

    public Classe(String descricao) {
        this.descricao = descricao;
    }

    public Classe(Integer id, String descricao, int tipofase_id2) {
        this.descricao = descricao;
        super.setId(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;   
    }

    public Integer getTipofase_id() {
        return tipofase_id;
    }

    public void setTipofase_id(Integer tipofase_id) {
        this.tipofase_id = tipofase_id;   
    }

    @Override
    public String toString() {
        return "Classe {" +
                "id='" + this.getId() + "\'" +
                "descricao='" + descricao + "\'" +
                "tipofase_id='" + tipofase.selectTipofase(tipofase_id) + "\'" +
                '}';
    }
}
