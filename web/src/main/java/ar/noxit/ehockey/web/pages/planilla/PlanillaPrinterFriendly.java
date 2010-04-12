package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IPartidoService;
import ar.noxit.ehockey.web.pages.base.AbstractColorLessBasePage;
import ar.noxit.ehockey.web.pages.partido.PartidoModel;
import org.apache.wicket.PageParameters;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PlanillaPrinterFriendly extends AbstractColorLessBasePage {

    @SpringBean
    private IPartidoService partidoService;

    public PlanillaPrinterFriendly(PageParameters pageParameters) {
        Integer partidoId = pageParameters.getAsInteger("partido");
        IModel<Partido> partidoModel = new PartidoModel(new Model<Integer>(partidoId), partidoService);
        add(new PlanillaPanel(new PlanillaModel(partidoModel)));
    }
}
