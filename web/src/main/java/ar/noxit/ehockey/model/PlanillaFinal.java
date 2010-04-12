package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
import ar.noxit.ehockey.exception.PlanillaNoModificableException;

public class PlanillaFinal extends Planilla {
    
    public PlanillaFinal(Planilla original) {
        super();

        id = original.id;

        local = original.local;
        visitante = original.visitante;
        dTL = original.dTL;
        capitanL = original.capitanL;
        pFisicoL = original.pFisicoL;
        medicoL = original.medicoL;
        juezDeMesaL = original.juezDeMesaL;
        arbitroL = original.arbitroL;

        visitante = original.visitante;
        dTV = original.dTV;
        capitanV = original.capitanV;
        pFisicoV = original.pFisicoV;
        medicoV = original.medicoV;
        juezDeMesaV = original.juezDeMesaV;
        arbitroV = original.arbitroV;

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
}
