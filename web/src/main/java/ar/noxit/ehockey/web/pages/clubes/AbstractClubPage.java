package ar.noxit.ehockey.web.pages.clubes;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;

public class AbstractClubPage extends AbstractContentPage {

    public AbstractClubPage() {
        super();
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(ClubPage.class);
    }

}
