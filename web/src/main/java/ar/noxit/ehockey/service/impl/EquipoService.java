package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.dao.IEquipoDao;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class EquipoService implements IEquiposService {

    private IEquipoDao equipoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Equipo> getAll() throws NoxitException {
        return equipoDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Equipo get(Integer id) throws NoxitException {
        return equipoDao.get(id);
    }

    public void setEquipoDao(IEquipoDao equipoDao) {
        this.equipoDao = equipoDao;
    }
}
