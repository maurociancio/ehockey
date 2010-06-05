package ar.noxit.ehockey.web.pages.equipos;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.web.pages.base.MensajePage;
import ar.noxit.exceptions.NoxitException;
import org.apache.commons.lang.Validate;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EquipoBajaPage extends BaseEquipoPage {

    @SpringBean
    private IEquipoService equipoService;
    @SpringBean
    private IExceptionConverter exceptionConverter;

    public EquipoBajaPage(IModel<Equipo> equipo) {
        Validate.notNull(equipo);

        add(new FeedbackPanel("feedback"));
        add(new BookmarkablePageLink<Void>("no", EquipoListadoPage.class));
        add(new Link<Equipo>("si", equipo) {

            @Override
            public void onClick() {
                try {
                    equipoService.baja(getModelObject().getId());
                    setResponsePage(new MensajePage("Baja de Equipo", "Se ha dado de baja correctamente el equipo."));
                } catch (NoxitException e) {
                    error(exceptionConverter.convert(e));
                }
            }
        });
    }
}
