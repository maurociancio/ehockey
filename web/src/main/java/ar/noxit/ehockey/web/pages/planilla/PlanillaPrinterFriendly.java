package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.web.pages.models.PartidoModel;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.Planilla;
import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.model.PlanillaPrecargada;
import ar.noxit.ehockey.service.IDateTimeProvider;
import ar.noxit.ehockey.service.IPartidoService;
import ar.noxit.ehockey.web.pages.base.AbstractColorLessBasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PlanillaPrinterFriendly extends AbstractColorLessBasePage {

    @SpringBean
    private IPartidoService partidoService;
    @SpringBean
    private IDateTimeProvider dateTimeProvider;

    public PlanillaPrinterFriendly(final PageParameters pageParameters) {
        Integer partidoId = pageParameters.getAsInteger("partido");
        final IModel<Partido> partidoModel = new PartidoModel(new Model<Integer>(partidoId), partidoService);

        add(new PlanillaPanel("panelPlanilla", new AbstractReadOnlyModel<Planilla>() {

            private IModel<PlanillaFinal> planillaFinal = new PlanillaFinalModel(partidoModel, dateTimeProvider);
            private IModel<PlanillaPrecargada> planillaPrecargada = new PlanillaPrecargadaModel(partidoModel,
                    dateTimeProvider);

            @Override
            public Planilla getObject() {
                boolean planillaFinal = pageParameters.getAsBoolean("final", false);
                if (planillaFinal)
                    return this.planillaFinal.getObject();
                else
                    return this.planillaPrecargada.getObject();
            }
        }));
    }
}
