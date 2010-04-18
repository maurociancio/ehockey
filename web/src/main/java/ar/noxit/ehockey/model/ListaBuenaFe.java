package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ClubNoCoincideException;
import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
import ar.noxit.ehockey.exception.SinClubException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.Validate;

/**
 * Lista de Buena Fe
 * 
 * @author Mauro Ciancio
 * 
 */
public class ListaBuenaFe {

    private Integer id;
    /**
     * Lista de jugadores en esta lista de buena fe.
     */
    private Set<Jugador> jugadores = new HashSet<Jugador>();
    /**
     * Equipo a la que pertenece esta lista de buena fe.
     */
    private Equipo equipo;

    /**
     * No debería ser llamado por el cliente. Este constructor es llamado por
     * {@link Equipo#getListaBuenaFe()}
     * 
     * @param equipo
     *            equipo al que pertenece esta lista de buena fe.
     * @throws IllegalArgumentException
     *             si el equipo es null
     */
    public ListaBuenaFe(Equipo equipo) {
        Validate.notNull(equipo, "el equipo no puede ser null");

        this.equipo = equipo;
    }

    /**
     * Agrega un jugador a la lista de buena fe.
     * 
     * @param jugador
     *            a ser agregado a la lista de buena fe
     * @throws JugadorYaPerteneceAListaException
     *             se lanza si el jugador ya pertence a esta lista de buena fe
     * @throws ClubNoCoincideException
     *             lanzada si el club del jugador no es igual al club de esta
     *             lista de buena fe
     * @throws IllegalArgumentException
     *             si el jugador pasado es null
     */
    public void agregarJugador(Jugador jugador) throws JugadorYaPerteneceAListaException, ClubNoCoincideException {
        Validate.notNull(jugador, "jugador no puede ser null");

        try {
            Club club = jugador.getClub();

            if (!club.equals(this.equipo.getClub())) {
                throw new ClubNoCoincideException(
                        "el club del jugador no es igual al club del equipo de esta lista de buena fe");
            }
        } catch (SinClubException e) {
            throw new ClubNoCoincideException(e);
        }

        if (jugadores.contains(jugador)) {
            throw new JugadorYaPerteneceAListaException("el jugador ya está en la lista de buena fe");
        }
        jugadores.add(jugador);
    }

    /**
     * Devuelve un iterador con los jugadores de esta lista de buena fe.
     * 
     * @return iterador de jugadores que estan en esta lista de buena fe.
     */
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

    /**
     * Equipo al que pertence esta lista de buena fe.
     * 
     * @return equipo
     */
    public Equipo getEquipo() {
        return equipo;
    }

    public void reemplazarJugadores(List<Jugador> jugadores) {
        Validate.notNull(jugadores);

        List<Jugador> seVan = new ArrayList<Jugador>(this.jugadores);
        seVan.removeAll(jugadores);

        List<Jugador> seAgregan = new ArrayList<Jugador>(jugadores);
        seAgregan.removeAll(this.jugadores);

        for (Jugador jugador : seVan) {
            this.jugadores.remove(jugador);
        }
        for (Jugador jugador : seAgregan) {
            this.jugadores.add(jugador);
        }
    }

    public Integer getId() {
        return id;
    }

    protected ListaBuenaFe() {
    }
}
