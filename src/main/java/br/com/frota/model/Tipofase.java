package br.com.frota.model;

public class Tipofase extends GenericModel {
    private String descricao;

    public Tipofase(String descricao) {
        this.descricao = descricao;
    }

    public Tipofase(Integer id, String descricao) {
        this.descricao = descricao;
        super.setId(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return "Tipo_Fase {" +
                "id='" + this.getId() + "\'" +
                "descricao='" + descricao + "\'" +
                '}';
    }
}
