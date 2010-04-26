package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.dao.ISectorDao;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.exceptions.NoxitException;
import java.util.List;

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

    public void setSectorDao(ISectorDao sectorDao) {
        this.sectorDao = sectorDao;
    }
}
