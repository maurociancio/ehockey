package ar.noxit.ehockey.model;

import java.util.Iterator;

import org.joda.time.LocalDateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ar.noxit.ehockey.exception.EquiposInvalidosException;
import ar.noxit.ehockey.exception.FechaInvalidaException;
import ar.noxit.ehockey.exception.PartidoNoJugadoPorEquipoException;
import ar.noxit.ehockey.exception.PartidoNoTerminadoException;
import ar.noxit.ehockey.exception.PartidoYaPerteneceATorneoExcepcion;
import ar.noxit.ehockey.exception.PartidoYaTerminadoException;
import ar.noxit.ehockey.exception.TorneoNoCoincideException;

import static org.testng.Assert.assertEquals;

public class TablaPosicionesTest {

    private Torneo torneo;
    private TablaPosiciones tabla;
    private Club club;
    private Division division;
    private Sector sector;
    private Equipo eq1;
    private Equipo eq2;

    @BeforeMethod
    public void setUp() throws TorneoNoCoincideException,
            PartidoYaPerteneceATorneoExcepcion, EquiposInvalidosException,
            FechaInvalidaException, PartidoNoTerminadoException,
            PartidoNoJugadoPorEquipoException, PartidoYaTerminadoException {
        torneo = new Torneo("Clausura");
        club = new Club("club");
        this.division = new Division("d");
        this.sector = new Sector("s");
        this.eq1 = club.crearNuevoEquipo("River", this.division, this.sector);
        this.eq2 = club.crearNuevoEquipo("Boca", this.division, this.sector);
        Partido partido1 = this.eq1.jugarContra(torneo, eq2, new Integer(1),
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        Partido partido2 = this.eq2.jugarContra(torneo, eq1, new Integer(2),
                new LocalDateTime(), new LocalDateTime().minusDays(2));
        partido1.getPlanillaPrecargada();
        partido1.finalizarPlanilla();
        partido1.terminarPartido(2, 0);
        partido2.getPlanillaPrecargada();
        partido2.finalizarPlanilla();
        partido2.terminarPartido(0, 2);
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
}
