package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.dao.IDivisionDao;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.exceptions.NoxitException;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class DivisionService implements IDivisionService {

    private IDivisionDao divisionDao;

    @Override
    @Transactional(readOnly = true)
    public Division get(Integer id) throws NoxitException {
        return divisionDao.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Division> getAll() throws NoxitException {
        return divisionDao.getAll();
    }

    public void setDivisionDao(IDivisionDao divisionDao) {
        this.divisionDao = divisionDao;
    }
}
