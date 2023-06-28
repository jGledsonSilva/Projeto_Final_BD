package br.com.frota.model;

public class Rota extends GenericModel {
    private String descricao;

    public Rota(String descricao) {
        this.descricao = descricao;
    }

    public Rota(Integer id, String descricao) {
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
    public String toString() {
        return "Rota {" +
                "id='" + this.getId() + "\'" +
                "descricao='" + descricao + "\'" +
                '}';
    }
}
