package ar.noxit.ehockey.web.pages.jugadores;

import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.providers.JugadorDataProvider;
import ar.noxit.exceptions.NoxitException;

public class JugadorVerPage extends AbstractJugadorPage {

    @SpringBean
    private IJugadorService jugadorService;

    public JugadorVerPage() {
        super();
        add(new JugadorVerPanel(jugadorService));
    }
}