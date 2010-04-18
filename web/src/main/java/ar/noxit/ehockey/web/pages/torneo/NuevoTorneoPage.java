package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;

public class NuevoTorneoPage extends AbstractContentPage {

    public NuevoTorneoPage() {
        add(new NuevoTorneoWizard("wizard"));
    }
}
