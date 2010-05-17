package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.PlanillaNoModificableException;
import ar.noxit.ehockey.exception.PlanillaYaFinalizadaException;
import ar.noxit.ehockey.exception.ReglaNegocioException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    private List<Jugador> locales;
    private List<Jugador> visitantes;

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

        // jugadores
        this.locales = new ArrayList<Jugador>();
        llenarJugadores(locales, club1);
        this.visitantes = new ArrayList<Jugador>();
        llenarJugadores(visitantes, club2);

        equipo1.getListaBuenaFe().reemplazarJugadores(locales);
        equipo2.getListaBuenaFe().reemplazarJugadores(visitantes);

        LocalDateTime inicio = new LocalDateTime();
        LocalDateTime now = inicio.minusDays(1);

        this.partido1 = equipo1.jugarContra(torneo, equipo2, 1, 1, 1, inicio, now);
        this.partido2 = equipo2.jugarContra(torneo, equipo1, 1, 2, 1, inicio, now);

        this.partido1.terminarPartido();
    }

    private void llenarJugadores(List<Jugador> locales, Club club) {
        for (int i = 0; i < 10; ++i) {
            locales.add(club.crearNuevoJugador("", "", sector, division));
        }
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

    @Test
    public void testJugadoresEnListaBuenaFe() throws ReglaNegocioException {
        PlanillaPrecargada planillaPrecargada = partido1.getPlanillaPrecargada();

        Set<Jugador> jugadoresLocales = planillaPrecargada.getJugadoresLocales();
        checkJugadoresEnListaBuenaFe(jugadoresLocales, locales);
        Set<Jugador> jugadoresVisitantes = planillaPrecargada.getJugadoresVisitantes();
        checkJugadoresEnListaBuenaFe(jugadoresVisitantes, visitantes);
    }

    @Test
    public void testSanciones() throws ReglaNegocioException {
        PlanillaFinal planilla = partido1.getPlanilla();
        Jugador jugador = locales.get(0);

        // tarjetas
        planilla.getDatosLocal().crearTarjetaPartido(jugador, 5, 0, 0);

        // datos planillas
        rellenarPlanilla(planilla);
        planilla.setGolesLocal(0);
        planilla.setGolesVisitante(1);

        partido1.publicarPlanilla();
        partido1.validarPlanilla();

        Assert.assertFalse(jugador.puedeJugar(partido2));

        PlanillaPrecargada planillaPrecargada = partido2.getPlanillaPrecargada();
        Assert.assertFalse(planillaPrecargada.getJugadoresVisitantes().contains(jugador));
        Assert.assertFalse(planillaPrecargada.getJugadoresLocales().contains(jugador));
        Assert.assertTrue(planillaPrecargada.getJugadoresVisitantes().contains(locales.get(1)));
    }

    private void checkJugadoresEnListaBuenaFe(Set<Jugador> jugadoresLocales, List<Jugador> jugadores) {
        List<Jugador> l = new ArrayList<Jugador>(jugadoresLocales);
        l.removeAll(jugadores);
        Assert.assertEquals(l.size(), 0);
    }
}
