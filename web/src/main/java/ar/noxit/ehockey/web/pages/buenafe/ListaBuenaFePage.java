package ar.noxit.ehockey.web.pages.buenafe;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class ListaBuenaFePage extends AbstractListaBuenaFePage {

    public ListaBuenaFePage() {
        add(new BookmarkablePageLink<Void>("ver", VerListaBuenaFePage.class));
        add(new BookmarkablePageLink<Void>("editar", EditarListaBuenaFePage.class));
    }
}
