package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class JugadorVerPage extends AbstractJugadorPage {

    public JugadorVerPage() {
        add(new JugadorVerPanel());
        add(new BookmarkablePageLink<AbstractJugadorPage>("menujugadores",
                JugadorPage.class));
    }
}