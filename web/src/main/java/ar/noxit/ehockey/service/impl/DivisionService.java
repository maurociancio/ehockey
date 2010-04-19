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
    public List<Division> getAll() throws NoxitException {
        return divisionDao.getAll();
    }

    @Override
    public List<DivisionPlano> getAllPlano() throws NoxitException {
        List<DivisionPlano> divisiones = new ArrayList<DivisionPlano>();
        for (Division each : divisionDao.getAll()) {
            divisiones.add(aplanar(each));
        }
        return divisiones;
    }

    private DivisionPlano aplanar(Division division) {
        DivisionPlano div = new DivisionPlano();
        div.setDivision(division.getNombre());
        return div;
    }

    public void setDivisionDao(IDivisionDao divisionDao) {
        this.divisionDao = divisionDao;
    }

}
