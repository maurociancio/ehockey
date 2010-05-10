package ar.noxit.ehockey.model;

import static org.testng.Assert.assertEquals;
import ar.noxit.ehockey.exception.EquiposInvalidosException;
import ar.noxit.ehockey.exception.FechaInvalidaException;
import ar.noxit.ehockey.exception.PartidoNoJugadoPorEquipoException;
import ar.noxit.ehockey.exception.PartidoNoTerminadoException;
import ar.noxit.ehockey.exception.PartidoYaTerminadoException;
import ar.noxit.ehockey.exception.ReglaNegocioException;
import org.joda.time.LocalDateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TablaPosicionesTest {

    private Torneo torneo;
    private TablaPosiciones tabla;
    private Club club;
    private Division division;
    private Sector sector;
    private Equipo eq1;
    private Equipo eq2;

    private void terminarPartido(Partido partido, int golesLocal, int golesVisitante) throws ReglaNegocioException {
        Planilla planilla = partido.getPlanilla();
        planilla.setGolesLocal(golesLocal);
        planilla.setGolesVisitante(golesVisitante);
        partido.finalizarPlanilla();
    }

    @BeforeMethod
    public void setUp() throws ReglaNegocioException {
        torneo = new Torneo("Clausura");
        club = new Club("club");
        this.division = new Division("d");
        this.sector = new Sector("s");
        this.eq1 = club.crearNuevoEquipo("River", this.division, this.sector);
        this.eq2 = club.crearNuevoEquipo("Boca", this.division, this.sector);
        Partido partido1 = this.eq1.jugarContra(torneo, eq2, 1, 1, 1,
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        Partido partido2 = this.eq2.jugarContra(torneo, eq1, 2, 1, 1,
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        terminarPartido(partido1, 2, 0);
        terminarPartido(partido2, 0, 2);
        tabla = torneo.crearTablaPosiciones().calcularTabla();
    }

    @Test
    public void testPartidosJugados() {
        assertEquals(tabla.getDatosTabla(eq1).getPartidosJugados(), 2);
        assertEquals(tabla.getDatosTabla(eq2).getPartidosJugados(), 2);
    }

    @Test
    public void testPartidosGanados() {
        assertEquals(tabla.getDatosTabla(eq1).getPartidosGanados(), 2);
        assertEquals(tabla.getDatosTabla(eq2).getPartidosGanados(), 0);
    }

    @Test
    public void testPartidosEmpatados() {
        assertEquals(tabla.getDatosTabla(eq1).getPartidosEmpatados(), 0);
        assertEquals(tabla.getDatosTabla(eq2).getPartidosEmpatados(), 0);
    }

    @Test
    public void testPartidosPerdidos() {
        assertEquals(tabla.getDatosTabla(eq1).getPartidosPerdidos(), 0);
        assertEquals(tabla.getDatosTabla(eq2).getPartidosPerdidos(), 2);
    }

    @Test
    public void testGolesEnContra() {
        assertEquals(tabla.getDatosTabla(eq1).getGolesContra(), 0);
        assertEquals(tabla.getDatosTabla(eq2).getGolesContra(), 4);
    }

    @Test
    public void testGolesAFavor() {
        assertEquals(tabla.getDatosTabla(eq1).getGolesFavor(), 4);
        assertEquals(tabla.getDatosTabla(eq2).getGolesFavor(), 0);
    }

    @Test
    public void testDiferenciaGol() {
        assertEquals(tabla.getDatosTabla(eq1).getDiferenciaGol(), 4);
        assertEquals(tabla.getDatosTabla(eq2).getDiferenciaGol(), -4);
    }

    @Test
    public void testPuntajeEquipo() {
        assertEquals(tabla.getDatosTabla(eq1).getPuntos(), 6);
        assertEquals(tabla.getDatosTabla(eq2).getPuntos(), 0);
    }

    @Test
    public void testPartidosPorDivision() {
        tabla.filtroTabla(new Division("d2")).calcularTabla();
        assertEquals(tabla.getDatosTabla(eq1).getPuntos(), 0);
        assertEquals(tabla.getDatosTabla(eq2).getPuntos(), 0);
        assertEquals(tabla.getDatosTabla(eq1).getPartidosJugados(), 0);
        assertEquals(tabla.getDatosTabla(eq2).getPartidosJugados(), 0);
    }

    @Test
    public void testPartidosPorDivision2() throws EquiposInvalidosException,
            FechaInvalidaException, PartidoYaTerminadoException,
            PartidoNoTerminadoException, PartidoNoJugadoPorEquipoException, ReglaNegocioException {
        Division div = new Division("d2");
        Equipo eq3 = club.crearNuevoEquipo("Chicago", div, this.sector);
        Equipo eq4 = club.crearNuevoEquipo("Racing", div, this.sector);
        Partido partido1 = eq3.jugarContra(torneo, eq4, 1, 1, 1,
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        Partido partido2 = eq4.jugarContra(torneo, eq3, 1, 1, 1,
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        terminarPartido(partido1, 4, 4);
        terminarPartido(partido2, 2, 2);
        tabla.filtroTabla(div).calcularTabla();
        assertEquals(tabla.getDatosTabla(eq3).getPuntos(), 2);
        assertEquals(tabla.getDatosTabla(eq4).getPuntos(), 2);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosJugados(), 2);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosJugados(), 2);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosEmpatados(), 2);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosEmpatados(), 2);
        assertEquals(tabla.getDatosTabla(eq3).getGolesFavor(), 6);
        assertEquals(tabla.getDatosTabla(eq4).getGolesFavor(), 6);
        assertEquals(tabla.getDatosTabla(eq3).getDiferenciaGol(), 0);
        assertEquals(tabla.getDatosTabla(eq4).getDiferenciaGol(), 0);
    }

    @Test
    public void testPartidosPorSector() {
        tabla.filtroTabla(new Sector("s2")).calcularTabla();
        assertEquals(tabla.getDatosTabla(eq1).getPuntos(), 0);
        assertEquals(tabla.getDatosTabla(eq2).getPuntos(), 0);
        assertEquals(tabla.getDatosTabla(eq1).getPartidosJugados(), 0);
        assertEquals(tabla.getDatosTabla(eq2).getPartidosJugados(), 0);
    }

    @Test
    public void testPartidosPorSector2() throws EquiposInvalidosException,
            FechaInvalidaException, PartidoYaTerminadoException,
            PartidoNoTerminadoException, PartidoNoJugadoPorEquipoException, ReglaNegocioException {
        Sector sec = new Sector("s2");
        Equipo eq3 = club.crearNuevoEquipo("Chicago", this.division, sec);
        Equipo eq4 = club.crearNuevoEquipo("Racing", this.division, sec);
        Partido partido1 = eq3.jugarContra(torneo, eq4, 1, 1, 1,
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        Partido partido2 = eq4.jugarContra(torneo, eq3, 2, 1, 1,
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        terminarPartido(partido1, 4, 0);
        terminarPartido(partido2, 2, 2);
        tabla.filtroTabla(sec).calcularTabla();
        assertEquals(tabla.getDatosTabla(eq3).getPuntos(), 4);
        assertEquals(tabla.getDatosTabla(eq4).getPuntos(), 1);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosJugados(), 2);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosJugados(), 2);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosEmpatados(), 1);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosEmpatados(), 1);
        assertEquals(tabla.getDatosTabla(eq3).getGolesFavor(), 6);
        assertEquals(tabla.getDatosTabla(eq4).getGolesFavor(), 2);
        assertEquals(tabla.getDatosTabla(eq3).getDiferenciaGol(), 4);
        assertEquals(tabla.getDatosTabla(eq4).getDiferenciaGol(), -4);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosGanados(), 1);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosGanados(), 0);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosEmpatados(), 1);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosEmpatados(), 1);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosPerdidos(), 0);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosPerdidos(), 1);
    }

    public void testPartidosPorSectorDivision()
            throws EquiposInvalidosException, FechaInvalidaException,
            PartidoYaTerminadoException, PartidoNoTerminadoException,
            PartidoNoJugadoPorEquipoException, ReglaNegocioException {
        Sector sec = new Sector("s2");
        Division div = new Division("d2");
        Equipo eq3 = club.crearNuevoEquipo("Chicago", div, sec);
        Equipo eq4 = club.crearNuevoEquipo("Racing", div, sec);
        Partido partido1 = eq3.jugarContra(torneo, eq4, 1, 1, 1,
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        Partido partido2 = eq4.jugarContra(torneo, eq3, 2, 1, 1,
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        Partido partido3 = eq1.jugarContra(torneo, eq3, 1, 1, 1,
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        Partido partido4 = eq4.jugarContra(torneo, eq2, 2, 1, 1,
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        terminarPartido(partido1, 2, 1);
        terminarPartido(partido2, 0, 0);
        terminarPartido(partido3, 2, 0);
        terminarPartido(partido4, 3, 4);
        tabla.filtroTabla(sec).filtroTabla(div).calcularTabla();
        assertEquals(tabla.getDatosTabla(eq3).getPuntos(), 6);
        assertEquals(tabla.getDatosTabla(eq4).getPuntos(), 0);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosJugados(), 2);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosJugados(), 2);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosEmpatados(), 0);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosEmpatados(), 0);
        assertEquals(tabla.getDatosTabla(eq3).getGolesFavor(), 4);
        assertEquals(tabla.getDatosTabla(eq4).getGolesFavor(), 4);
        assertEquals(tabla.getDatosTabla(eq3).getDiferenciaGol(), 0);
        assertEquals(tabla.getDatosTabla(eq4).getDiferenciaGol(), 0);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosGanados(), 1);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosGanados(), 1);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosEmpatados(), 0);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosEmpatados(), 0);
        assertEquals(tabla.getDatosTabla(eq3).getPartidosPerdidos(), 1);
        assertEquals(tabla.getDatosTabla(eq4).getPartidosPerdidos(), 1);
    }

}
