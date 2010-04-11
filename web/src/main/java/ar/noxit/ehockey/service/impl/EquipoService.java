package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.dao.IEquipoDao;
import ar.noxit.ehockey.dao.IJugadorDao;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class EquipoService implements IEquiposService {

    private IEquipoDao equipoDao;
    private IJugadorDao jugadorDao;

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

    @Override
    @Transactional
    public void asignarListaBuenaFe(Integer equipoId, List<Integer> jugadoresIds) throws NoxitException {
        Equipo equipo = equipoDao.get(equipoId);

        List<Jugador> jugadores = new ArrayList<Jugador>();
        for (Integer jugadorId : jugadoresIds) {
            jugadores.add(jugadorDao.get(jugadorId));
        }

        equipo.getListaBuenaFe().reemplazarJugadores(jugadores);
    }

    public void setEquipoDao(IEquipoDao equipoDao) {
        this.equipoDao = equipoDao;
    }

    public void setJugadorDao(IJugadorDao jugadorDao) {
        this.jugadorDao = jugadorDao;
    }
}
