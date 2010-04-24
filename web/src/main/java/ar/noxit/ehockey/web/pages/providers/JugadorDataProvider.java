package ar.noxit.ehockey.web.pages.providers;

import java.util.List;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.jugadores.JugadorModel;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.provider.DataProvider;

public class JugadorDataProvider extends DataProvider<Jugador> {

    private IJugadorService jugadorService;

    public JugadorDataProvider(IJugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @Override
    protected List<Jugador> loadList() {
        try {
            return jugadorService.getAll();
        } catch (NoxitException e) {
            throw new NoxitRuntimeException(e);
        }
    }

    @Override
    public IModel<Jugador> model(Jugador object) {
        return new JugadorModel(object.getFicha(), jugadorService);
    }
}