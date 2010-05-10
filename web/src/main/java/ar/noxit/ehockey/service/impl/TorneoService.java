package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.dao.IEquipoDao;
import ar.noxit.ehockey.dao.ITorneoDao;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.service.IDateTimeProvider;
import ar.noxit.ehockey.service.ITorneoService;
import ar.noxit.ehockey.service.transfer.PartidoInfo;
import ar.noxit.exceptions.NoxitException;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.joda.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;

public class TorneoService implements ITorneoService {

    private IEquipoDao equipoDao;
    private ITorneoDao torneoDao;
    private IDateTimeProvider dateTimeProvider;

    @Override
    @Transactional(rollbackFor = { RuntimeException.class, NoxitException.class })
    public void crearTorneo(String nombre, List<PartidoInfo> partidos) throws NoxitException {
        Validate.notNull(nombre, "nombre no puede ser null");
        Validate.notNull(partidos, "partidos no puede ser null");
        Validate.noNullElements(partidos, "ningun elemento de la colección puede ser null");

        LocalDateTime now = dateTimeProvider.getLocalDateTime();

        Torneo torneo = new Torneo(nombre);
        for (PartidoInfo info : partidos) {
            Integer localId = info.getEquipoLocalId();
            Integer visitanteId = info.getEquipoVisitanteId();
            Integer numeroFecha = info.getNumeroFecha();
            LocalDateTime fecha = info.getFecha();
            Integer rueda = info.getRueda();
            Integer partido = info.getPartido();

            Equipo local = equipoDao.get(localId);
            Equipo visitante = equipoDao.get(visitanteId);

            local.jugarContra(torneo, visitante, numeroFecha, rueda, partido, fecha, now);
        }

        torneoDao.save(torneo);
    }

    @Override
    @Transactional(readOnly = true)
    public Torneo get(Integer id) throws NoxitException {
        return torneoDao.get(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Torneo> getAll() throws NoxitException {
        return torneoDao.getAll();
    }

    public void setEquipoDao(IEquipoDao equipoDao) {
        this.equipoDao = equipoDao;
    }

    public void setTorneoDao(ITorneoDao torneoDao) {
        this.torneoDao = torneoDao;
    }

    public void setDateTimeProvider(IDateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }
}
