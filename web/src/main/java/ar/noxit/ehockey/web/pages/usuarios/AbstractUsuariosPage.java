package ar.noxit.ehockey.web.pages.usuarios;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;

public class AbstractUsuariosPage extends AbstractContentPage {

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(ListaUsuariosPage.class);
    }
}
