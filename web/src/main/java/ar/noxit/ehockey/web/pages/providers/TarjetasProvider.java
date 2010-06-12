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
    private IModel<Integer> idJugador;

    public TarjetasProvider(IJugadorService jugadorService, IModel<Integer> idJugador) {
        this.jugadorService = jugadorService;
        this.idJugador = idJugador;
    }

    @Override
    protected List<Tarjeta> loadList() {
        try {
            if (idJugador.getObject() == null)
                return new ArrayList<Tarjeta>();
            Jugador jugador = jugadorService.get(idJugador.getObject());
            return jugador.getTarjetas();
        } catch (NoxitException e) {
            return new ArrayList<Tarjeta>();
        }
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
            Jugador jugador = jugadorService.get(idJugador.getObject());
            return jugador.getTarjeta(id);
        }

        @Override
        protected Integer getObjectId(Tarjeta object) {
            return object.getId();
        }
    }

}