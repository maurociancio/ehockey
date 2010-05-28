package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.authentication.AuthSession;
import ar.noxit.ehockey.web.pages.planilla.PlanillaPrecargadaPage;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.model.IModel;
import org.joda.time.LocalDateTime;

public class PlanillaPrecargadaLink extends PlanillaBaseLink {

    public PlanillaPrecargadaLink(String id, IModel<Partido> rowModel) {
        super(id, rowModel);
    }

    @Override
    protected boolean isEnabled(Partido partido, LocalDateTime localDateTime) {
        return partido.puedeVersePlanillaPrecargada(localDateTime);
    }

    @Override
    protected Page createNewPage(IModel<Partido> model) {
        return new PlanillaPrecargadaPage(model);
    }

    @Override
    public boolean couldBeRendered(Roles roles) {
        Usuario userLogged = AuthSession.get().getUserLogged();

        Partido partido = getModel().getObject();
        Club clubLocal = partido.getLocal().getClub();

        return userLogged.puedeVer(clubLocal);
    }
}