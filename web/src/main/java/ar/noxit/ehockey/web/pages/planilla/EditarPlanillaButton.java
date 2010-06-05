package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Administrador;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.model.Representante;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.authentication.AuthSession;
import ar.noxit.ehockey.web.pages.authentication.IRenderable;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;

public final class EditarPlanillaButton extends Button implements IRenderable {

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
        return planillaModel.getObject().isEditable() || planillaModel.getObject().isVencida();
    }

    @Override
    public boolean couldBeRendered(Roles roles) {
        Usuario userLogged = AuthSession.get().getUserLogged();
        PlanillaFinal pf = planillaModel.getObject();
        if (pf.isVencida()) {
            return userLogged instanceof Administrador;
        }
        if (pf.isEditable()) {
            Partido p = partido.getObject();
            return userLogged instanceof Representante && userLogged.puedeVer(p.getLocal().getClub());
        }
        return false;
    }
}