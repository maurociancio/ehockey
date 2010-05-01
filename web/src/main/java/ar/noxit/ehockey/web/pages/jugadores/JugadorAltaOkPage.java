package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;

public class JugadorAltaOkPage extends AbstractJugadorPage {

    public JugadorAltaOkPage(IModel<String> mensaje) {
        super();
        add(new Label("mensaje", mensaje));
        add(new JugadorVerPanel());
        add(new BookmarkablePageLink<AbstractJugadorPage>("paginaalta",
                JugadorAltaPage.class));
    }
}
