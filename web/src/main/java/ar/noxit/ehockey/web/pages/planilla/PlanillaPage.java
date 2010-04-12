package ar.noxit.ehockey.web.pages.planilla;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Planilla;
import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;

public class PlanillaPage extends AbstractHeaderPage {

    public PlanillaPage(IModel<? extends Planilla> planillaModel) {
        super();
        add(new PlanillaPanel(planillaModel));
    }
}