package ar.noxit.ehockey.web.pages.clubes;

import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;

public abstract class AbstractClubPage extends AbstractHeaderPage {

    public AbstractClubPage() {
        super();
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(ClubPage.class);
    }

}
