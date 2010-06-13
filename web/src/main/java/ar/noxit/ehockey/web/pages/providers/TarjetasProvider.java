package ar.noxit.ehockey.web.pages.providers;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Tarjeta;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import ar.noxit.web.wicket.provider.DataProvider;

public class TarjetasProvider extends DataProvider<Tarjeta> {

    private IJugadorService jugadorService;
    private IModel<Jugador> jugadorModel;

    public TarjetasProvider(IJugadorService jugadorService, IModel<Jugador> jugadorModel) {
        this.jugadorService = jugadorService;
        this.jugadorModel = jugadorModel;
    }

    @Override
    protected List<Tarjeta> loadList() {
        if (jugadorModel.getObject() == null)
            return new ArrayList<Tarjeta>();
        return jugadorModel.getObject().getTarjetas();
    }

    @Override
    public IModel<Tarjeta> model(Tarjeta object) {
        return new TarjetaModel(Model.of(object.getId()));
    }

    private class TarjetaModel extends IdLDM<Tarjeta, Integer> {

        public TarjetaModel(IModel<Integer> model) {
            super(model);
        }

        @Override
        protected Tarjeta doLoad(Integer id) throws NoxitException {
            return jugadorModel.getObject().getTarjeta(id);
        }

        @Override
        protected Integer getObjectId(Tarjeta object) {
            return object.getId();
        }
    }

}