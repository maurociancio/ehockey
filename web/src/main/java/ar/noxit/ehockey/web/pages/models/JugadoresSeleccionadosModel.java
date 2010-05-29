package ar.noxit.ehockey.web.pages.models;

import java.util.List;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class JugadoresSeleccionadosModel implements IModel<List<Jugador>> {

    private IModel<Integer> clubId;
    private IClubService clubService;
    private IModel<? extends List<Integer>> seleccionados;

    
    public JugadoresSeleccionadosModel(IModel<Integer> clubId, IClubService clubService,
            IModel<? extends List<Integer>> seleccionados) {
        super();
        this.clubId = clubId;
        this.clubService = clubService;
        this.seleccionados = seleccionados;
    }

    @Override
    public List<Jugador> getObject() {
        try {
            return clubService.getJugadoresPorClub(clubId.getObject(), seleccionados.getObject());
        } catch (NoxitException e) {
            throw new NoxitRuntimeException(e);
        }
    }

    @Override
    public void setObject(List<Jugador> object) {
        List<Integer> lista = seleccionados.getObject();
        lista.clear();
        for (Jugador jugador : object) {
            lista.add(jugador.getFicha());
        }
    }

    @Override
    public void detach() {
    }
}