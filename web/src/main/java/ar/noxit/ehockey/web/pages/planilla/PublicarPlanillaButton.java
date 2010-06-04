package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.exceptions.NoxitException;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PublicarPlanillaButton extends Button {

    private final IModel<PlanillaFinal> planillaModel;
    private final IModel<Partido> partido;
    @SpringBean
    private IExceptionConverter exceptionConverter;
    @SpringBean
    private IPlanillaService planillaService;

    public PublicarPlanillaButton(String id, IModel<PlanillaFinal> planillaModel, IModel<Partido> partido) {
        super(id);
        this.planillaModel = planillaModel;
        this.partido = partido;
    }

    @Override
    public void onSubmit() {
        try {
            Integer id = partido.getObject().getId();
            planillaService.publicarPlanilla(id);
        } catch (NoxitException e) {
            error(exceptionConverter.convert(e));
        }
    }

    @Override
    public boolean isVisible() {
        return planillaModel.getObject().isEditable();
    }
}