package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.exceptions.NoxitException;

public class JugadorAltaPage extends AbstractJugadorPage {

    @SpringBean
    private IJugadorService jugadorService;

    public JugadorAltaPage() {
        super();
        IModel<JugadorPlano> jugador = new Model<JugadorPlano>(
                new JugadorPlano());

        // creamos un formulario
        add(new JugadorForm("formulario", jugador) {

            @Override
            protected void onSubmit(IModel<JugadorPlano> jugador) {
                try {
                    // tratamos de agregar a la persona
                    jugadorService.add(jugador.getObject());
                    setResponsePage(new JugadorAltaOkPage(new Model<String>(
                            "El jugador " + jugador.getObject().getApellido()
                                    + ", " + jugador.getObject().getNombre()
                                    + " ha sido agregado correctamente.")));
                } catch (NoxitException ex) {
                    // se produjo una excepcion
                    // la levantamos pero en runtime
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
