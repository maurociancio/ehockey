package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.service.transfer.PartidoInfo;
import ar.noxit.exceptions.NoxitException;
import java.util.List;

public interface ITorneoService {

    void crearTorneo(String nombre, List<PartidoInfo> partidos) throws NoxitException;

    List<Torneo> getAll() throws NoxitException;

    Torneo get(Integer id) throws NoxitException;
}
