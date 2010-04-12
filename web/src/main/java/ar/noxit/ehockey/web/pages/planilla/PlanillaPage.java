package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;

public class PlanillaPage extends AbstractHeaderPage {

    public PlanillaPage() {
        super();
        add(new PlanillaPanel());
    }
}