package ar.noxit.ehockey.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Transactional;

import ar.noxit.ehockey.dao.IDivisionDao;
import ar.noxit.ehockey.dao.IEquipoDao;
import ar.noxit.ehockey.dao.ISectorDao;
import ar.noxit.ehockey.dao.ITorneoDao;
import ar.noxit.ehockey.model.DatosTabla;
import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.service.ITablaPosicionesService;
import ar.noxit.exceptions.NoxitException;

public class TablaPosicionesService implements ITablaPosicionesService {

    private ITorneoDao torneoDao;
    private IEquipoDao equipoDao;
    private ISectorDao sectorDao;
    private IDivisionDao divisionDao;

    @Override
    @Transactional
    public DatosTabla get(Integer torneoId, Integer equipoId)
            throws NoxitException {
        Validate.notNull(torneoId);
        Validate.notNull(equipoId);
        Torneo torneo = torneoDao.get(torneoId);
        return torneo.crearTablaPosiciones().calcularTabla().getDatosTabla(
                equipoDao.get(equipoId));
    }

    @Override
    @Transactional
    public DatosTabla get(Integer torneoId, Integer equipoId, Integer sectorId,
            Integer divisionId) throws NoxitException {
        Validate.notNull(torneoId);
        Validate.notNull(equipoId);
        Validate.notNull(sectorId);
        Validate.notNull(divisionId);
        Torneo torneo = torneoDao.get(torneoId);
        return torneo.crearTablaPosiciones().filtroTabla(
                sectorDao.get(sectorId)).filtroTabla(
                divisionDao.get(divisionId)).calcularTabla().getDatosTabla(
                equipoDao.get(equipoId));
    }

    @Override
    @Transactional
    public List<DatosTabla> getAllByTorneo(Integer torneoId)
            throws NoxitException {
        List<DatosTabla> lista = new ArrayList<DatosTabla>();
        Torneo torneo = torneoDao.get(torneoId);
        for (DatosTabla each : torneo.crearTablaPosiciones().calcularTabla()
                .getDatosTabla()) {
            lista.add(each);
        }
        return lista;
    }

    @Override
    @Transactional
    public List<DatosTabla> getAllByTorneoSectorDivision(Integer torneoId,
            Integer sectorId, Integer divisionId) throws NoxitException {
        List<DatosTabla> lista = new ArrayList<DatosTabla>();
        Torneo torneo = torneoDao.get(torneoId);
        for (DatosTabla each : torneo.crearTablaPosiciones().filtroTabla(
                divisionDao.get(divisionId)).filtroTabla(
                sectorDao.get(sectorId)).calcularTabla().getDatosTabla()) {
            lista.add(each);
        }
        return lista;
    }

    public void setDivisionDao(IDivisionDao divisionDao) {
        this.divisionDao = divisionDao;
    }

    public void setEquipoDao(IEquipoDao equipoDao) {
        this.equipoDao = equipoDao;
    }

    public void setSectorDao(ISectorDao sectorDao) {
        this.sectorDao = sectorDao;
    }

    public void setTorneoDao(ITorneoDao torneoDao) {
        this.torneoDao = torneoDao;
    }

}
