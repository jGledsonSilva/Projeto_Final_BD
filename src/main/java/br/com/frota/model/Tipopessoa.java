package br.com.frota.model;

public class Tipopessoa extends GenericModel {
    private String descricao;

    public Tipopessoa(String descricao) {
        this.descricao = descricao;
    }

    public Tipopessoa(Integer id, String descricao) {
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
        return "Tipo_Pessoa {" +
                "id='" + this.getId() + "\'" +
                "descricao='" + descricao + "\'" +
                '}';
    }
}
