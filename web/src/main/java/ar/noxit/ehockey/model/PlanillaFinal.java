package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.JugadorSinTarjetasException;
import ar.noxit.ehockey.exception.JugadorYaPerteneceAListaException;
import ar.noxit.ehockey.exception.PlanillaNoModificableException;
import ar.noxit.ehockey.exception.ReglaNegocioException;
import ar.noxit.ehockey.exception.ViolacionReglaNegocioException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.Validate;

public class PlanillaFinal implements PlanillaPublicable, Comentable, Planilla {

    private int id;

    private DatosEquipoPlanilla datosLocal = new DatosEquipoPlanilla();
    private DatosEquipoPlanilla datosVisitante = new DatosEquipoPlanilla();

    private String observaciones;

    // TODO definir el atributo fecha
    // TODO definir el resto de los atributos que son: Torneo, Rueda, Partido,
    // Sector(damas/caballeros), categoría, división, zona
    private Partido partido;
    private String comentario;
    private EstadoPlanilla estado;

    protected PlanillaFinal() {
    }

    public PlanillaFinal(PlanillaPrecargada original) {
        Validate.notNull(original);

        this.partido = original.getPartido();
        this.estado = new EstadoPlanillaCargada();

        // que hace temp? TODO
        Set<Jugador> temp = this.datosLocal.getJugadores();
        temp.clear();
        temp.addAll(original.getJugadoresLocales());

        temp = this.datosVisitante.getJugadores();
        temp.addAll(original.getJugadoresVisitantes());
    }

    private void agregarJugador(Jugador jugador, Set<Jugador> jugadores) throws JugadorYaPerteneceAListaException {
        Validate.notNull(jugador, "jugador no puede ser null");

        if (jugadores.contains(jugador)) {
            throw new JugadorYaPerteneceAListaException("el jugador ya está en la lista de buena fe");
        }
        jugadores.add(jugador);
    }

    private void agregarJugadores(Collection<Jugador> jugadoresNuevos, Set<Jugador> jugadores) {
        jugadores.clear();
        for (Jugador j : jugadoresNuevos) {
            jugadores.add(j);
        }
    }

    // VALIDADORES
    private void validatePlanillaCerrada() throws PlanillaNoModificableException {
        if (this.estado.estaFinalizada())
            throw new PlanillaNoModificableException();
    }

    private Equipo getEquipoDeJugador(Jugador jugador) {
        Validate.notNull(jugador);

        boolean jugoLocal = datosLocal.jugo(jugador);
        boolean jugoVisitante = datosVisitante.jugo(jugador);

        if (jugoLocal && jugoVisitante) {
            throw new ViolacionReglaNegocioException("el jugador jugo en los dos equipos");
        }

        if (jugoLocal) {
            return getLocal();
        }
        if (jugoVisitante) {
            return getVisitante();
        }

        throw new ViolacionReglaNegocioException("el jugador no jugo en ningun lado");
    }

    private void amonestar(Map<Jugador, TarjetasPartido> tarjetasLocal) {
        for (Map.Entry<Jugador, TarjetasPartido> entry : tarjetasLocal.entrySet()) {
            Jugador jugador = entry.getKey();
            TarjetasPartido tarjetasPartido = entry.getValue();
            Equipo equipoJugador = getEquipoDeJugador(jugador);

            jugador.amonestar(partido, equipoJugador, tarjetasPartido);
        }
    }

    @Override
    public void checkPublicable() throws ReglaNegocioException {
        CompositeReglaDeNegocioException composite = new CompositeReglaDeNegocioException();

        DatosEquipoPlanilla datos[] = { datosLocal, datosVisitante };
        for (DatosEquipoPlanilla dep : datos) {
            try {
                dep.checkCompleta();
            } catch (ReglaNegocioException e) {
                composite.add(e);
            }
        }

        composite.throwsIfNotEmpty();
    }

    @Override
    public void comentar(String comentario) {
        Validate.notNull(comentario);

        this.comentario = comentario;
    }

    private class PlanillaFinalFinalizable implements PlanillaFinalizable {

        @Override
        public void finalizarPlanilla() throws ReglaNegocioException {
            amonestar(datosLocal.getTarjetas());
            amonestar(datosVisitante.getTarjetas());
        }
    }

    public void publicar() throws ReglaNegocioException {
        estado = estado.publicar(this);
    }

    public void validar() throws ReglaNegocioException {
        estado = estado.validar(new PlanillaFinalFinalizable());
    }

    public void rechazar(String comentario) throws ReglaNegocioException {
        estado = estado.rechazar(this, comentario);
    }

    public TarjetasPartido getTarjetasDe(Jugador object) throws JugadorSinTarjetasException {
        try {
            return datosLocal.buscarJugador(object);
        } catch (JugadorSinTarjetasException e) {
            return datosVisitante.buscarJugador(object);
            // TODO validar que no juegue en los dos equipos
        }
    }

    public void agregarJugadorLocal(Jugador jugador) throws PlanillaNoModificableException,
            JugadorYaPerteneceAListaException {

        validatePlanillaCerrada();
        agregarJugador(jugador, this.datosLocal.getJugadores());
    }

    public void agregarJugadorVisitante(Jugador jugador) throws PlanillaNoModificableException,
            JugadorYaPerteneceAListaException {

        validatePlanillaCerrada();
        agregarJugador(jugador, this.datosVisitante.getJugadores());
    }

    public void setJugadoresLocal(Collection<Jugador> jugadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        agregarJugadores(jugadores, this.datosLocal.getJugadores());
    }

    public void setJugadoresVisitante(Collection<Jugador> jugadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        agregarJugadores(jugadores, this.datosVisitante.getJugadores());
    }

    public void setGolesLocal(Integer goles) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosLocal.setGoles(goles);
    }

    public void setGolesVisitante(Integer goles) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosVisitante.setGoles(goles);
    }

    public void setArbitroL(String arbitro) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosLocal.setArbitro(arbitro);
    }

    public void setDtL(String dt) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosLocal.setdT(dt);
    }

    public void setGoleadoresL(String goleadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosLocal.setGoleadores(goleadores);
    }

    public void setJuezMesaL(String juezMesa) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosLocal.setJuezDeMesa(juezMesa);
    }

    public void setMedicoL(String medico) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosLocal.setMedico(medico);
    }

    public void setCapitanL(String capitan) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosLocal.setCapitan(capitan);
    }

    public void setCapitanV(String capitan) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosVisitante.setCapitan(capitan);
    }

    public void setPfL(String pf) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosLocal.setpFisico(pf);
    };

    public void setArbitroV(String arbitro) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosVisitante.setArbitro(arbitro);
    }

    public void setDtV(String dt) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosVisitante.setdT(dt);
    }

    public void setGoleadoresV(String goleadores) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosVisitante.setGoleadores(goleadores);
    }

    public void setJuezMesaV(String juezMesa) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosVisitante.setJuezDeMesa(juezMesa);
    }

    public void setMedicoV(String medico) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosVisitante.setMedico(medico);
    }

    public void setPfV(String pf) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.datosVisitante.setpFisico(pf);
    }

    public void setObservaciones(String observaciones) throws PlanillaNoModificableException {
        validatePlanillaCerrada();

        this.observaciones = observaciones;
    }

    public String getComentario() {
        return comentario;
    }

    public Equipo getLocal() {
        return partido.getLocal();
    }

    public Equipo getVisitante() {
        return partido.getVisitante();
    }

    public Set<Jugador> getJugadoresL() {
        return this.datosLocal.getJugadores();
    }

    public Set<Jugador> getJugadoresV() {
        return this.datosVisitante.getJugadores();
    }

    public DatosEquipoPlanilla getDatosLocal() {
        return this.datosLocal;
    }

    public DatosEquipoPlanilla getDatosVisitante() {
        return this.datosVisitante;
    }

    public int getId() {
        return id;
    }

    public boolean isFinalizada() {
        return estado.estaFinalizada();
    }

    public Integer getGolesLocal() {
        return this.datosLocal.getGoles();
    }

    public Integer getGolesVisitante() {
        return this.datosVisitante.getGoles();
    }

    public String getObservaciones() {
        return observaciones;
    }

    @Override
    public Partido getPartido() {
        return this.partido;
    }

    public String getEstado() {
        return this.estado.toString();
    }
}
