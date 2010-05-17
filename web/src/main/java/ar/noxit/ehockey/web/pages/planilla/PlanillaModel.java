package ar.noxit.ehockey.web.pages.planilla;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaFinal;

public class PlanillaModel extends AbstractReadOnlyModel<PlanillaFinal> {

    private IModel<Partido> partido;

    public PlanillaModel(IModel<Partido> partido) {
        this.partido = partido;
    }

    @Override
    public PlanillaFinal getObject() {
        return partido.getObject().getPlanilla();
    }

    @Override
    public void detach() {
        partido.detach();
    }
}
