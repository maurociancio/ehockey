package ar.noxit.ehockey.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.Validate;

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

    /**
     * Agrega un jugador a la lista de buena fe.
     * 
     * @param jugador
     *            a ser agregado a la lista de buena fe
     * @throws IllegalArgumentException
     *             si el jugador pasado es null
     */
    public void agregarJugador(Jugador jugador) {
        Validate.notNull(jugador, "jugador no puede ser null");

        // TODO resolver que se hace si el jugador ya existe. Pero hay que
        // ckeckearlo
        jugadores.add(jugador);
    }

    public Iterator<Jugador> iterator() {
        return jugadores.iterator();
    }

    /**
     * Chequea que un jugador esté en la lista de buena fe.
     * 
     * @param jugador
     *            para chequear que este en la lista de buena fe o no.
     * @return si el jugador está en la lista de buena fe o no.
     */
    public boolean tieneJugador(Jugador jugador) {
        Validate.notNull(jugador, "jugador no puede ser null");

        return jugadores.contains(jugador);
    }

    public Equipo getEquipo() {
        return equipo;
    }
}
