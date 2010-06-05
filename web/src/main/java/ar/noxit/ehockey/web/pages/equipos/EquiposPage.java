package ar.noxit.ehockey.web.pages.equipos;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class EquiposPage extends BaseEquipoPage {

    public EquiposPage() {
        add(new BookmarkablePageLink<Void>("alta", EquipoAltaPage.class));
        add(new BookmarkablePageLink<Void>("listado", EquipoListadoPage.class));
    }
}
