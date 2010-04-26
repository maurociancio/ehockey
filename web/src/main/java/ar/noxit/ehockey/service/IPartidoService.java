package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.transfer.PartidoInfo;
import ar.noxit.exceptions.NoxitException;
import java.util.List;

public interface IPartidoService {

    public List<Partido> getAll() throws NoxitException;

    Partido get(Integer id) throws NoxitException;

    void reprogramar(PartidoInfo pi) throws NoxitException;
}
