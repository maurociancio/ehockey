package ar.noxit.ehockey.web.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Ronda;

public class TestDivision {

    private Division division;

    @BeforeMethod
    public void setUp() {
        division = new Division("Primera B Hombres");
    }

    @Test
    public void testNombreDivision() {
        assertEquals(division.getNombre(), "Primera B Hombres");
    }

    @Test
    public void testExisteRonda() {
        Ronda ronda = new Ronda();
        division.agregarRonda(ronda);
        assertTrue(division.existeRonda(ronda));
    }

    @Test
    public void testObtenerEquipo() {
        Equipo equipo = new Equipo("GEBA");
        division.agregarEquipo(equipo);
        assertEquals(equipo, division.obtenerEquipo("GEBA"));
    }

    @Test
    public void testGeneracionDivision() {
        Ronda ronda = new Ronda();
        division.agregarRonda(ronda);
        division.agregarEquipo(new Equipo("San Pedro"));
        division.agregarEquipo(new Equipo("San Juan"));
        division.agregarEquipo(new Equipo("San Jorge"));
        division.generarDivision();
        ronda = division.obtenerRonda(0);
        assertEquals(ronda.getCantidadPartidos(), 6);
    }
}
