package ar.noxit.ehockey.web;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import ar.noxit.ehockey.configuration.MenuItem;
import ar.noxit.ehockey.exception.SessionClosedException;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.IHorarioService;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.ehockey.service.ITablaPosicionesService;
import ar.noxit.ehockey.service.ITorneoService;
import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.ehockey.web.pages.HomePage;
import ar.noxit.ehockey.web.pages.authentication.LoginPage;
import ar.noxit.ehockey.web.pages.buenafe.VerListaBuenaFePage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.header.IMenuItemProvider;
import ar.noxit.ehockey.web.pages.jugadores.JugadorAltaPage;
import ar.noxit.ehockey.web.pages.jugadores.JugadorPage;
import ar.noxit.ehockey.web.pages.torneo.ListadoTorneoPage;
import ar.noxit.ehockey.web.pages.torneo.NuevoTorneoPage;
import ar.noxit.ehockey.web.pages.torneo.TorneoPage;
import ar.noxit.exceptions.NoxitException;
import com.ttdev.wicketpagetest.MockableBeanInjector;
import java.util.ArrayList;
import org.apache.wicket.Session;
import org.easymock.EasyMock;
import org.joda.time.LocalDateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Simple test using the WicketTester
 */
public class TestRenderPage extends BaseSpringWicketTest {

    @Test
    public void testRenderHomePage() throws NoxitException {
        mockTorneoService();
        mockTablaService();

        startPageAndTestRendered(HomePage.class);
    }

    @Test
    public void testLoginPage() {
        startPageAndTestRendered(LoginPage.class);
    }

    @Test
    public void testTorneoPage() {
        startPageAndTestRendered(TorneoPage.class);
    }

    @Test
    public void testCrearTorneoPage() throws NoxitException {
        mockEquipoService();
        mockClubService();
        mockTorneoService();
        mockExceptionConverter();
        mockDivisionService();
        mockSectorService();

        startPageAndTestRendered(NuevoTorneoPage.class);
    }

    @Test
    public void testListadoTorneoPage() throws NoxitException {
        mockTorneoService();

        startPageAndTestRendered(ListadoTorneoPage.class);
    }

    @Test
    public void testJugadoresPage() {
        startPageAndTestRendered(JugadorPage.class);
    }

    @Test
    public void testJugadoresAltaPage() throws NoxitException {
        mockJugadoresService();
        mockClubService();
        mockDivisionService();
        mockSectorService();

        startPageAndTestRendered(JugadorAltaPage.class);
    }

    private void mockJugadoresService() {
        IJugadorService jugadorService = createMock(IJugadorService.class);
        MockableBeanInjector.mockBean("jugadorService", jugadorService);
        replay(jugadorService);
    }

    private void mockDivisionService() throws NoxitException {
        IDivisionService divisionService = createMock(IDivisionService.class);
        expect(divisionService.getAll()).andReturn(new ArrayList<Division>()).anyTimes();
        replay(divisionService);
        MockableBeanInjector.mockBean("divisionService", divisionService);
    }

    private void mockSectorService() throws NoxitException {
        ISectorService sectorService = createMock(ISectorService.class);
        expect(sectorService.getAll()).andReturn(new ArrayList<Sector>()).anyTimes();
        replay(sectorService);
        MockableBeanInjector.mockBean("sectorService", sectorService);
    }

    private void mockExceptionConverter() {
        IExceptionConverter exceptionConverter = createMock(IExceptionConverter.class);
        replay(exceptionConverter);
        MockableBeanInjector.mockBean("exceptionConverter", exceptionConverter);
    }

    @Test
    public void testRenderVerListaBuenaFe() throws NoxitException {
        mockEquipoService();
        mockClubService();

        startPageAndTestRendered(VerListaBuenaFePage.class);
    }

    private void mockClubService() throws NoxitException {
        IClubService clubService = createMock(IClubService.class);
        expect(clubService.getAll()).andReturn(new ArrayList<Club>()).times(3);
        replay(clubService);
        MockableBeanInjector.mockBean("clubService", clubService);
    }

    private void mockEquipoService() {
        IEquipoService equipoService = createMock(IEquipoService.class);
        MockableBeanInjector.mockBean("equipoService", equipoService);
        replay(equipoService);
    }

    @BeforeMethod
    public void mockHeaderItems() throws NoxitException {
        mockMenuItemProvider();
        mockHorarioService();
        mockUsuarioService();
    }

    private void mockUsuarioService() throws SessionClosedException, NoxitException {
        // usuarios service
        IUsuarioService usuariosService = createMock(IUsuarioService.class);
        expect(usuariosService.getUsuarioConectado((Session) EasyMock.anyObject())).andReturn(null).anyTimes();
        expect(usuariosService.getAll()).andReturn(new ArrayList<Usuario>()).anyTimes();
        MockableBeanInjector.mockBean("usuarioService", usuariosService);

        // replay
        replay(usuariosService);
    }

    private void mockHorarioService() throws NoxitException {
        // horario service
        IHorarioService horarioService = createMock(IHorarioService.class);
        expect(horarioService.getHoraSistema()).andReturn(new LocalDateTime());
        MockableBeanInjector.mockBean("horarioService", horarioService);
        replay(horarioService);
    }

    private void mockMenuItemProvider() {
        // menu item provider
        IMenuItemProvider menuItemProvider = createMock(IMenuItemProvider.class);

        ArrayList<IMenuItem> value = new ArrayList<IMenuItem>();
        value.add(new MenuItem("home", HomePage.class, null));
        expect(menuItemProvider.getMenuItems()).andReturn(value);

        MockableBeanInjector.mockBean("menuItemProvider", menuItemProvider);
        replay(menuItemProvider);
    }

    private void mockTablaService() {
        // tabla service
        ITablaPosicionesService tablaService = createMock(ITablaPosicionesService.class);
        MockableBeanInjector.mockBean("tablaService", tablaService);
        replay(tablaService);
    }

    private void mockTorneoService() throws NoxitException {
        // torneo service
        ITorneoService torneoService = createMock(ITorneoService.class);
        expect(torneoService.getAll()).andReturn(new ArrayList<Torneo>()).times(3);
        MockableBeanInjector.mockBean("torneoService", torneoService);
        replay(torneoService);
    }
}
