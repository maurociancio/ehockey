package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.JugadorSinTarjetasException;
import java.util.Set;

public interface Planilla {

    public DatosEquipoPlanilla getDatosLocal();

    public DatosEquipoPlanilla getDatosVisitante();

    public Partido getPartido();

    public Equipo getLocal();

    public Equipo getVisitante();

    public String getObservaciones();

    public Set<Jugador> getJugadoresL();

    public Set<Jugador> getJugadoresV();

    public TarjetasPartido getTarjetasDe(Jugador jugador) throws JugadorSinTarjetasException;
}
