package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.model.Representante;
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

public class PublicarPlanillaButton extends Button implements IRenderable {

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
        PlanillaFinal object = planillaModel.getObject();
        return object.isEditable();
    }

    @Override
    public boolean couldBeRendered(Roles roles) {
        return puedeRenderizar(planillaModel, partido);
    }

    public static boolean puedeRenderizar(IModel<PlanillaFinal> planillaModel, IModel<Partido> partido) {
        Usuario userLogged = AuthSession.get().getUserLogged();
        PlanillaFinal pf = planillaModel.getObject();
        if (pf.isEditable()) {
            Partido p = partido.getObject();
            return userLogged instanceof Representante && userLogged.puedeVer(p.getLocal().getClub());
        }
        return false;
    }
}