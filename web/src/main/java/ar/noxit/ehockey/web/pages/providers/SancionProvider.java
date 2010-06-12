package ar.noxit.ehockey.web.pages.providers;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.ISancion;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.provider.DataProvider;

public class SancionProvider extends DataProvider<ISancion> {

    private IJugadorService jugadorService;
    private IModel<Integer> idJugador;

    public SancionProvider(IJugadorService jugadorService, IModel<Integer> idJugador) {
        this.jugadorService = jugadorService;
        this.idJugador = idJugador;
    }

    @Override
    protected List<ISancion> loadList() {
        try {
            if (idJugador.getObject() == null)
                return new ArrayList<ISancion>();
            Jugador jugador = jugadorService.get(idJugador.getObject());
            return jugador.getSanciones();
        } catch (NoxitException e) {
            return new ArrayList<ISancion>();
        }
    }

    @Override
    public IModel<ISancion> model(ISancion object) {
        final Integer id = object.getId();
        return new AbstractReadOnlyModel<ISancion>() {

            @Override
            public ISancion getObject() {
                if (idJugador.getObject() == null) {
                    return null;
                }
                Jugador jugador;
                try {
                    jugador = jugadorService.get(idJugador.getObject());
                    return jugador.getSancion(id);
                } catch (NoxitException e) {
                    return null;
                }
            }
        };
    }
}
