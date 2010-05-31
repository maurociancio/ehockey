package ar.noxit.ehockey.web.pages.equipos;

import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.transfer.EquipoPlano;
import ar.noxit.exceptions.NoxitException;
import org.apache.commons.lang.Validate;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EquipoModificarPage extends BaseEquipoPage {

    @SpringBean
    private IEquipoService equipoService;
    @SpringBean
    private IExceptionConverter exceptionConverter;

    public EquipoModificarPage(IModel<EquipoPlano> equipo) {
        Validate.notNull(equipo);

        add(new EquipoFormPanel("panel", equipo) {

            @Override
            protected void onSubmit(EquipoPlano modelObject) {
                try {
                    equipoService.update(modelObject);
                } catch (NoxitException e) {
                    error(exceptionConverter.convert(e));
                }
            }
        }.setClubActivo(false));
    }
}
