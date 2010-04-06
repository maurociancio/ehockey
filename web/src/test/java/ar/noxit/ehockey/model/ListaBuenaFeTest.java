package ar.noxit.ehockey.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListaBuenaFeTest {

    private ListaBuenaFe lista;
    private Equipo equipo;
    private Club club;

    @BeforeMethod
    public void setUp() {
        this.club = new Club("Geba");
        this.equipo = new Equipo(club);
        this.lista = equipo.getListaBuenaFe();
    }

    @Test
    public void testAgregarJugador() {
        assertFalse(lista.iterator().hasNext());
        Jugador jugador = new Jugador("ficha", "apellido", "nombre");
        lista.agregarJugador(jugador);

        assertTrue(lista.tieneJugador(jugador));
        assertEquals(lista.iterator().next(), jugador);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testBuscarJugadorFalla() {
        lista.tieneJugador(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAgregarJugadorFalla() {
        lista.agregarJugador(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCrearListadoSinEquipo() {
        new ListaBuenaFe(null);
    }
}
