package br.com.frota.model;

import java.sql.Date;

import br.com.frota.DAO.ClasseDAO;

public class Tarifa extends GenericModel {
    private String taxa;
    private Integer classe_id;
    static private ClasseDAO classe = new ClasseDAO();
    private String lei;
    private Date datainicio;
    private Date datafim;
    private String aliquotaicms;

    public Tarifa(String taxa) {
        this.taxa = taxa;
    }

    public Tarifa(Integer id, String taxa, int classe_id, String lei, Date datainicio, Date datafim, String aliquotaicms) {
        this.taxa = taxa;
        this.lei = lei;
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.aliquotaicms = aliquotaicms;
        super.setId(id);
    }

    public String getTaxa() {
        return taxa;
    }

    public void setTaxa(String taxa) {
        this.taxa = taxa;   
    }

    public Integer getClasse_id() {
        return classe_id;
    }

    public void setClasse_id(Integer classe_id) {
        this.classe_id = classe_id;   
    }

    public String getLei() {
        return lei;
    }

    public void setLei(String lei) {
        this.lei = lei;   
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

    public String getAliquotaicms() {
        return aliquotaicms;
    }

    public void setAliquotaicms(String aliquotaicms) {
        this.aliquotaicms = aliquotaicms;   
    }

    @Override
    public String toString() {
        return "Tarifa {" +
                "id='" + this.getId() + "\'" +
                "taxa='" + taxa + "\'" +
                "classe_id='" + classe.selectClasse(classe_id) + "\'" +
                "lei='" + lei + "\'" +
                "datainicio='" + datainicio + "\'" +
                "datafim='" + datafim + "\'" +
                "aliquotaicms='" + aliquotaicms + "\'" +
                '}';
    }
}
