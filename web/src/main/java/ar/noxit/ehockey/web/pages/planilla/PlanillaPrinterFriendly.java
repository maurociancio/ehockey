package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.web.pages.base.AbstractColorLessBasePage;

public class PlanillaPrinterFriendly extends AbstractColorLessBasePage {

    public PlanillaPrinterFriendly() {
        add(new PlanillaPanel());
    }
}
