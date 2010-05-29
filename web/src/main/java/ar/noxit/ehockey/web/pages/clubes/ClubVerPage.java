package ar.noxit.ehockey.web.pages.clubes;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class ClubVerPage extends AbstractClubPage {

    public ClubVerPage() {
        add(new BookmarkablePageLink<AbstractClubPage>("volver", ClubPage.class));
    }

}
