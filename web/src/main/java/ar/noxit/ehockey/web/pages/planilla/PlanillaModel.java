package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.Planilla;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class PlanillaModel extends AbstractReadOnlyModel<Planilla> {

    private IModel<Partido> partido;

    public PlanillaModel(IModel<Partido> partido) {
        this.partido = partido;
    }

    @Override
    public Planilla getObject() {
        Partido object = partido.getObject();
        return getPlanilla(object);
    }

    protected Planilla getPlanilla(Partido object) {
        return object.getPlanilla();
    }

    @Override
    public void detach() {
        partido.detach();
    }
}
