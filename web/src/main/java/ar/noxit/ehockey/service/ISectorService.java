package ar.noxit.ehockey.service;

import java.util.List;

import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.web.pages.jugadores.SectorPlano;
import ar.noxit.exceptions.NoxitException;

public interface ISectorService {

    public Sector get(Integer id) throws NoxitException;

    public List<Sector> getAll() throws NoxitException;

}
