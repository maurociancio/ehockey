package ar.noxit.ehockey.web.pages.providers;

import java.util.List;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.models.JugadorModel;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.provider.DataProvider;

public abstract class JugadorDataProvider extends DataProvider<Jugador> {

    private IJugadorService jugadorService;

    public JugadorDataProvider(IJugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @Override
    protected List<Jugador> loadList() {
        try {
            // return jugadorService.getAll();
            return this.listoToLoad();
        } catch (NoxitException e) {
            throw new NoxitRuntimeException(e);
        }
    }

    @Override
    public IModel<Jugador> model(Jugador object) {
        return new JugadorModel(object.getFicha(), jugadorService);
    }

    public IJugadorService getService() {
        return jugadorService;
    }

    public abstract List<Jugador> listoToLoad() throws NoxitException;
}