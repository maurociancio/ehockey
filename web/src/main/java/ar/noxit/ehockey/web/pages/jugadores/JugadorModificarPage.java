package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.exception.JugadorExistenteException;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.exceptions.NoxitException;

public class JugadorModificarPage extends AbstractJugadorPage {

    @SpringBean
    private IJugadorService jugadorService;

    public JugadorModificarPage(IModel<JugadorPlano> jugador) {
        super();
        add(new BookmarkablePageLink<AbstractJugadorPage>("listado",
                JugadorVerPage.class));
        add(new JugadorForm("formulario", jugador) {

            @Override
            protected void onSubmit(IModel<JugadorPlano> jugador) {
                try {
                    // tratamos de agregar al jugador
                    jugadorService.update(jugador.getObject());
                    setResponsePage(new JugadorAltaOkPage(new Model<String>(
                            "El jugador " + jugador.getObject().getApellido()
                                    + ", " + jugador.getObject().getNombre()
                                    + " ha sido actualizado correctamente.")));
                } catch (JugadorExistenteException ex) {
                    info("El jugador de "
                            + jugador.getObject().getTipoDocumento()
                            + " "
                            + jugador.getObject().getNumeroDocumento()
                            + " ya existe en el sistema, por lo tanto el jugador no se puede actualizar.");
                } catch (NoxitException ex) {
                    // se produjo una excepcion
                    // la levantamos pero en runtime
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
