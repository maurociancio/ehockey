package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class EquipoService implements IEquiposService {

    @Override
    public List<Equipo> getAll() {
        List<Equipo> equipos = new ArrayList<Equipo>();
        Equipo equipo = createEquipo();
        equipos.add(equipo);
        return equipos;
    }

    @Override
    public Equipo get(Integer id) throws NoxitException {
        Equipo equipo = createEquipo();
        return equipo;
    }

    private Equipo createEquipo() {
        try {
            Equipo equipo = new Equipo("equipo", new Club("CLUB"));
            Jugador jugador = new Jugador("ficha", "riquelme", "roman");
            jugador.setId(1);
            equipo.getListaBuenaFe().agregarJugador(jugador);
            equipo.setId(1);
            return equipo;
        } catch (JugadorYaPerteneceAListaException e) {
            throw new NoxitRuntimeException(e);
        }
    }
}
