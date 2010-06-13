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
    private IModel<Jugador> jugadorModel;

    public SancionProvider(IJugadorService jugadorService, IModel<Jugador> jugadorModel) {
        this.jugadorService = jugadorService;
        this.jugadorModel = jugadorModel;
    }

    @Override
    protected List<ISancion> loadList() {
        if (jugadorModel.getObject() == null)
            return new ArrayList<ISancion>();
        return jugadorModel.getObject().getSanciones();
    }

    @Override
    public IModel<ISancion> model(ISancion object) {
        final Integer id = object.getId();
        return new AbstractReadOnlyModel<ISancion>() {

            @Override
            public ISancion getObject() {
                if (jugadorModel.getObject() == null) {
                    return null;
                }
                return jugadorModel.getObject().getSancion(id);
            }
        };
    }
}
