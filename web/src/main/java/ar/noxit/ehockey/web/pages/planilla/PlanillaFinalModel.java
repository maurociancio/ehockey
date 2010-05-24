package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.exception.PlanillaNoDisponibleException;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.service.IDateTimeProvider;
import org.apache.wicket.model.IModel;
import org.joda.time.LocalDateTime;

public class PlanillaFinalModel extends PlanillaModel<PlanillaFinal> {

    public PlanillaFinalModel(IModel<Partido> partido, IDateTimeProvider dateTimeProvider) {
        super(partido, dateTimeProvider);
    }

    @Override
    public PlanillaFinal getObject() {
        return super.getObject();
    }

    protected PlanillaFinal getPlanilla(Partido object, LocalDateTime now) throws PlanillaNoDisponibleException {
        return object.getPlanilla(now);
    }
}
