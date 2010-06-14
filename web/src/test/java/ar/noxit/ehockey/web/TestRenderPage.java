package ar.noxit.ehockey.web;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import ar.noxit.ehockey.configuration.MenuItem;
import ar.noxit.ehockey.exception.SessionClosedException;
import ar.noxit.ehockey.model.Administrador;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Jugador;
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
import ar.noxit.ehockey.web.pages.AdministracionPage;
import ar.noxit.ehockey.web.pages.HomePage;
import ar.noxit.ehockey.web.pages.authentication.LoginPage;
import ar.noxit.ehockey.web.pages.buenafe.VerListaBuenaFePage;
import ar.noxit.ehockey.web.pages.clubes.ClubAltaPage;
import ar.noxit.ehockey.web.pages.clubes.ClubPage;
import ar.noxit.ehockey.web.pages.clubes.ClubVerPage;
import ar.noxit.ehockey.web.pages.equipos.EquiposPage;
import ar.noxit.ehockey.web.pages.fechahora.FechaHoraPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.header.IMenuItemProvider;
import ar.noxit.ehockey.web.pages.jugadores.JugadorAltaPage;
import ar.noxit.ehockey.web.pages.jugadores.JugadorBajaPage;
import ar.noxit.ehockey.web.pages.jugadores.JugadorModificarPage;
import ar.noxit.ehockey.web.pages.jugadores.JugadorPage;
import ar.noxit.ehockey.web.pages.jugadores.JugadorPlano;
import ar.noxit.ehockey.web.pages.jugadores.JugadorVerPage;
import ar.noxit.ehockey.web.pages.report.ReportPage;
import ar.noxit.ehockey.web.pages.tablaposiciones.TablaPosicionesPage;
import ar.noxit.ehockey.web.pages.torneo.ListadoTorneoPage;
import ar.noxit.ehockey.web.pages.torneo.NuevoTorneoPage;
import ar.noxit.ehockey.web.pages.torneo.TorneoPage;
import ar.noxit.ehockey.web.pages.usuarios.AltaUsuarioPage;
import ar.noxit.ehockey.web.pages.usuarios.IUsuarioDTOProvider;
import ar.noxit.ehockey.web.pages.usuarios.ListaUsuariosPage;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.hasher.MD5Hasher;
import com.ttdev.wicketpagetest.MockableBeanInjector;
import java.util.ArrayList;
import org.apache.wicket.Session;
import org.apache.wicket.model.Model;
import org.easymock.EasyMock;
import org.joda.time.LocalDate;
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
    public void testUsuariosPage() {
        startPageAndTestRendered(ListaUsuariosPage.class);
    }

    @Test
    public void testReportPage() throws NoxitException {
        mockJugadoresService();
        mockEquipoService();
        mockClubService();

        startPageAndTestRendered(ReportPage.class);
    }

    @Test
    public void testNewUser() throws NoxitException {
        mockClubService();
        IUsuarioDTOProvider provider = createMock(IUsuarioDTOProvider.class);
        expect(provider.getListaTipos()).andReturn(new ArrayList<String>());
        replay(provider);
        MockableBeanInjector.mockBean("provider", provider);

        startPageAndTestRendered(AltaUsuarioPage.class);
    }

    @Test
    public void testClubAltaPage() throws NoxitException {
        mockClubService();
        startPageAndTestRendered(ClubAltaPage.class);
    }

    @Test
    public void testClubVerPage() throws NoxitException {
        mockClubService();
        startPageAndTestRendered(ClubVerPage.class);
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

    @Test
    public void testBajaJugadorPage() throws NoxitException {
        mockJugadoresService();
        mockClubService();

        startPageAndTestRendered(JugadorBajaPage.class);
    }

    @Test
    public void testRenderVerListaBuenaFe() throws NoxitException {
        mockEquipoService();
        mockClubService();

        startPageAndTestRendered(VerListaBuenaFePage.class);
    }

    @Test
    public void testVerJugadorPage() throws NoxitException {
        mockJugadoresService();
        mockClubService();
        mockDivisionService();
        mockSectorService();

        startPageAndTestRendered(JugadorVerPage.class);
    }

    @Test
    public void testModificarJugadorPage() throws NoxitException {
        mockJugadoresService();
        mockClubService();
        mockDivisionService();
        mockSectorService();

        JugadorPlano object = new JugadorPlano();
        object.setApellido("lla");
        object.setNombre("lla");
        object.setClubId(1);
        object.setDivisionId(1);
        object.setFechaAlta(new LocalDate());
        object.setFechaNacimiento(new LocalDate());
        object.setFicha(1);
        object.setLetraJugador("a");
        object.setNumeroDocumento("3232323");
        object.setSectorId(1);
        object.setTelefono("4444");
        object.setTipoDocumento("dni");
        startPageAndTestRendered(new JugadorModificarPage(Model.of(object)));
    }

    @Test
    public void testTablaPosiciones() throws NoxitException {
        mockTorneoService();
        mockTablaService();

        startPageAndTestRendered(TablaPosicionesPage.class);
    }

    @Test
    public void testFechaHoraPage() throws NoxitException {
        mockHorarioService();
        startPageAndTestRendered(FechaHoraPage.class);
    }

    @Test
    public void testAdminPage() throws NoxitException {
        startPageAndTestRendered(AdministracionPage.class);
    }

    @Test
    public void testClubPage() throws NoxitException {
        startPageAndTestRendered(ClubPage.class);
    }

    @Test
    public void testEquipoPage() throws NoxitException {
        startPageAndTestRendered(EquiposPage.class);
    }

    private void mockJugadoresService() throws NoxitException {
        IJugadorService jugadorService = createMock(IJugadorService.class);
        expect(jugadorService.getAllByClubDivisionSector(null, null, null)).andReturn(new ArrayList<Jugador>())
                .anyTimes();
        MockableBeanInjector.mockBean("jugadorService", jugadorService);
        replay(jugadorService);
    }

    private void mockDivisionService() throws NoxitException {
        IDivisionService divisionService = createMock(IDivisionService.class);
        Division value = new Division("");
        value.setId(1);
        expect(divisionService.get(1)).andReturn(value);
        expect(divisionService.getAll()).andReturn(new ArrayList<Division>()).anyTimes();
        replay(divisionService);
        MockableBeanInjector.mockBean("divisionService", divisionService);
    }

    private void mockSectorService() throws NoxitException {
        ISectorService sectorService = createMock(ISectorService.class);
        Sector value = new Sector("");
        value.setId(1);
        expect(sectorService.get(1)).andReturn(value);
        expect(sectorService.getAll()).andReturn(new ArrayList<Sector>()).anyTimes();
        replay(sectorService);
        MockableBeanInjector.mockBean("sectorService", sectorService);
    }

    private void mockExceptionConverter() {
        IExceptionConverter exceptionConverter = createMock(IExceptionConverter.class);
        replay(exceptionConverter);
        MockableBeanInjector.mockBean("exceptionConverter", exceptionConverter);
    }

    private void mockClubService() throws NoxitException {
        IClubService clubService = createMock(IClubService.class);
        Club value = new Club("");
        value.setId(1);
        expect(clubService.get(1)).andReturn(value);
        expect(clubService.getAll()).andReturn(new ArrayList<Club>()).anyTimes();
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
        ArrayList<Usuario> value = new ArrayList<Usuario>();
        Administrador e = new Administrador("a", "a", new MD5Hasher());
        value.add(e);
        expect(usuariosService.get("a")).andReturn(e).anyTimes();
        expect(usuariosService.getAll()).andReturn(value).anyTimes();
        MockableBeanInjector.mockBean("usuarioService", usuariosService);

        // replay
        replay(usuariosService);
    }

    private void mockHorarioService() throws NoxitException {
        // horario service
        IHorarioService horarioService = createMock(IHorarioService.class);
        expect(horarioService.getHoraSistema()).andReturn(new LocalDateTime()).anyTimes();
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
