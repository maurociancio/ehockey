package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ReglaNegocioException;

import ar.noxit.ehockey.exception.JugadorSinTarjetasException;

import java.util.HashMap;
import java.util.Map;

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
    private Map<Jugador, TarjetasPartido> tarjetas = new HashMap<Jugador, TarjetasPartido>();

    public boolean jugo(Jugador jugador) {
        Validate.notNull(jugador);
        return jugadores.contains(jugador);
    }

    public void checkCompleta() throws ReglaNegocioException {
        CompositeReglaDeNegocioException composite = new CompositeReglaDeNegocioException();

        check(composite, dT != null, "DT no puede ser null");
        check(composite, capitan != null, "Capitan no puede ser null");
        check(composite, pFisico != null, "Preparador Físico no puede ser null");
        check(composite, medico != null, "Médico no puede ser null");
        check(composite, juezDeMesa != null, "Juez de Mesa no puede ser null");
        check(composite, arbitro != null, "Arbitro no puede ser null");
        check(composite, goles != null, "Goles no puede ser null");
        check(composite, jugadores.size() < 8, "Pocos jugadores");
        check(composite, jugadores.size() > 18, "Muchos jugadores");

        composite.throwsIfNotEmpty();
    }

    private void check(CompositeReglaDeNegocioException composite, boolean condicion, String mensaje) {
        if (condicion) {
            // TODO MEJORAR
            composite.add(new ReglaNegocioException(mensaje));
        }
    }

    public Map<Jugador, TarjetasPartido> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(Map<Jugador, TarjetasPartido> tarjetas) {
        this.tarjetas = tarjetas;
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

    public void limpiarTarjetas() {
        tarjetas.clear();
    }

    public void crearTarjetaPartido(Jugador jugador, Integer rojas, Integer amarillas, Integer verdes) {
        this.tarjetas.put(jugador, new TarjetasPartido(rojas, amarillas, verdes));
    }

    public TarjetasPartido buscarJugador(Jugador object) throws JugadorSinTarjetasException {
        TarjetasPartido tarjetasPartido = tarjetas.get(object);
        if (tarjetasPartido == null) {
            throw new JugadorSinTarjetasException();
        }
        return tarjetasPartido;
    }
}
