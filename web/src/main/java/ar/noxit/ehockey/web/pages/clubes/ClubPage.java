package ar.noxit.ehockey.web.pages.clubes;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;

public class ClubPage extends AbstractClubPage {

    public ClubPage() {
        add(new BookmarkablePageLink<AbstractContentPage>("altaclubes", ClubAltaPage.class));
        add(new BookmarkablePageLink<AbstractContentPage>("verclubes", ClubVerPage.class));
    }

}
