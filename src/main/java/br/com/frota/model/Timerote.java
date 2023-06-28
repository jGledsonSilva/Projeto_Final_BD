package br.com.frota.model;

import br.com.frota.DAO.FuncionarioDAO;
import br.com.frota.DAO.TarefarotaDAO;

public class Timerote extends GenericModel {
    private int fucionario_id;
    private int tarefarota_id;

    static private FuncionarioDAO funcionario = new FuncionarioDAO();
    static private TarefarotaDAO tarefarota = new TarefarotaDAO();

    public Timerote(int fucionario_id, int tarefarota_id) {
        this.fucionario_id = fucionario_id;
        this.tarefarota_id = tarefarota_id;
    }

    public Timerote(Integer id, int fucionario_id, int tarefarota_id) {
        this.fucionario_id = fucionario_id;
        this.tarefarota_id = tarefarota_id;
        super.setId(id);
    }

    public int getFucionario_id() {
        return fucionario_id;
    }

    public void setFucionario_id(int fucionario_id) {
        this.fucionario_id = fucionario_id;   
    }

    public int getTarefarota_id() {
        return tarefarota_id;
    }

    public void setTarefarota_id(int tarefarota_id) {
        this.tarefarota_id = tarefarota_id;   
    }

    @Override
    public String toString() {
        return "Time_rote {" +
                "id='" + this.getId() + "\'" +
                "fucionario_id='" + funcionario.selectFuncionario(fucionario_id) + "\'" +
                "tarefarota_id='" + tarefarota.selectTarefarota(tarefarota_id) + "\'" +
                '}';
    }
}
