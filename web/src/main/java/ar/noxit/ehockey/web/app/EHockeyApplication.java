package ar.noxit.ehockey.web.app;

import ar.noxit.ehockey.main.StartJetty;
import ar.noxit.ehockey.web.pages.HomePage;
import ar.noxit.ehockey.web.pages.buenafe.EditarListaBuenaFePage;
import ar.noxit.ehockey.web.pages.buenafe.VerListaBuenaFePage;
import ar.noxit.ehockey.web.pages.partido.PartidoPage;
import ar.noxit.ehockey.web.pages.torneo.NuevoTorneoPage;
import org.apache.commons.lang.Validate;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see StartJetty.myproject.Start#main(String[])
 */
public class EHockeyApplication extends WebApplication {

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

        mountBookmarkablePage("/listabuenafe/ver", VerListaBuenaFePage.class);
        mountBookmarkablePage("/listabuenafe/editar", EditarListaBuenaFePage.class);
        mountBookmarkablePage("/partidos", PartidoPage.class);
        mountBookmarkablePage("/torneos/crear", NuevoTorneoPage.class);
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
}
