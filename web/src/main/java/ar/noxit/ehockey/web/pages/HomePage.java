package ar.noxit.ehockey.web.pages;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.tablaposiciones.TablaPosicionesPanel;

@AuthorizeInstantiation( { "USER", "ADMIN" })
public class HomePage extends AbstractContentPage {

    public HomePage() {
        add(new TablaPosicionesPanel());
    }
}
