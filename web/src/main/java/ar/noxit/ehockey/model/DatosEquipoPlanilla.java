package ar.noxit.ehockey.model;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.Validate;

public class DatosEquipoPlanilla {

    private String dT;
    private String capitan;
    private String pFisico;
    private String medico;
    private String juezDeMesa;
    private String arbitro;
    private Integer goles;
    private Set<Jugador> jugadores = new HashSet<Jugador>();
    private String goleadores;

    public boolean jugo(Jugador jugador) {
        Validate.notNull(jugador);
        return jugadores.contains(jugador);
    }

    public String getdT() {
        return dT;
    }

    public void setdT(String dT) {
        this.dT = dT;
    }

    public String getCapitan() {
        return capitan;
    }

    public void setCapitan(String capitan) {
        this.capitan = capitan;
    }

    public String getpFisico() {
        return pFisico;
    }

    public void setpFisico(String pFisico) {
        this.pFisico = pFisico;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getJuezDeMesa() {
        return juezDeMesa;
    }

    public void setJuezDeMesa(String juezDeMesa) {
        this.juezDeMesa = juezDeMesa;
    }

    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }

    public Integer getGoles() {
        return goles;
    }

    public void setGoles(Integer goles) {
        this.goles = goles;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public String getGoleadores() {
        return goleadores;
    }

    public void setGoleadores(String goleadores) {
        this.goleadores = goleadores;
    }

}
