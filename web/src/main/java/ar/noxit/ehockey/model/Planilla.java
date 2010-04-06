package ar.noxit.ehockey.model;

import java.util.ArrayList;
import java.util.List;

public class Planilla {
    Equipo local;
    Equipo visitante;

    String dTL;
    String capitanL;
    String pFisicoL;
    String medicoL;
    String juezDeMesaL;
    String arbitroL;

    String dTV;
    String capitanV;
    String pFisicoV;
    String medicoV;
    String juezDeMesaV;
    String arbitroV;

    String observaciones;
    
    //TODO definir el atributo fecha
    //TODO definir el resto de los atributos que son: Torneo, Rueda, Partido, Sector(damas/caballeros), categoría, división, zona

    List<Jugador> goleadoresLocal = new ArrayList<Jugador>();
    List<Jugador> goleadoresVisitante = new ArrayList<Jugador>();

    public Planilla(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
    }

}
