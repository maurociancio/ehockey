package ar.noxit.ehockey.model;

import static org.testng.Assert.assertEquals;
import ar.noxit.ehockey.exception.EquiposInvalidosException;
import org.joda.time.LocalDateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PartidoTest {

    private Club club;
    private Division division;
    private Sector sector;

    @BeforeMethod
    public void setUp() {
        club = new Club("club");
        division = new Division("division");
        sector = new Sector("sector");
    }

    @Test
    public void testCreacionPartido() throws EquiposInvalidosException {
        Equipo equipo1 = club.crearNuevoEquipo("Equipo1", division, sector);
        Equipo equipo2 = club.crearNuevoEquipo("Equipo2", division, sector);

        LocalDateTime inicio = new LocalDateTime();
        Partido partido = equipo1.jugarContra(equipo2, 1, inicio);

        assertEquals(partido.getFechaDelTorneo(), new Integer(1));
        assertEquals(partido.getInicio(), inicio);
        assertEquals(partido.getLocal(), equipo1);
        assertEquals(partido.getVisitante(), equipo2);
        assertEquals(partido.getId(), null);
    }

    @Test(expectedExceptions = EquiposInvalidosException.class)
    public void testPartidoMismoLocalYVisitante() throws EquiposInvalidosException {
        Equipo equipo = club.crearNuevoEquipo("Equipo1", division, sector);
        LocalDateTime inicio = new LocalDateTime();

        equipo.jugarContra(equipo, 1, inicio);
    }
}
