package ar.noxit.ehockey.web.pages;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.tablaposiciones.TablaPosicionesPanel;

public class HomePage extends AbstractContentPage {

    public HomePage() {
        add(new TablaPosicionesPanel());
    }
}
