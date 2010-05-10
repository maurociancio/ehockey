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
    private String capitan;
    private List<Integer> seleccionados = new ArrayList<Integer>();
    private List<AmonestacionInfo> amonestaciones = new ArrayList<AmonestacionInfo>();

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

    public List<AmonestacionInfo> getAmonestaciones() {
        return amonestaciones;
    }

    public void setAmonestaciones(List<AmonestacionInfo> amonestaciones) {
        this.amonestaciones = amonestaciones;
    }

    public String getCapitan() {
        return capitan;
    }

    public void setCapitan(String capitan) {
        this.capitan = capitan;
    }
}
