package ar.noxit.ehockey.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
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
    public void testAgregarJugador() throws JugadorYaPerteneceAListaException {
        assertFalse(lista.iterator().hasNext());
        Jugador jugador = new Jugador("apellido", "nombre", new Sector("s"), new Division("d"));
        lista.agregarJugador(jugador);

        assertTrue(lista.tieneJugador(jugador));
        assertEquals(lista.iterator().next(), jugador);

        assertEquals(equipo, lista.getEquipo());
    }

    @Test(expectedExceptions = JugadorYaPerteneceAListaException.class)
    public void testAgregarJugador2Veces() throws JugadorYaPerteneceAListaException {
        Jugador jugador = new Jugador("apellido", "nombre", new Sector("s"), new Division("d"));
        lista.agregarJugador(jugador);
        lista.agregarJugador(jugador);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testBuscarJugadorFalla() {
        lista.tieneJugador(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAgregarJugadorFalla() throws JugadorYaPerteneceAListaException {
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
