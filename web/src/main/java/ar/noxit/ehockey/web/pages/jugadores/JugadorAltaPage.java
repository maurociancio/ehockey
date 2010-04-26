package ar.noxit.ehockey.web.pages.jugadores;

import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.exceptions.NoxitException;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class JugadorAltaPage extends AbstractContentPage {

    @SpringBean
    private IJugadorService jugadorService;

    public JugadorAltaPage() {
        IModel<JugadorPlano> jugador = new Model<JugadorPlano>(
                new JugadorPlano());

        // creamos un formulario
        add(new JugadorForm("formulario", jugador) {
            @Override
            protected void onSubmit(IModel<JugadorPlano> jugador) {
                try {
                    // tratamos de agregar a la persona
                    jugadorService.add(jugador.getObject());
                    info("El jugador " + jugador.getObject().getApellido()
                            + ", " + jugador.getObject().getNombre()
                            + " ha sido agregada correctamente.");
                    jugador.setObject((new JugadorPlano()));
                } catch (NoxitException ex) {
                    // se produjo una excepcion
                    // la levantamos pero en runtime
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
