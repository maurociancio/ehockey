package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;

public class AbstractJugadorPage extends AbstractContentPage {

    public AbstractJugadorPage() {
        super();
        add(new BookmarkablePageLink<AbstractJugadorPage>("menujugador",
                JugadorPage.class));
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(JugadorPage.class);
    }
}
