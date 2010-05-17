package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.PlanillaNoModificableException;
import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;
import ar.noxit.ehockey.exception.ReglaNegocioException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SancionesTest {

    private Partido partido1;
    private Partido partido2;
    private Club club1;
    private Division division;
    private Sector sector;
    private Club club2;

    private void rellenarPlanilla(PlanillaFinal planilla) throws PlanillaNoModificableException {
        planilla.setArbitroL("");
        planilla.setArbitroV("");
        planilla.setCapitanL("");
        planilla.setCapitanV("");
        planilla.setDtL("");
        planilla.setDtV("");
        planilla.setGoleadoresL("");
        planilla.setGoleadoresV("");
        planilla.setJuezMesaL("");
        planilla.setJuezMesaV("");
        planilla.setMedicoL("");
        planilla.setMedicoV("");
        planilla.setPfL("");
        planilla.setPfV("");

        List<Jugador> locales = new ArrayList<Jugador>();
        for (int i = 0; i < 10; ++i) {
            locales.add(club1.crearNuevoJugador("", "", sector, division));
        }
        List<Jugador> visitantes = new ArrayList<Jugador>();
        for (int i = 0; i < 10; ++i) {
            visitantes.add(club2.crearNuevoJugador("", "", sector, division));
        }

        planilla.setJugadoresLocal(locales);
        planilla.setJugadoresVisitante(visitantes);
    }

    @BeforeMethod
    public void setUp() throws ReglaNegocioException {
        this.club1 = new Club("club1");
        this.club2 = new Club("club2");

        this.division = new Division("division");
        this.sector = new Sector("sector");

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
    public void testSinSanciones() throws ReglaNegocioException {
        PlanillaFinal planilla1 = partido1.getPlanilla();
        rellenarPlanilla(planilla1);
        planilla1.setGolesLocal(0);
        planilla1.setGolesVisitante(1);
        partido1.publicarPlanilla();
        partido1.validarPlanilla();

        Assert.assertEquals(partido1.getGolesLocal().intValue(), 0);
        Assert.assertEquals(partido1.getGolesVisitante().intValue(), 1);
        Assert.assertTrue(partido1.isJugado());
    }

    @Test(expectedExceptions = PlanillaYaFinalizadaException.class)
    public void testFinalizar2Veces() throws ReglaNegocioException {
        PlanillaFinal planilla1 = partido1.getPlanilla();
        rellenarPlanilla(planilla1);
        planilla1.setGolesLocal(0);
        planilla1.setGolesVisitante(1);
        partido1.publicarPlanilla();
        partido1.validarPlanilla();
        partido1.validarPlanilla();
    }
}
