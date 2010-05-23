package ar.noxit.ehockey.web.pages.torneo;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

@AuthorizeAction(action = "ENABLE", roles = { "USER", "ADMIN" })
public class TorneoPage extends BaseTorneoPage {

    public TorneoPage() {
        add(new BookmarkablePageLink<Void>("crear_torneo",
                NuevoTorneoPage.class));
        add(new BookmarkablePageLink<Void>("ver_torneos",
                ListadoTorneoPage.class));
    }
}
