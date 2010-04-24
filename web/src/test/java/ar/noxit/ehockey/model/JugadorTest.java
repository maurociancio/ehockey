package ar.noxit.ehockey.model;

import static org.testng.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.testng.annotations.Test;

public class JugadorTest {

    @Test
    public void testCrearJugador() {
        // crear jugador
        Sector sector = new Sector("sector");
        Division division = new Division("division");
        Jugador jugador = new Jugador("Apellido", "Nombre", new Club("club"),
                sector, division);

        // verificar atributos
        assertEquals(jugador.getApellido(), "Apellido");
        assertEquals(jugador.getNombre(), "Nombre");
        assertEquals(jugador.getDivision(), division);
        assertEquals(jugador.getSector(), sector);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCrearJugadorConNull1() {
        Sector sector = new Sector("sector");
        new Jugador("Apellido", "Nombre", new Club("club"), sector, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCrearJugadorConNull2() {
        Division division = new Division("division");
        new Jugador("Apellido", "Nombre", new Club("club"), null, division);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCrearJugadorConNull3() {
        Sector sector = new Sector("sector");
        Division division = new Division("division");
        new Jugador("Apellido", null, new Club("club"), sector, division);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCrearJugadorConNull4() {
        Sector sector = new Sector("sector");
        Division division = new Division("division");
        new Jugador(null, "Nombre", new Club("club"), sector, division);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCrearJugadorConNull5() {
        Division division = new Division("division");
        Sector sector = new Sector("s");
        new Jugador("Apellido", "Nombre", null, sector, division);
    }

    @Test
    public void testFechaCreacionJugador() {
        Division division = new Division("division");
        Sector sector = new Sector("s");
        Jugador jugador = new Jugador("Apellido", "Nombre", new Club("c"),
                sector, division);
        assertEquals(jugador.getFechaAlta(), new LocalDate());
    }
}
