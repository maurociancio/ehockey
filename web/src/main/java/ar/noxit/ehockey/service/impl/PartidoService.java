package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.dao.IPartidoDao;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IDateTimeProvider;
import ar.noxit.ehockey.service.IPartidoService;
import ar.noxit.ehockey.service.transfer.PartidoInfo;
import ar.noxit.exceptions.NoxitException;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Transactional;

public class PartidoService implements IPartidoService {

    private IPartidoDao partidoDao;
    private IDateTimeProvider dateTimeProvider;

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

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public void reprogramar(PartidoInfo pi) throws NoxitException {
        Validate.notNull(pi);
        Partido partido = partidoDao.get(pi.getId());
        partido.reprogramar(pi.getFecha(), dateTimeProvider.getLocalDateTime());
    }

    public void setPartidoDao(IPartidoDao partidoDao) {
        this.partidoDao = partidoDao;
    }

    public void setDateTimeProvider(IDateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }
}
