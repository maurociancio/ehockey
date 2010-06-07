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

    public void checkCompleta(String equipo) throws ReglaNegocioException {
        CompositeReglaDeNegocioException composite = new CompositeReglaDeNegocioException();

        check(composite, dT == null, String.format("DT %s no puede estar vacío", equipo));
        check(composite, capitan == null, String.format("Capitan %s no puede estar vacío", equipo));
        check(composite, pFisico == null, String.format("Preparador Físico %s no puede estar vacío", equipo));
        check(composite, medico == null, String.format("Médico %s no puede estar vacío", equipo));
        check(composite, juezDeMesa == null, String.format("Juez de Mesa %s no puede estar vacío", equipo));
        check(composite, arbitro == null, String.format("Arbitro %s no puede estar vacío", equipo));
        check(composite, goles == null, String.format("Goles %s no puede estar vacío", equipo));
        check(composite, jugadores.size() < 8, String.format("Pocos jugadores en %s. Mínimo 8 jugadores.", equipo));
        check(composite, jugadores.size() > 18, String.format("Muchos jugadores en %s. Máximo 18 jugadores.", equipo));

        composite.throwsIfNotEmpty();
    }

    private void check(CompositeReglaDeNegocioException composite, boolean condicion, String mensaje) {
        if (condicion) {
            // TODO MEJORAR
            composite.add(new ReglaNegocioException(mensaje).setMensaje(mensaje));
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

    public void asignarJugadores(Set<Jugador> jugadores) {
        this.jugadores.addAll(jugadores);
    }
}
