package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.exceptions.NoxitException;

public class JugadorAltaPage extends AbstractContentPage {

    @SpringBean
    private IJugadorService jugadorService;

    public JugadorAltaPage() {
        IModel<Jugador> jugador = new Model<Jugador>(new Jugador());

        // creamos un formulario
        add(new JugadorForm("formulario", jugador) {
            @Override
            protected void onSubmit(IModel<Jugador> jugador) {
                try {
                    // tratamos de agregar a la persona
                    jugadorService.add(jugador.getObject());
                    info("El jugador " + jugador.getObject().getApellido()
                            + ", " + jugador.getObject().getNombre()
                            + " ha sido agregada correctamente.");
                    jugador.setObject(new Jugador());
                } catch (NoxitException ex) {
                    // se produjo una excepcion
                    // la levantamos pero en runtime
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
