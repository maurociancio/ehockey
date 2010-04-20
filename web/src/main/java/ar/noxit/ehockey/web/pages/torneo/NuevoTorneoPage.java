package ar.noxit.ehockey.web.pages.torneo;

public class NuevoTorneoPage extends BaseTorneoPage {

    public NuevoTorneoPage() {
        add(new NuevoTorneoWizard("wizard"));
    }
}
