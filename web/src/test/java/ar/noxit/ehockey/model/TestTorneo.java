package ar.noxit.ehockey.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import ar.noxit.ehockey.exception.PartidoYaPerteneceATorneoExcepcion;
import ar.noxit.ehockey.exception.TorneoNoCoincideException;
import ar.noxit.exceptions.NoxitException;
import org.joda.time.LocalDateTime;
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

    @Test(expectedExceptions = TorneoNoCoincideException.class)
    public void testAgregarPartidoAOtroTorneo() throws NoxitException {
        Club club = new Club("nombre");

        Sector sector = new Sector("sector");
        Division division = new Division("division");

        Equipo equipo1 = club.crearNuevoEquipo("equipo1", division, sector);
        Equipo equipo2 = club.crearNuevoEquipo("equipo2", division, sector);

        Torneo torneo1 = new Torneo("torneo1");
        Torneo torneo2 = new Torneo("torneo2");

        Partido partido = new Partido(torneo1, equipo1, equipo2, 1, new LocalDateTime());

        torneo2.agregarPartido(partido);
    }

    @Test(expectedExceptions = PartidoYaPerteneceATorneoExcepcion.class)
    public void testAgregarDosVecesPartido() throws NoxitException {
        Club club = new Club("nombre");

        Sector sector = new Sector("sector");
        Division division = new Division("division");

        Equipo equipo1 = club.crearNuevoEquipo("equipo1", division, sector);
        Equipo equipo2 = club.crearNuevoEquipo("equipo2", division, sector);

        Partido partido = equipo1.jugarContra(torneo, equipo2, 1, new LocalDateTime());
        torneo.agregarPartido(partido);
    }
}
