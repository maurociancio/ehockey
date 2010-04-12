package ar.noxit.ehockey.service;

import java.util.List;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.exceptions.NoxitException;

public interface IPartidoService {

    public List<Partido> getAll() throws NoxitException;

    Partido get(Integer id) throws NoxitException;
}
