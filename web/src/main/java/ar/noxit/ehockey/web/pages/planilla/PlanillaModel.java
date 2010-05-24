package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.exception.PlanillaNoDisponibleException;
import ar.noxit.ehockey.exception.PlanillaNoDisponibleRuntimeException;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.Planilla;
import ar.noxit.ehockey.service.IDateTimeProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.joda.time.LocalDateTime;

public abstract class PlanillaModel<T extends Planilla> extends AbstractReadOnlyModel<T> {

    private IModel<Partido> partido;
    private IDateTimeProvider dateTimeProvider;

    public PlanillaModel(IModel<Partido> partido, IDateTimeProvider dateTimeProvider) {
        this.partido = partido;
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public T getObject() {
        Partido object = partido.getObject();
        return getPlanilla(object);
    }

    public IModel<Partido> getPartido() {
        return partido;
    }

    protected T getPlanilla(Partido object) {
        LocalDateTime now = dateTimeProvider.getLocalDateTime();
        try {
            return getPlanilla(object, now);
        } catch (PlanillaNoDisponibleException e) {
            throw new PlanillaNoDisponibleRuntimeException(e);
        }
    }

    protected abstract T getPlanilla(Partido object, LocalDateTime now) throws PlanillaNoDisponibleException;

    @Override
    public void detach() {
        partido.detach();
    }
}