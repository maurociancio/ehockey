package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.exceptions.NoxitException;

public class JugadorModificarPage extends AbstractContentPage {

    @SpringBean
    private IJugadorService jugadorService;

    public JugadorModificarPage(IModel<JugadorPlano> jugador) {
        add(new JugadorForm("formulario", jugador) {
            @Override
            protected void onSubmit(IModel<JugadorPlano> jugador) {
                try {
                    // tratamos de agregar al jugador
                    jugadorService.convertAdd(jugador.getObject());
                    info("El jugador " + jugador.getObject().getApellido()
                            + ", " + jugador.getObject().getNombre()
                            + " ha sido agregada correctamente.");
                    jugador.setObject(new JugadorPlano());
                } catch (NoxitException ex) {
                    // se produjo una excepcion
                    // la levantamos pero en runtime
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
