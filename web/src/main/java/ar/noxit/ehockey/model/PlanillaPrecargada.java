package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.JugadorSinTarjetasException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PlanillaPrecargada implements Planilla {

    private int id;

    private DatosEquipoPlanilla datosLocal = new DatosEquipoPlanilla();
    private DatosEquipoPlanilla datosVisitante = new DatosEquipoPlanilla();

    private Partido partido;

    public PlanillaPrecargada(Partido partido) {
        this.partido = partido;

        Iterator<Jugador> it = partido.getLocal().getListaBuenaFe().iterator(partido);
        while (it.hasNext()) {
            datosLocal.getJugadores().add(it.next());
        }

        it = partido.getVisitante().getListaBuenaFe().iterator(partido);
        while (it.hasNext()) {
            datosVisitante.getJugadores().add(it.next());
        }
    }

    protected PlanillaPrecargada() {

    }

    public Set<Jugador> getJugadoresLocales() {
        Set<Jugador> temp = new HashSet<Jugador>();
        temp.addAll(datosLocal.getJugadores());
        return temp;
    }

    public Set<Jugador> getJugadoresVisitantes() {
        Set<Jugador> temp = new HashSet<Jugador>();
        temp.addAll(datosVisitante.getJugadores());
        return temp;
    }

    public Equipo getLocal() {
        return partido.getLocal();
    }

    public Equipo getVisitante() {
        return partido.getVisitante();
    }

    public Partido getPartido() {
        return partido;
    }

    @Override
    public DatosEquipoPlanilla getDatosLocal() {
        return datosLocal;
    }

    @Override
    public DatosEquipoPlanilla getDatosVisitante() {
        return datosVisitante;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getObservaciones() {
        return null;
    }

    @Override
    public Set<Jugador> getJugadoresL() {
        return datosLocal.getJugadores();
    }

    @Override
    public Set<Jugador> getJugadoresV() {
        return datosVisitante.getJugadores();
    }

    @Override
    public TarjetasPartido getTarjetasDe(Jugador jugador) throws JugadorSinTarjetasException {
        return null;
    }
}
