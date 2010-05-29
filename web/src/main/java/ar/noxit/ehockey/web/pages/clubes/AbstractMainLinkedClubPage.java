package ar.noxit.ehockey.web.pages.clubes;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public abstract class AbstractMainLinkedClubPage extends AbstractClubPage {

    public AbstractMainLinkedClubPage() {
        super();
        add(new BookmarkablePageLink<AbstractClubPage>("volver", ClubPage.class));

    }

}
