package ar.noxit.ehockey.web.pages.jugadores;

import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;

public class AbstractJugadorPage extends AbstractContentPage {

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(JugadorPage.class);
    }
}
