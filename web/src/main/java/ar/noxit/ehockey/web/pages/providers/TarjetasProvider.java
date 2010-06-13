package ar.noxit.ehockey.web.pages.providers;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Tarjeta;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import ar.noxit.web.wicket.provider.DataProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class TarjetasProvider extends DataProvider<Tarjeta> {

    private IModel<Jugador> jugadorModel;

    public TarjetasProvider(IModel<Jugador> jugadorModel) {
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