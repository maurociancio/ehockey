package ar.noxit.ehockey.web.pages.planilla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EquipoInfo implements Serializable {

    private String goleadores;
    private String dt;
    private String pf;
    private String medico;
    private String juezMesa;
    private String arbitro;
    private List<Integer> seleccionados = new ArrayList<Integer>();

    public String getGoleadores() {
        return goleadores;
    }

    public void setGoleadores(String goleadores) {
        this.goleadores = goleadores;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getJuezMesa() {
        return juezMesa;
    }

    public void setJuezMesa(String juezMesa) {
        this.juezMesa = juezMesa;
    }

    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }

    public List<Integer> getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(List<Integer> seleccionados) {
        this.seleccionados = seleccionados;
    }
}
