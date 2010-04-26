package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.Sector;
import ar.noxit.exceptions.NoxitException;
import java.util.List;

public interface ISectorService {

    public Sector get(Integer id) throws NoxitException;

    public List<Sector> getAll() throws NoxitException;
}
