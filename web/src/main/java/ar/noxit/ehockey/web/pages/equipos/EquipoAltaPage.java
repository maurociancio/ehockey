package ar.noxit.ehockey.web.pages.equipos;

import ar.noxit.ehockey.service.transfer.EquipoPlano;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class EquipoAltaPage extends BaseEquipoPage {

    public EquipoAltaPage() {
        IModel<EquipoPlano> equipo = new Model<EquipoPlano>(new EquipoPlano());
        add(new EquipoFormPanel("panel", equipo));
    }
}
