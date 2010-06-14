package ar.noxit.ehockey.web;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import ar.noxit.ehockey.exception.SessionClosedException;

import org.apache.wicket.Session;

import org.easymock.EasyMock;

import ar.noxit.ehockey.configuration.MenuItem;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.service.IHorarioService;
import ar.noxit.ehockey.service.ITablaPosicionesService;
import ar.noxit.ehockey.service.ITorneoService;
import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.ehockey.web.pages.HomePage;
import ar.noxit.ehockey.web.pages.authentication.LoginPage;
import ar.noxit.ehockey.web.pages.buenafe.VerListaBuenaFePage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.header.IMenuItemProvider;
import ar.noxit.exceptions.NoxitException;
import com.ttdev.wicketpagetest.MockableBeanInjector;
import java.util.ArrayList;
import java.util.List;
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
    public void testRenderVerListaBuenaFe() throws NoxitException {
        IEquipoService equipoService = createMock(IEquipoService.class);
        IClubService clubService = createMock(IClubService.class);
        List<Club> value = new ArrayList<Club>();

        expect(clubService.getAll()).andReturn(value).times(3);
        replay(equipoService);
        replay(clubService);

        MockableBeanInjector.mockBean("equipoService", equipoService);
        MockableBeanInjector.mockBean("clubService", clubService);

        startPageAndTestRendered(VerListaBuenaFePage.class);
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
