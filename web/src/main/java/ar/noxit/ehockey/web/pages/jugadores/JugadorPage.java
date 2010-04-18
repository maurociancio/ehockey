package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;

public class JugadorPage extends AbstractContentPage {

    public JugadorPage() {
        add(new BookmarkablePageLink<AbstractContentPage>("altajugadores",
                JugadorAltaPage.class));
        add(new BookmarkablePageLink<AbstractContentPage>("verjugadores",
                JugadorVerPage.class));
    }
}
