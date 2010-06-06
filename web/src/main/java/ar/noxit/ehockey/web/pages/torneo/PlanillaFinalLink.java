package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.authentication.AuthSession;
import ar.noxit.ehockey.web.pages.planilla.PlanillaPage;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.model.IModel;
import org.joda.time.LocalDateTime;

public class PlanillaFinalLink extends PlanillaBaseLink {

    public PlanillaFinalLink(String id, IModel<Partido> rowModel) {
        super(id, rowModel);
    }

    @Override
    protected boolean isVisible(Partido partido, LocalDateTime localDateTime) {
        return partido.puedeVersePlanillaFinal(localDateTime);
    }

    @Override
    protected Page createNewPage(IModel<Partido> model) {
        return new PlanillaPage(model);
    }

    @Override
    public boolean couldBeRendered(Roles roles) {
        Usuario userLogged = AuthSession.get().getUserLogged();

        Partido partido = getModel().getObject();
        Club clubLocal = partido.getLocal().getClub();
        Club clubVisitante = partido.getVisitante().getClub();

        return userLogged.puedeVer(clubLocal) || userLogged.puedeVer(clubVisitante);
    }
}