package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitRuntimeException;

import java.util.ArrayList;
import java.util.List;

public class ClubService implements IClubService {

    @Override
    public List<Jugador> getJugadoresPorClub(Integer clubId) {
        List<Jugador> jugadoresOriginal = getClub(clubId).getJugadores();
        List<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.addAll(jugadoresOriginal);
        
        return jugadores;
    }

    @Override
    public Club getClub(Integer id) {
        return getClub();
    }

    @Override
    public List<Club> getAll() {
        ArrayList<Club> list = new ArrayList<Club>();
        list.add(getClub());
        return list;
    }

    private Club getClub() {
        Club club = new Club("club1");
        club.setId(1);
        club.agregarJugador(getJ());
        return club;
    }

    @Override
    public List<Jugador> getJugadoresPorClub(Integer clubId, List<Integer> idJugadores) {
        ArrayList<Jugador> arrayList = new ArrayList<Jugador>();
        if (idJugadores != null && !idJugadores.isEmpty()) {
            Jugador e = getJ();
            arrayList.add(e);
        }
        return arrayList;
    }

    private Jugador getJ() {
        Jugador e = new Jugador("a", "n", new Sector("s"), new Division("s"));
        e.setFicha(1);
        return e;
    }

    @Override
    public List<Equipo> getEquiposPorClub(Integer clubId) {
        List<Equipo> equipos = new ArrayList<Equipo>();
        equipos.addAll(getClub(clubId).getEquipos());
        
        //MOCKEANDO AGREGO UN EQUIPO FABRICADO
        equipos.add(createEquipo());
        /////////////////////////////////////
        
        return equipos;
    }
    
    
    private Equipo createEquipo() {
        try {
            Club club = new Club("CLUB");
            Sector sector = new Sector("s");
            Division d = new Division("d");
            Equipo equipo = club.crearNuevoEquipo("equipo", d, sector);
            Jugador jugador = new Jugador("riquelme", "roman", sector, d);
            jugador.setFicha(1);
            equipo.getListaBuenaFe().agregarJugador(jugador);
            equipo.setId(1);
            return equipo;
        } catch (JugadorYaPerteneceAListaException e) {
            throw new NoxitRuntimeException(e);
        }
    }
}
