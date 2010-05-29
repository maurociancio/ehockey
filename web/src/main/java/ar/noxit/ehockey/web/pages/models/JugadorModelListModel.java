package ar.noxit.ehockey.web.pages.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public abstract class JugadorModelListModel extends
        LoadableDetachableModel<List<IModel<Jugador>>> {

    private IJugadorService jugadorService;

    public JugadorModelListModel(IJugadorService jugadorService) {
        Validate.notNull(jugadorService);

        this.jugadorService = jugadorService;
    }

    @Override
    protected List<IModel<Jugador>> load() {
        List<IModel<Jugador>> lista = new ArrayList<IModel<Jugador>>();
        try {
            // List<Jugador> planos = jugadorServiceService.getAll();
            List<Jugador> planos = this.listToLoad();

            for (Jugador each : planos) {
                IModel<Jugador> model = new JugadorModel(Model.of(each.getFicha()),
                        jugadorService);
                model.setObject(each);
                lista.add(model);
            }
        } catch (NoxitException ex) {
            // #TODO
            throw new NoxitRuntimeException(ex);
        }
        return lista;
    }

    protected IJugadorService getService() {
        return jugadorService;
    }

    protected abstract List<Jugador> listToLoad() throws NoxitException;
}