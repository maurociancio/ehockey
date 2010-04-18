package ar.noxit.ehockey.persistent;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Sector;
import org.testng.annotations.Test;

public class JugadorPersistentTest extends BaseSessionTest {

    @Test
    public void testPersistJugador() {
        Club club = new Club("club");
        Jugador jugador = club.crearNuevoJugador("apellido", "nombre", new Sector("sector"), new Division("division"));

        session.persist(jugador.getClass().getSimpleName(), jugador);
    }
}