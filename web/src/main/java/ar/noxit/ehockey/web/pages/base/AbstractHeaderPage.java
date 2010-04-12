package ar.noxit.ehockey.web.pages.base;

import ar.noxit.ehockey.web.pages.footer.FooterPanel;
import ar.noxit.ehockey.web.pages.header.HeaderPanel;
import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.header.IMenuSelection;

public abstract class AbstractHeaderPage extends AbstractColorBasePage implements IMenuSelection {

    public AbstractHeaderPage() {
        add(new HeaderPanel("header", this));
        add(new FooterPanel("footer"));
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(getClass());
    }
}
