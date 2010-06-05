package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Administrador;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.ehockey.web.pages.authentication.AuthSession;
import ar.noxit.ehockey.web.pages.authentication.IRenderable;
import ar.noxit.exceptions.NoxitException;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public final class FinalizarPlanillaButton extends Button implements IRenderable {

    private final IModel<Partido> partido;
    private final IModel<PlanillaFinal> planillaModel;
    @SpringBean
    private IPlanillaService planillaService;
    @SpringBean
    private IExceptionConverter exceptionConverter;

    public FinalizarPlanillaButton(String id, IModel<Partido> partido, IModel<PlanillaFinal> planillaModel) {
        super(id);
        this.partido = partido;
        this.planillaModel = planillaModel;
    }

    @Override
    public void onSubmit() {
        try {
            Integer id = partido.getObject().getId();
            planillaService.finalizarPlanilla(id);
        } catch (NoxitException e) {
            error(exceptionConverter.convert(e));
        }
    }

    @Override
    public boolean isVisible() {
        return planillaModel.getObject().isVencida();
    }

    @Override
    public boolean couldBeRendered(Roles roles) {
        Usuario userLogged = AuthSession.get().getUserLogged();
        return userLogged instanceof Administrador;
    }
}