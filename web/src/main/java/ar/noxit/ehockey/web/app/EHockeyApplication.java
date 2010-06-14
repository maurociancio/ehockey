package ar.noxit.ehockey.web.app;

import org.apache.commons.lang.Validate;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.request.IRequestCycleProcessor;
import org.apache.wicket.request.target.coding.HybridUrlCodingStrategy;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import ar.noxit.ehockey.main.StartJetty;
import ar.noxit.ehockey.model.Rol;
import ar.noxit.ehockey.web.pages.AdministracionPage;
import ar.noxit.ehockey.web.pages.HomePage;
import ar.noxit.ehockey.web.pages.authentication.AuthSession;
import ar.noxit.ehockey.web.pages.authentication.EHockeyPageFactory;
import ar.noxit.ehockey.web.pages.authentication.ForbiddenAccessPage;
import ar.noxit.ehockey.web.pages.authentication.LoginPage;
import ar.noxit.ehockey.web.pages.authentication.RolRenderableStrategy;
import ar.noxit.ehockey.web.pages.base.MensajePage;
import ar.noxit.ehockey.web.pages.buenafe.VerListaBuenaFePage;
import ar.noxit.ehockey.web.pages.clubes.ClubAltaPage;
import ar.noxit.ehockey.web.pages.clubes.ClubEditarPage;
import ar.noxit.ehockey.web.pages.clubes.ClubPage;
import ar.noxit.ehockey.web.pages.clubes.ClubVerPage;
import ar.noxit.ehockey.web.pages.equipos.EquipoAltaPage;
import ar.noxit.ehockey.web.pages.equipos.EquipoBajaPage;
import ar.noxit.ehockey.web.pages.equipos.EquipoListadoPage;
import ar.noxit.ehockey.web.pages.equipos.EquipoModificarPage;
import ar.noxit.ehockey.web.pages.equipos.EquiposPage;
import ar.noxit.ehockey.web.pages.fechahora.FechaHoraPage;
import ar.noxit.ehockey.web.pages.jugadores.JugadorAltaOkPage;
import ar.noxit.ehockey.web.pages.jugadores.JugadorAltaPage;
import ar.noxit.ehockey.web.pages.jugadores.JugadorBajaPage;
import ar.noxit.ehockey.web.pages.jugadores.JugadorModificarPage;
import ar.noxit.ehockey.web.pages.jugadores.JugadorPage;
import ar.noxit.ehockey.web.pages.jugadores.JugadorVerPage;
import ar.noxit.ehockey.web.pages.planilla.ModificarPlanillaPage;
import ar.noxit.ehockey.web.pages.planilla.PlanillaPage;
import ar.noxit.ehockey.web.pages.planilla.PlanillaPrecargadaPage;
import ar.noxit.ehockey.web.pages.planilla.PlanillaPrinterFriendly;
import ar.noxit.ehockey.web.pages.report.ReportPage;
import ar.noxit.ehockey.web.pages.report.ReportePrinterFriendly;
import ar.noxit.ehockey.web.pages.tablaposiciones.TablaPosicionesPage;
import ar.noxit.ehockey.web.pages.torneo.ListadoTorneoPage;
import ar.noxit.ehockey.web.pages.torneo.NuevoTorneoPage;
import ar.noxit.ehockey.web.pages.torneo.ReprogramacionPartidoPage;
import ar.noxit.ehockey.web.pages.torneo.TorneoPage;
import ar.noxit.ehockey.web.pages.torneo.VerPartidosPage;
import ar.noxit.ehockey.web.pages.usuarios.AltaUsuarioPage;
import ar.noxit.ehockey.web.pages.usuarios.EditarUsuarioPage;
import ar.noxit.ehockey.web.pages.usuarios.ListaUsuariosPage;
import ar.noxit.ehockey.web.pages.usuarios.PerfilUsuarioPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start
 * class.
 * 
 * @see StartJetty.myproject.Start#main(String[])
 */
public class EHockeyApplication extends AuthenticatedWebApplication {

    private final String appMode;

    /**
     * Constructor
     */
    public EHockeyApplication(String appMode) {
        Validate.notNull(appMode, "application mode cannot be null");
        this.appMode = appMode;
    }

    @Override
    protected void init() {
        super.init();

        addComponentInstantiationListener(new SpringComponentInjector(this));

        mount(new HybridUrlCodingStrategy("/listabuenafe", VerListaBuenaFePage.class, false));
        mount(new HybridUrlCodingStrategy("/planillas/final", PlanillaPage.class, false));
        mount(new HybridUrlCodingStrategy("/planillas/print", PlanillaPrinterFriendly.class, false));
        mount(new HybridUrlCodingStrategy("/planillas/precargada", PlanillaPrecargadaPage.class, false));
        mount(new HybridUrlCodingStrategy("/planillas/modificar", ModificarPlanillaPage.class, false));
        mount(new HybridUrlCodingStrategy("/torneos", TorneoPage.class, false));
        mount(new HybridUrlCodingStrategy("/torneos/crear", NuevoTorneoPage.class, false));
        mount(new HybridUrlCodingStrategy("/torneos/listado", ListadoTorneoPage.class, false));
        mount(new HybridUrlCodingStrategy("/torneos/ver", VerPartidosPage.class, false));
        mount(new HybridUrlCodingStrategy("/partidos/reprogramar", ReprogramacionPartidoPage.class, false));
        mount(new HybridUrlCodingStrategy("/jugadores", JugadorPage.class, false));
        mount(new HybridUrlCodingStrategy("/jugadores/alta", JugadorAltaPage.class, false));
        mount(new HybridUrlCodingStrategy("/jugadores/alta/ver", JugadorAltaOkPage.class, false));
        mount(new HybridUrlCodingStrategy("/jugadores/baja", JugadorBajaPage.class, false));
        mount(new HybridUrlCodingStrategy("/jugadores/modificar", JugadorModificarPage.class, false));
        mount(new HybridUrlCodingStrategy("/jugadores/ver", JugadorVerPage.class, false));
        mount(new HybridUrlCodingStrategy("/tablaposiciones", TablaPosicionesPage.class, false));
        mount(new HybridUrlCodingStrategy("/usuarios/listado", ListaUsuariosPage.class, false));
        mount(new HybridUrlCodingStrategy("/usuarios/alta", AltaUsuarioPage.class, false));
        mount(new HybridUrlCodingStrategy("/usuarios/modificar", EditarUsuarioPage.class, false));
        mount(new HybridUrlCodingStrategy("/equipos", EquiposPage.class, false));
        mount(new HybridUrlCodingStrategy("/equipos/alta", EquipoAltaPage.class, false));
        mount(new HybridUrlCodingStrategy("/equipos/listado", EquipoListadoPage.class, false));
        mount(new HybridUrlCodingStrategy("/equipos/modificar", EquipoModificarPage.class, false));
        mount(new HybridUrlCodingStrategy("/equipos/baja", EquipoBajaPage.class, false));
        mount(new HybridUrlCodingStrategy("/resultado", MensajePage.class, false));
        mount(new HybridUrlCodingStrategy("/fechahora", FechaHoraPage.class, false));
        mount(new HybridUrlCodingStrategy("/login", LoginPage.class, false));
        mount(new HybridUrlCodingStrategy("/denegado", ForbiddenAccessPage.class, false));
        mount(new HybridUrlCodingStrategy("/clubes", ClubPage.class, false));
        mount(new HybridUrlCodingStrategy("/clubes/alta", ClubAltaPage.class, false));
        mount(new HybridUrlCodingStrategy("/clubes/ver", ClubVerPage.class, false));
        mount(new HybridUrlCodingStrategy("/clubes/editar", ClubEditarPage.class, false));
        mount(new HybridUrlCodingStrategy("/perfil", PerfilUsuarioPage.class, false));
        mount(new HybridUrlCodingStrategy("/reporte", ReportPage.class, false));
        mount(new HybridUrlCodingStrategy("/reporte/print", ReportePrinterFriendly.class, false));
        mount(new HybridUrlCodingStrategy("/admin", AdministracionPage.class, false));

        getApplicationSettings().setAccessDeniedPage(ForbiddenAccessPage.class);
        getSessionSettings().setPageFactory(new EHockeyPageFactory());

        RoleAuthorizationStrategy strategy = new RoleAuthorizationStrategy(this);
        strategy.add(new RolRenderableStrategy());
        getSecuritySettings().setAuthorizationStrategy(strategy);

        authorizationStrategy();
    }

    private void authorizationStrategy() {
        MetaDataRoleAuthorizationStrategy.authorize(HomePage.class, Rol.HOME);

        MetaDataRoleAuthorizationStrategy.authorize(VerListaBuenaFePage.class, Rol.VER_BUENA_FE);

        MetaDataRoleAuthorizationStrategy.authorize(TorneoPage.class, Rol.TORNEOS);
        MetaDataRoleAuthorizationStrategy.authorize(NuevoTorneoPage.class, Rol.CREAR_TORNEOS);
        MetaDataRoleAuthorizationStrategy.authorize(ListadoTorneoPage.class, Rol.VER_TORNEOS);
        MetaDataRoleAuthorizationStrategy.authorize(VerPartidosPage.class, Rol.VER_PARTIDOS_TORNEOS);
        MetaDataRoleAuthorizationStrategy.authorize(PlanillaPage.class, Rol.VER_PLANILLAS_FINALES_TORNEOS);
        MetaDataRoleAuthorizationStrategy.authorize(PlanillaPage.class, Rol.VER_PLANILLAS_PRECARGADAS_TORNEOS);
        MetaDataRoleAuthorizationStrategy.authorize(PlanillaPrinterFriendly.class, Rol.VER_PLANILLAS_FINALES_TORNEOS);
        MetaDataRoleAuthorizationStrategy.authorize(PlanillaPrinterFriendly.class,
                Rol.VER_PLANILLAS_PRECARGADAS_TORNEOS);
        MetaDataRoleAuthorizationStrategy.authorize(PlanillaPrecargadaPage.class, Rol.VER_PLANILLAS_FINALES_TORNEOS);
        MetaDataRoleAuthorizationStrategy
                .authorize(PlanillaPrecargadaPage.class, Rol.VER_PLANILLAS_PRECARGADAS_TORNEOS);
        MetaDataRoleAuthorizationStrategy.authorize(PlanillaPrecargadaPage.class, Rol.VER_PLANILLAS_FINALES_TORNEOS);
        MetaDataRoleAuthorizationStrategy
                .authorize(PlanillaPrecargadaPage.class, Rol.VER_PLANILLAS_PRECARGADAS_TORNEOS);

        // MetaDataRoleAuthorizationStrategy.authorize(
        // ReprogramacionPartidoPage.class, false));
        MetaDataRoleAuthorizationStrategy.authorize(JugadorPage.class, Rol.JUGADORES);
        MetaDataRoleAuthorizationStrategy.authorize(JugadorAltaPage.class, Rol.ALTA_JUGADORES);
        MetaDataRoleAuthorizationStrategy.authorize(JugadorBajaPage.class, Rol.BAJA_JUGADORES);
        MetaDataRoleAuthorizationStrategy.authorize(JugadorModificarPage.class, Rol.ACTUALIZACION_JUGADORES);
        MetaDataRoleAuthorizationStrategy.authorize(JugadorVerPage.class, Rol.LISTA_JUGADORES);

        MetaDataRoleAuthorizationStrategy.authorize(TablaPosicionesPage.class, Rol.TABLA_POSICIONES);

        MetaDataRoleAuthorizationStrategy.authorize(AdministracionPage.class, Rol.ADMINISTRACION);

        MetaDataRoleAuthorizationStrategy.authorize(ListaUsuariosPage.class, Rol.USUARIOS);
        MetaDataRoleAuthorizationStrategy.authorize(AltaUsuarioPage.class, Rol.ALTA_USUARIOS);
        MetaDataRoleAuthorizationStrategy.authorize(EditarUsuarioPage.class, Rol.MODIF_USUARIOS);

        MetaDataRoleAuthorizationStrategy.authorize(ClubPage.class, Rol.CLUBES);
        MetaDataRoleAuthorizationStrategy.authorize(ClubAltaPage.class, Rol.ALTA_CLUBES);
        MetaDataRoleAuthorizationStrategy.authorize(ClubVerPage.class, Rol.VER_CLUBES);
        MetaDataRoleAuthorizationStrategy.authorize(ClubEditarPage.class, Rol.MODIF_CLUBES);

        MetaDataRoleAuthorizationStrategy.authorize(EquiposPage.class, Rol.EQUIPOS);
        MetaDataRoleAuthorizationStrategy.authorize(EquipoAltaPage.class, Rol.ALTA_EQUIPOS);
        MetaDataRoleAuthorizationStrategy.authorize(EquipoListadoPage.class, Rol.LISTADO_EQUIPOS);
        MetaDataRoleAuthorizationStrategy.authorize(EquipoModificarPage.class, Rol.MODIF_EQUIPOS);
        MetaDataRoleAuthorizationStrategy.authorize(EquipoBajaPage.class, Rol.BAJA_EQUIPOS);

        MetaDataRoleAuthorizationStrategy.authorize(ReportPage.class, Rol.REPORTES);
        MetaDataRoleAuthorizationStrategy.authorize(ReportePrinterFriendly.class, Rol.REPORTES_IMP);

        MetaDataRoleAuthorizationStrategy.authorize(MensajePage.class, Rol.MENSAJE);

        MetaDataRoleAuthorizationStrategy.authorize(FechaHoraPage.class, Rol.FECHA_HORA);
    }

    @Override
    protected IRequestCycleProcessor newRequestCycleProcessor() {
        return new EHockeyRequestCycleProcessor();
    }

    @Override
    public RequestCycle newRequestCycle(Request request, Response response) {
        return new EHockeyWebRequestCycle(this, (WebRequest) request, (WebResponse) response);
    }

    /**
     * @see wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public String getConfigurationType() {
        return appMode;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return AuthSession.class;
    }
}
