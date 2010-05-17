package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.DatosTabla;
import ar.noxit.exceptions.NoxitException;
import java.util.List;

public interface ITablaPosicionesService {

    public DatosTabla get(Integer torneoId, Integer equipoId)
            throws NoxitException;

    public DatosTabla get(Integer torneoId, Integer equipoId, Integer sectorId,
            Integer divisionId) throws NoxitException;

    public List<DatosTabla> getAllByTorneo(Integer torneoId)
            throws NoxitException;
}
