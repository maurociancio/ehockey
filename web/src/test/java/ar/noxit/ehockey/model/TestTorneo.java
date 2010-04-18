package ar.noxit.ehockey.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTorneo {

    private Torneo torneo;

    @BeforeMethod
    public void setUp() {
        torneo = new Torneo("nombre");
    }

    @Test
    public void testObtenerDivision() {
        assertEquals("nombre", torneo.getNombre());
        assertFalse(torneo.iteradorPartidos().hasNext());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCrearTorneoSinNombre() {
        new Torneo(null);
    }
}
