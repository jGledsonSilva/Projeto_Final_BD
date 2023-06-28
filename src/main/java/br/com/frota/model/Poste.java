package br.com.frota.model;

public class Poste extends GenericModel {
    private String latitude;
    private String longitude;
    private String codigo;
    private String observacao;

    public Poste(String latitude, String longitude, String codigo, String observacao) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.codigo = codigo;
        this.observacao = observacao;
    }

    public Poste(Integer id, String latitude, String longitude, String codigo, String observacao) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.codigo = codigo;
        this.observacao = observacao;
        super.setId(id);
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Poste {" +
                "id='" + this.getId() + "\'" +
                "latitude='" + latitude + "\'" +
                "longitude='" + longitude + "\'" +
                "codigo='" + codigo + "\'" +
                "observacao='" + observacao + "\'" +
                '}';
    }
}
