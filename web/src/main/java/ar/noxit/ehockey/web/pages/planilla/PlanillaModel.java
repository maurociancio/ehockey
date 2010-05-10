package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaBase;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class PlanillaModel extends AbstractReadOnlyModel<PlanillaBase> {

    private IModel<Partido> partido;

    public PlanillaModel(IModel<Partido> partido) {
        this.partido = partido;
    }

    @Override
    public PlanillaBase getObject() {
        return partido.getObject().getPlanilla();
    }

    @Override
    public void detach() {
        partido.detach();
    }
}
