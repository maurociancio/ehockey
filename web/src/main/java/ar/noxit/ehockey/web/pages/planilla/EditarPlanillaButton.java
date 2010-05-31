package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Administrador;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.web.pages.authentication.AuthSession;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;

public final class EditarPlanillaButton extends Button {

    private final IModel<PlanillaFinal> planillaModel;
    private final IModel<Partido> partido;

    public EditarPlanillaButton(String id, IModel<PlanillaFinal> planillaModel, IModel<Partido> partido) {
        super(id);
        this.planillaModel = planillaModel;
        this.partido = partido;
    }

    @Override
    public void onSubmit() {
        setResponsePage(new ModificarPlanillaPage(partido));
    }

    @Override
    public boolean isVisible() {
        return planillaModel.getObject().isEditable() || (planillaModel.getObject().isVencida()
                && AuthSession.get().getUserLogged() instanceof Administrador);
    }
}