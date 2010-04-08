package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import java.util.ArrayList;
import java.util.List;

public class EquipoService implements IEquiposService {

    @Override
    public List<Equipo> getAll() {
        List<Equipo> equipos = new ArrayList<Equipo>();
        Equipo equipo = new Equipo("equipo", new Club("CLUB"));
        equipo.setId(1);
        equipos.add(equipo);
        return equipos;
    }

    @Override
    public Equipo get(Integer id) throws NoxitException {
        Equipo equipo = new Equipo("equipo", new Club("CLUB"));
        equipo.setId(1);
        return equipo;
    }
}
