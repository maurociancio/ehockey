package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.authentication.AuthSession;
import ar.noxit.ehockey.web.pages.authentication.IRenderable;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

public class PlanillaEstadosForm extends Form<Void> implements IRenderable {

    private final IModel<PlanillaFinal> planillaModel;
    private IModel<Partido> partido;

    public PlanillaEstadosForm(String id, IModel<PlanillaFinal> planillaModel, IModel<Partido> partido) {
        super(id);
        this.planillaModel = planillaModel;
        this.partido = partido;

        add(new PublicarPlanillaButton("publicar", planillaModel, partido));
        add(new EditarPlanillaButton("editar", planillaModel, partido));
        add(new FinalizarPlanillaButton("finalizar", partido, planillaModel));
    }

    @Override
    public boolean isVisible() {
        return planillaModel.getObject().isEditable();
    }

    @Override
    public boolean couldBeRendered(Roles roles) {
        Usuario userLogged = AuthSession.get().getUserLogged();
        Club clubVisitante = partido.getObject().getLocal().getClub();
        return userLogged.puedeVer(clubVisitante);
    }
}