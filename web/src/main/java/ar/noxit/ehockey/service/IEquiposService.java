package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.exceptions.NoxitException;
import java.util.List;

public interface IEquiposService {

    List<Equipo> getAll() throws NoxitException;

    Equipo get(Integer id) throws NoxitException;

    void asignarListaBuenaFe(Integer equipoId, List<Integer> jugadoresIds) throws NoxitException;
}
