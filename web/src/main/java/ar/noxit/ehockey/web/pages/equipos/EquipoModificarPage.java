package ar.noxit.ehockey.web.pages.equipos;

import ar.noxit.ehockey.service.transfer.EquipoPlano;
import org.apache.commons.lang.Validate;
import org.apache.wicket.model.IModel;

public class EquipoModificarPage extends BaseEquipoPage {

    public EquipoModificarPage(IModel<EquipoPlano> equipo) {
        Validate.notNull(equipo);

        add(new EquipoFormPanel("panel", equipo) {

            @Override
            protected void onSubmit(EquipoPlano modelObject) {
            }
        }.setClubActivo(false));
    }
}
