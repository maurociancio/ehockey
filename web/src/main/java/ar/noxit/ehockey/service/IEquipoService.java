package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.transfer.EquipoPlano;
import ar.noxit.exceptions.NoxitException;
import java.util.List;

public interface IEquipoService {

    Equipo get(Integer id) throws NoxitException;

    void asignarListaBuenaFe(Integer equipoId, List<Integer> jugadoresIds) throws NoxitException;

    List<Equipo> getEquiposDe(Integer sector, Integer division) throws NoxitException;

    void add(EquipoPlano equipoPlano) throws NoxitException;

    List<Equipo> getAll() throws NoxitException;
}
