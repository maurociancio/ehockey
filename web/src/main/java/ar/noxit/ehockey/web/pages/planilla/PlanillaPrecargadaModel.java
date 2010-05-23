package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.exception.PlanillaNoDisponibleException;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaPrecargada;
import ar.noxit.ehockey.service.IDateTimeProvider;
import org.apache.wicket.model.IModel;
import org.joda.time.LocalDateTime;

public class PlanillaPrecargadaModel extends PlanillaModel<PlanillaPrecargada> {

    public PlanillaPrecargadaModel(IModel<Partido> partido, IDateTimeProvider dateTimeProvider) {
        super(partido, dateTimeProvider);
    }

    @Override
    protected PlanillaPrecargada getPlanilla(Partido object, LocalDateTime now) throws PlanillaNoDisponibleException {
        return object.getPlanillaPrecargada(now);
    }
}
