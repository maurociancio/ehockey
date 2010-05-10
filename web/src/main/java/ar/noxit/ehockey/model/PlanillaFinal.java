package ar.noxit.ehockey.model;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang.Validate;

import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
import ar.noxit.ehockey.exception.PlanillaNoModificableException;

public class PlanillaFinal extends PlanillaBase {

    public PlanillaFinal(Partido partido) {
        super(partido);
    }

    private void agregarJugador(Jugador jugador, Set<Jugador> jugadores) throws JugadorYaPerteneceAListaException {
        Validate.notNull(jugador, "jugador no puede ser null");

        if (jugadores.contains(jugador)) {
            throw new JugadorYaPerteneceAListaException("el jugador ya está en la lista de buena fe");
        }
        jugadores.add(jugador);
    }

    private void agregarJugadores(Collection<Jugador> jugadoresNuevos, Set<Jugador> jugadores) {
        jugadores.clear();
        for (Jugador j : jugadoresNuevos) {
            jugadores.add(j);
        }
    }

    public void agregarJugadorLocal(Jugador jugador) throws PlanillaNoModificableException,
            JugadorYaPerteneceAListaException {
        validatePlanillaCerrada();
        agregarJugador(jugador, this.datosLocal.getJugadores());
    }

    public void agregarJugadorVisitante(Jugador jugador) throws PlanillaNoModificableException,
            JugadorYaPerteneceAListaException {
        validatePlanillaCerrada();
        agregarJugador(jugador, this.datosVisitante.getJugadores());
    }

    public void setJugadoresLocal(Collection<Jugador> jugadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        agregarJugadores(jugadores, this.datosLocal.getJugadores());
    }

    public void setJugadoresVisitante(Collection<Jugador> jugadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        agregarJugadores(jugadores, this.datosVisitante.getJugadores());
    }

    public void setGolesLocal(Integer goles) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setGoles(goles);
    }

    public void setGolesVisitante(Integer goles) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setGoles(goles);
    }

    public void setArbitroL(String arbitro) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setArbitro(arbitro);
    }

    public void setDtL(String dt) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setdT(dt);
    }

    public void setGoleadoresL(String goleadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setGoleadores(goleadores);
    }

    public void setJuezMesaL(String juezMesa) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setJuezDeMesa(juezMesa);
    }

    public void setMedicoL(String medico) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setMedico(medico);
    }

    public void setPfL(String pf) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosLocal.setpFisico(pf);
    };

    public void setArbitroV(String arbitro) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setArbitro(arbitro);
    }

    public void setDtV(String dt) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setdT(dt);
    }

    public void setGoleadoresV(String goleadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setGoleadores(goleadores);
    }

    public void setJuezMesaV(String juezMesa) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setJuezDeMesa(juezMesa);
    }

    public void setMedicoV(String medico) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setMedico(medico);
    }

    public void setPfV(String pf) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.datosVisitante.setpFisico(pf);
    }

    public void setObservaciones(String observaciones) throws PlanillaNoModificableException {
        validatePlanillaCerrada();
        this.observaciones = observaciones;
    }

}
