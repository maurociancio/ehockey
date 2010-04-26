package ar.noxit.ehockey.web.pages.jugadores;

import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.exceptions.NoxitException;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class JugadorModificarPage extends AbstractContentPage {

    @SpringBean
    private IJugadorService jugadorService;

    public JugadorModificarPage(IModel<JugadorPlano> jugador) {
        add(new JugadorForm("formulario", jugador) {

            @Override
            protected void onSubmit(IModel<JugadorPlano> jugador) {
                try {
                    // tratamos de agregar al jugador
                    jugadorService.update(jugador.getObject());
                    info("El jugador " + jugador.getObject().getApellido()
                            + ", " + jugador.getObject().getNombre()
                            + " ha sido agregada correctamente.");
                } catch (NoxitException ex) {
                    // se produjo una excepcion
                    // la levantamos pero en runtime
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
