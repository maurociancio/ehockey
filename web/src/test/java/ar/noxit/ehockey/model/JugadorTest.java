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
}
