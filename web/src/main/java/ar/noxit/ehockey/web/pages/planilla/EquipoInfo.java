package ar.noxit.ehockey.web.pages.planilla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.noxit.ehockey.model.Jugador;

public class EquipoInfo implements Serializable {

    private List<Jugador> jugadores = new ArrayList<Jugador>();
    private String goleadores;
    private String dt;
    private String pf;
    private String medico;
    private String juezMesa;
    private String arbitro;

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

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
}
