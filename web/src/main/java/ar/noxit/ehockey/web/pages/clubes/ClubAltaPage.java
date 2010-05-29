package ar.noxit.ehockey.web.pages.clubes;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class ClubAltaPage extends AbstractClubPage {
    
    public ClubAltaPage() {
        add(new BookmarkablePageLink<AbstractClubPage>("volver", ClubPage.class));
    }

}
