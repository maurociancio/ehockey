package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.dao.IPartidoDao;
import ar.noxit.ehockey.exception.PlanillaNoDisponibleException;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IDateTimeProvider;
import ar.noxit.ehockey.service.IPartidoService;
import ar.noxit.ehockey.service.transfer.PartidoInfo;
import ar.noxit.exceptions.NoxitException;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.joda.time.LocalDateTime;
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

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public void terminarPartido(Integer id) throws NoxitException {
        Partido partido = partidoDao.get(id);
        partido.terminarPartido(dateTimeProvider.getLocalDateTime());
    }

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public void actualizarEstados() throws NoxitException {
        LocalDateTime now = dateTimeProvider.getLocalDateTime();

        for (Partido partido : partidoDao.getAll()) {
            try {
                partido.getPlanilla(now);
            } catch (PlanillaNoDisponibleException ignore) {
                // ignore
            }
        }
    }

    public void setPartidoDao(IPartidoDao partidoDao) {
        this.partidoDao = partidoDao;
    }

    public void setDateTimeProvider(IDateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

}
