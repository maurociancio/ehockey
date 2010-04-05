package ar.noxit.ehockey.model;

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
        Jugador jugador = new Jugador();
        lista.agregarJugador(jugador);
        assertTrue(lista.tieneJugador(jugador));
    }

}
