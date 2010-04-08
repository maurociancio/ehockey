package ar.noxit.ehockey.web.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.Ronda;

public class TestRonda {

    private Ronda ronda;

    @BeforeMethod
    public void setUp() {
        ronda = new Ronda();
    }

    @Test
    public void testExistePartido() {
        Partido partido = new Partido(new Equipo("San Jorge"), new Equipo(
                "San Juan"));
        ronda.cargarPartidoAlaRonda(partido);
        assertTrue(ronda.existePartidoRonda(partido));
    }
}
