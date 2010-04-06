package ar.noxit.ehockey.model;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class JugadorTest {

    @Test
    public void testCrearJugador() {
        // crear jugador
        Jugador jugador = new Jugador("F0001", "Apellido", "Nombre");

        // verificar atributos
        assertEquals(jugador.getApellido(), "Apellido");
        assertEquals(jugador.getNombre(), "Nombre");
        assertEquals(jugador.getFicha(), "F0001");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCrearJugadorConNull1() {
        new Jugador("F0001", "Apellido", null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCrearJugadorConNull2() {
        new Jugador(null, "Apellido", "nombre");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCrearJugadorConNull3() {
        new Jugador("F0001", null, "nombre");
    }
}
