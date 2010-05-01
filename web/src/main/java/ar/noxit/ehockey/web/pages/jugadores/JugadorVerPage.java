package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class JugadorVerPage extends AbstractJugadorPage {

    public JugadorVerPage() {
        super();
        add(new JugadorVerPanel());
    }
}