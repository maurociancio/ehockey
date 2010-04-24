package ar.noxit.ehockey.service.impl;

import java.util.ArrayList;
import java.util.List;

import ar.noxit.ehockey.dao.ISectorDao;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.ehockey.web.pages.jugadores.SectorPlano;
import ar.noxit.exceptions.NoxitException;

public class SectorService implements ISectorService {

    private ISectorDao sectorDao;

    @Override
    public Sector get(Integer id) throws NoxitException {
        return sectorDao.get(id);
    }

    @Override
    public List<Sector> getAll() throws NoxitException {
        return sectorDao.getAll();
    }

    private SectorPlano aplanar(Sector sector) {
        SectorPlano sec = new SectorPlano();
        sec.setId(sector.getId());
        sec.setSector(sector.getSector());
        return sec;
    }

    public void setSectorDao(ISectorDao sectorDao) {
        this.sectorDao = sectorDao;
    }
}
