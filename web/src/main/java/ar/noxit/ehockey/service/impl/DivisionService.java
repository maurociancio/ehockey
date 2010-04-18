package ar.noxit.ehockey.service.impl;

import java.util.List;

import ar.noxit.ehockey.dao.IDivisionDao;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.exceptions.NoxitException;

public class DivisionService implements IDivisionService {

    private IDivisionDao divisionDao;

    @Override
    public List<Division> getAll() throws NoxitException {
        return divisionDao.getAll();
    }

    public void setDivisionDao(IDivisionDao divisionDao) {
        this.divisionDao = divisionDao;
    }

}
