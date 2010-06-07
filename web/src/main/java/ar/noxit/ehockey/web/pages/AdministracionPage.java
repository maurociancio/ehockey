package ar.noxit.ehockey.web.pages;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.clubes.ClubPage;
import ar.noxit.ehockey.web.pages.equipos.EquiposPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.usuarios.ListaUsuariosPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class AdministracionPage extends AbstractContentPage {

    public AdministracionPage() {
        add(new BookmarkablePageLink<Void>("usuarios", ListaUsuariosPage.class));
        add(new BookmarkablePageLink<Void>("clubes", ClubPage.class));
        add(new BookmarkablePageLink<Void>("equipos", EquiposPage.class));
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(AdministracionPage.class);
    }
}
