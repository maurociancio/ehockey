package ar.noxit.ehockey.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.noxit.ehockey.dao.IPartidoDao;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IPartidoService;
import ar.noxit.exceptions.NoxitException;

public class PartidoService implements IPartidoService {

    private IPartidoDao partidoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Partido> getAll() throws NoxitException {
        return partidoDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Partido get(Integer id) throws NoxitException {
        return partidoDao.get(id);
    }
}
