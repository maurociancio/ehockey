package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;
import ar.noxit.ehockey.exception.ReglaNegocioException;
import org.joda.time.LocalDateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SancionesTest {

    private Partido partido1;
    private Partido partido2;

    @BeforeMethod
    public void setUp() throws ReglaNegocioException {
        Club club1 = new Club("club1");
        Club club2 = new Club("club2");

        Division division = new Division("division");
        Sector sector = new Sector("sector");

        Equipo equipo1 = club1.crearNuevoEquipo("equipo1", division, sector);
        Equipo equipo2 = club2.crearNuevoEquipo("equipo2", division, sector);

        Torneo torneo = new Torneo("el torneo");

        LocalDateTime inicio = new LocalDateTime();
        LocalDateTime now = inicio.minusDays(1);

        this.partido1 = equipo1.jugarContra(torneo, equipo2, 1, 1, 1, inicio, now);
        this.partido2 = equipo2.jugarContra(torneo, equipo1, 1, 2, 1, inicio, now);

        this.partido1.terminarPartido();
    }

    @Test(expectedExceptions = ReglaNegocioException.class)
    public void testGetGolesEnPartidoNoTerminado() throws ReglaNegocioException {
        partido1.getGolesLocal();
    }

    @Test
    public void testSanciones() throws ReglaNegocioException {
        PlanillaFinal planilla1 = partido1.getPlanilla();
        planilla1.setGolesLocal(0);
        planilla1.setGolesVisitante(1);
        partido1.finalizarPlanilla();

        Assert.assertEquals(partido1.getGolesLocal().intValue(), 0);
        Assert.assertEquals(partido1.getGolesVisitante().intValue(), 1);
        Assert.assertTrue(partido1.isJugado());
    }

    @Test(expectedExceptions = PlanillaYaFinalizadaException.class)
    public void testFinalizar2Veces() throws ReglaNegocioException {
        PlanillaFinal planilla1 = partido1.getPlanilla();
        planilla1.setGolesLocal(0);
        planilla1.setGolesVisitante(1);
        partido1.finalizarPlanilla();
        partido1.finalizarPlanilla();
    }
}
