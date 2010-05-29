package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import org.apache.wicket.model.IModel;

public class JugadorModel extends IdLDM<Jugador, Integer> {

    private IJugadorService jugadorService;

    public JugadorModel(IModel<Integer> idModel, IJugadorService jugadorService) {
        super(idModel);
        this.jugadorService = jugadorService;
    }

    @Override
    protected Jugador doLoad(Integer id) throws NoxitException {
        return jugadorService.get(id);
    }

    @Override
    protected Integer getObjectId(Jugador object) {
        return object.getFicha();
    }
}