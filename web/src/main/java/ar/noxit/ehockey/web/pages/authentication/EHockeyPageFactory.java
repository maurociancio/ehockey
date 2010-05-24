package ar.noxit.ehockey.web.pages.authentication;

import ar.noxit.ehockey.model.Rol;
import ar.noxit.ehockey.web.pages.torneo.ListadoTorneoPage;
import ar.noxit.ehockey.web.pages.torneo.TorneoPage;
import org.apache.wicket.IPageFactory;
import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.session.DefaultPageFactory;

public class EHockeyPageFactory implements IPageFactory {

    private DefaultPageFactory pageFactory = new DefaultPageFactory();

    @Override
    public <C extends Page> Page newPage(Class<C> pageClass) {
        checkTorneoPage(pageClass);
        return pageFactory.newPage(pageClass);
    }

    @Override
    public <C extends Page> Page newPage(Class<C> pageClass, PageParameters parameters) {
        checkTorneoPage(pageClass);
        return pageFactory.newPage(pageClass, parameters);
    }

    private <C> void checkTorneoPage(Class<C> pageClass) {
        if (pageClass.equals(TorneoPage.class)) {
            AuthSession session = AuthSession.get();

            if (!session.getRoles().hasAnyRole(new Roles(Rol.CREAR_TORNEOS))) {
                throw new RedirectRestartResponseException(ListadoTorneoPage.class);
            }
        }
    }
}