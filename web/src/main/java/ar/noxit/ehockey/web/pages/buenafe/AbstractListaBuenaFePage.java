package ar.noxit.ehockey.web.pages.buenafe;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;

public abstract class AbstractListaBuenaFePage extends AbstractContentPage {

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(ListaBuenaFePage.class);
    }
}
