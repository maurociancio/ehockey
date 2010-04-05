package ar.noxit.ehockey.model;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class ClubTest {

    @Test
    public void testCrearClub() {
        // creamos
        Club equipo = new Club("club");

        // chequeamos
        assertEquals(equipo.getNombre(), "club");
    }
}
