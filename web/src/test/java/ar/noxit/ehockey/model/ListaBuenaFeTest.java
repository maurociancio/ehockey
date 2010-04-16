package ar.noxit.ehockey.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import ar.noxit.ehockey.exception.ClubNoCoincideException;
import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
import ar.noxit.ehockey.exception.ReglaNegocioException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListaBuenaFeTest {

    private ListaBuenaFe lista;
    private Equipo equipo;
    private Club club;

    @BeforeMethod
    public void setUp() {
        this.club = new Club("Geba");
        this.equipo = club.crearNuevoEquipo("equipo", new Division("d"), new Sector("s"));
        this.lista = equipo.getListaBuenaFe();
    }

    @Test
    public void testAgregarJugador() throws ReglaNegocioException {
        assertFalse(lista.iterator().hasNext());

        Jugador jugador = club.crearNuevoJugador("apellido", "nombre", new Sector("s"), new Division("d"));
        lista.agregarJugador(jugador);

        assertTrue(lista.tieneJugador(jugador));
        assertEquals(lista.iterator().next(), jugador);

        assertEquals(equipo, lista.getEquipo());
    }

    @Test(expectedExceptions = ClubNoCoincideException.class)
    public void testAgregarJugadorDeOtroClub() throws ReglaNegocioException {
        Club otroClub = new Club("otro");
        Jugador jugador = otroClub.crearNuevoJugador("lala", "lala", new Sector("s"), new Division("s"));
        lista.agregarJugador(jugador);
    }

    @Test(expectedExceptions = JugadorYaPerteneceAListaException.class)
    public void testAgregarJugador2Veces() throws ReglaNegocioException {
        Jugador jugador = club.crearNuevoJugador("apellido", "nombre", new Sector("s"), new Division("d"));
        lista.agregarJugador(jugador);
        lista.agregarJugador(jugador);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testBuscarJugadorFalla() {
        lista.tieneJugador(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAgregarJugadorFalla() throws ReglaNegocioException {
        lista.agregarJugador(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCrearListadoSinEquipo() {
        new ListaBuenaFe(null);
    }

    @Test
    public void testObtenerEquipo() {
        assertEquals(equipo, lista.getEquipo());
    }
}
