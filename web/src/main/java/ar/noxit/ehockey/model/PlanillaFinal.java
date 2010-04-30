package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;

import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
import ar.noxit.ehockey.exception.PlanillaNoModificableException;

public class PlanillaFinal extends Planilla {

    public PlanillaFinal(Planilla original) {
        super();

        Validate.notNull(original, "La planilla no puede ser null");

        id = original.id;

        local = original.local;
        visitante = original.visitante;
        dTL = original.dTL;
        capitanL = original.capitanL;
        pFisicoL = original.pFisicoL;
        medicoL = original.medicoL;
        juezDeMesaL = original.juezDeMesaL;
        arbitroL = original.arbitroL;
        golesL = original.golesL;

        visitante = original.visitante;
        dTV = original.dTV;
        capitanV = original.capitanV;
        pFisicoV = original.pFisicoV;
        medicoV = original.medicoV;
        juezDeMesaV = original.juezDeMesaV;
        arbitroV = original.arbitroV;
        golesV = original.golesV;

        observaciones = original.observaciones;

        jugadoresL.addAll(original.jugadoresL);
        jugadoresV.addAll(original.jugadoresV);
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
    public void setGolesLocal(Integer goles) throws PlanillaNoModificableException{
        throw new PlanillaNoModificableException();
    }
    
    @Override
    public void setGolesVisitante(Integer goles) throws PlanillaNoModificableException{
        super.setGolesVisitante(goles);
    }
    
}
