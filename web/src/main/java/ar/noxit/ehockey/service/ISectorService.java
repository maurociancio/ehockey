package ar.noxit.ehockey.service;

import java.util.List;

import ar.noxit.ehockey.model.Sector;
import ar.noxit.exceptions.NoxitException;

public interface ISectorService {

    public List<Sector> getAll() throws NoxitException;

}
