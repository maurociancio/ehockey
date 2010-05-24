package ar.noxit.ehockey.web.pages.planilla;

import org.apache.wicket.model.IModel;
import org.joda.time.LocalDateTime;

import ar.noxit.ehockey.exception.PlanillaNoDisponibleException;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaPrecargada;
import ar.noxit.ehockey.service.IDateTimeProvider;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class PlanillaPrecargadaModel extends PlanillaModel<PlanillaPrecargada> {
    private IPlanillaService planillaService;

    public PlanillaPrecargadaModel(IModel<Partido> partido, IDateTimeProvider dateTimeProvider, IPlanillaService planillaService) {
        super(partido, dateTimeProvider);
        this.planillaService = planillaService;
    }

    @Override
    protected PlanillaPrecargada getPlanilla(Partido object, LocalDateTime now) throws PlanillaNoDisponibleException {
        try {
            return planillaService.getPlanillaPrecargada(object.getId());
        } catch (PlanillaNoDisponibleException e) {
            throw e;
        } catch (NoxitException e) {
            throw new NoxitRuntimeException(e);
        }
    }
}
