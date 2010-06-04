package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaFinal;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

public class PlanillaEstadosForm extends Form<Void> {

    private final IModel<PlanillaFinal> planillaModel;

    public PlanillaEstadosForm(String id, IModel<PlanillaFinal> planillaModel, IModel<Partido> partido) {
        super(id);
        this.planillaModel = planillaModel;

        add(new PublicarPlanillaButton("publicar", planillaModel, partido));
        add(new EditarPlanillaButton("editar", planillaModel, partido));
        add(new FinalizarPlanillaButton("finalizar", partido, planillaModel));
    }

    @Override
    public boolean isEnabled() {
        return !planillaModel.getObject().isFinalizada();
    }
}