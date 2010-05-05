package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;

import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
import ar.noxit.ehockey.exception.PlanillaNoModificableException;

public class PlanillaFinal extends Planilla {

    public PlanillaFinal(Planilla original) {
        super();

        Validate.notNull(original, "La planilla no puede ser null");

        id = original.id;

        partido = original.partido;
        datosLocal = original.datosLocal;

        datosVisitante = original.datosVisitante;

        observaciones = original.observaciones;

        datosLocal.setJugadores(original.datosLocal.getJugadores());
        datosVisitante.setJugadores(original.datosVisitante.getJugadores());

        finalizada = true;
    }

    @Override
    public void agregarJugadorLocal(Jugador jugador) throws PlanillaNoModificableException,
            JugadorYaPerteneceAListaException {
        throw new PlanillaNoModificableException();
    }

    @Override
    public void agregarJugadorVisitante(Jugador jugador) throws PlanillaNoModificableException,
            JugadorYaPerteneceAListaException {
        throw new PlanillaNoModificableException();
    }

    @Override
    public void setGolesLocal(Integer goles) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    @Override
    public void setGolesVisitante(Integer goles) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    public void setArbitroL(String arbitro) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    public void setDtL(String dt) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    public void setGoleadoresL(String goleadores) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    public void setJuezMesaL(String juezMesa) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    public void setMedicoL(String medico) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    public void setPfL(String pf) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    };

    public void setArbitroV(String arbitro) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    public void setDtV(String dt) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    public void setGoleadoresV(String goleadores) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    public void setJuezMesaV(String juezMesa) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    public void setMedicoV(String medico) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

    public void setPfV(String pf) throws PlanillaNoModificableException {
        throw new PlanillaNoModificableException();
    }

}
