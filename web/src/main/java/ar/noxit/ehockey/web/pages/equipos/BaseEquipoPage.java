package ar.noxit.ehockey.web.pages.equipos;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;

public abstract class BaseEquipoPage extends AbstractContentPage {

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(EquiposPage.class);
    }
}
