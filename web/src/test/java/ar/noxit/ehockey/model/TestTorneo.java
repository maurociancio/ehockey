package ar.noxit.ehockey.model;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Torneo;

public class TestTorneo {

    private Torneo torneo;

    @BeforeMethod
    public void setUp() {
        torneo = new Torneo();
    }

    @Test
    public void testDivisionExistente() {
        Division division = new Division("Primera A Hombres");
        torneo.agregarDivision(division);
        assertTrue(torneo.existeDivision(division));
    }

    public void testObtenerDivision() {
        Division division = new Division("Primera A Mujeres");
        torneo.agregarDivision(division);
        assertEquals(division, torneo.getDivision("Primera A Mujeres"));
    }

}
