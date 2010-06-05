package ar.noxit.ehockey.web.pages.equipos;

import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.transfer.EquipoPlano;
import ar.noxit.ehockey.web.pages.base.MensajePage;
import ar.noxit.exceptions.NoxitException;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EquipoAltaPage extends BaseEquipoPage {

    @SpringBean
    private IEquipoService equipoService;
    @SpringBean
    private IExceptionConverter exceptionConverter;

    public EquipoAltaPage() {
        IModel<EquipoPlano> equipo = new Model<EquipoPlano>(new EquipoPlano());
        add(new EquipoFormPanel("panel", equipo) {

            @Override
            protected void onSubmit(EquipoPlano equipoPlano) {
                try {
                    equipoService.add(equipoPlano);
                    setResponsePage(new MensajePage("Alta de Equipo", "Se ha dado de alta correctamente el equipo."));
                } catch (NoxitException e) {
                    error(exceptionConverter.convert(e));
                }
            }
        });
    }
}
