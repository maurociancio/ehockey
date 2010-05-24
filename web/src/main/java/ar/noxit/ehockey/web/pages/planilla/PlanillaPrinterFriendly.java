package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Planilla;
import ar.noxit.ehockey.web.pages.base.AbstractColorLessBasePage;
import org.apache.wicket.model.IModel;

public class PlanillaPrinterFriendly extends AbstractColorLessBasePage {

    public PlanillaPrinterFriendly(IModel<? extends Planilla> planilla) {
        add(new PlanillaPanel("panelPlanilla", planilla));
    }
}
