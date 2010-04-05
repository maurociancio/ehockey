package ar.noxit.ehockey.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaBuenaFe {

    private List<Jugador> jugadores = new ArrayList<Jugador>();
    private Equipo equipo;

    /**
     * No debería ser llamado por el cliente. Este constructor es llamado por
     * {@link Equipo#getListaBuenaFe()}
     * 
     * @param equipo
     *            equipo al que pertenece esta lista de buena fe.
     */
    public ListaBuenaFe(Equipo equipo) {
        this.equipo = equipo;
    }

    public void agregarJugador(Jugador jugador) {
        // TODO resolver que se hace si el jugador ya existe. Pero hay que
        // ckeckearlo
        jugadores.add(jugador);
    }

    public Iterator<Jugador> iterator() {
        return jugadores.iterator();
    }

    public boolean tieneJugador(Jugador jugador) {
        return jugadores.contains(jugador);
    }

    public Equipo getEquipo() {
        return equipo;
    }
}
