package ar.noxit.ehockey.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.noxit.ehockey.dao.IDivisionDao;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.ehockey.web.pages.jugadores.DivisionPlano;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.persistence.PersistenceException;

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

    private DivisionPlano aplanar(Division division) {
        DivisionPlano div = new DivisionPlano();
        div.setId(division.getId());
        div.setDivision(division.getNombre());
        return div;
    }

    public void setDivisionDao(IDivisionDao divisionDao) {
        this.divisionDao = divisionDao;
    }

}
