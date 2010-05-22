package ar.noxit.ehockey.web.pages.fechahora;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;

public class FechaHoraPage extends AbstractContentPage {

    public FechaHoraPage() {
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(FechaHoraPage.class);
    }
}
