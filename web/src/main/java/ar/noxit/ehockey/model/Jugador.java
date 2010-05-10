package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.NoHayPartidoSiguienteException;
import ar.noxit.ehockey.exception.SinClubException;
import ar.noxit.ehockey.exception.SinPartidosException;
import ar.noxit.ehockey.exception.TarjetaYaUsadaException;
import ar.noxit.ehockey.model.Tarjeta.TipoTarjeta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang.Validate;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jugador
 * 
 * @author Mauro Ciancio
 * 
 */
public class Jugador {

    public static final int PUNTOSTARJETASANCION = 15;
    /**
     * Ficha del Jugador
     */
    private Integer ficha;
    /**
     * Apellido del jugador
     */
    private String apellido;
    /**
     * Nombre del jugador
     */
    private String nombre;

    private DocumentoJugador documento;
    private LocalDate fechaNacimiento;
    private String telefono;
    private LocalDate fechaAlta;
    private String letraJugador;

    private Division division;
    private Club club;
    private Sector sector;

    private Set<Tarjeta> tarjetas = new HashSet<Tarjeta>();
    private Set<ISancion> sanciones = new HashSet<ISancion>();

    private static final Logger logger = LoggerFactory.getLogger(TablaPosiciones.class);

    /**
     * Construye un nuevo jugador
     * 
     * @param apellido
     *            del jugador
     * @param nombre
     *            del jugador
     * @throws IllegalArgumentException
     *             si ficha, apellido o nombre son null
     */
    public Jugador(String apellido, String nombre, Club club, Sector sector, Division division) {
        Validate.notNull(apellido, "apellido no puede ser null");
        Validate.notNull(nombre, "nombre no puede ser null");
        Validate.notNull(division, "division no puede ser null");
        Validate.notNull(sector, "sector no puede ser null");
        Validate.notNull(club, "club no puede ser null");

        this.apellido = apellido;
        this.nombre = nombre;
        this.sector = sector;
        this.division = division;
        this.club = club;
        this.documento = new DocumentoJugador();
        this.fechaAlta = new LocalDate();
    }

    /**
     * Obtiene el id del jugador
     * 
     * @return id
     */
    public Integer getFicha() {
        return ficha;
    }

    public Club getClub() throws SinClubException {
        if (club == null) {
            throw new SinClubException("el jugador no tiene club");
        }
        return club;
    }

    public void asignarClub(Club club) {
        Validate.notNull(club);

        if (club != this.club) {
            Club oldClub = this.club;
            this.club = null;
            if (oldClub != null) {
                oldClub.borrarJugador(this);
            }

            this.club = club;
            if (!club.contiene(this)) {
                club.agregarJugador(this);
            }
        }
    }

    public void liberar() {
        Club clubViejo = this.club;
        this.club = null;
        if (clubViejo != null && clubViejo.contiene(this)) {
            clubViejo.borrarJugador(this);
        }
    }

    /**
     * Crea y guarda las tarjetas y crea sanciones si corresponde.
     * 
     * @param partido
     * @param equipo
     * @param tarjetasPartido
     */
    public void amonestar(Partido partido, Equipo equipo, TarjetasPartido tarjetasPartido) {
        Validate.notNull(partido);
        Validate.notNull(equipo);
        Validate.notNull(tarjetasPartido);

        Integer rojas = tarjetasPartido.getRojas();
        Integer amarillas = tarjetasPartido.getAmarillas();
        Integer verdes = tarjetasPartido.getVerdes();

        crearTarjetas(partido, rojas, TipoTarjeta.ROJA);
        crearTarjetas(partido, amarillas, TipoTarjeta.AMARILLA);
        crearTarjetas(partido, verdes, TipoTarjeta.VERDE);

        crearSancionesSiCorresponde(partido, equipo);
    }

    /**
     * Permite cargar una sanción al jugador que lo inhabilite a jugar los
     * partidos que le corresponda según las reglas de la sanción.
     * 
     * @param sancion
     *            sancion aplicada al jugador
     */
    public void sancionar(ISancion sancion) {
        Validate.notNull(sancion);
        sanciones.add(sancion);
    }

    private void crearSancionesSiCorresponde(Partido partido, Equipo equipo) {
        Validate.notNull(partido, "partido no puede ser null");
        Validate.notNull(equipo, "equipo no puede ser null");

        // torneo
        Torneo torneo = partido.getTorneo();

        // partidos en los que el jugador va a estar suspendido
        Collection<Partido> suspendidos = new ArrayList<Partido>();

        try {
            Partido partidoActual = partido;
            while (sumarTarjetas() >= PUNTOSTARJETASANCION) {
                partidoActual = torneo.getProximoPartidoDe(partidoActual, equipo);
                suspendidos.add(partidoActual);
                descontarTarjetas(PUNTOSTARJETASANCION);
            }
        } catch (NoHayPartidoSiguienteException e) {
            logger.debug("Sancion no aplicada, no hay más partidos", e);
        } finally {
            try {
                this.sancionar(new SancionPartidosInhabilitados(suspendidos));
            } catch (SinPartidosException e) {
                logger.debug("Se intento sancionar pero no había partidos", e);
            }
        }
    }

    public boolean puedeJugar(Partido partido) {
        boolean juega = true;
        for (ISancion sancion : this.sanciones) {
            juega &= sancion.puedeJugar(partido);
        }
        return juega;
    }

    private int sumarTarjetas() {
        // TODO
        int suma = 0;
        for (Tarjeta tarjeta : tarjetas) {
            suma += tarjeta.getValor();
        }
        return suma;
    }

    private void descontarTarjetas(int puntos) {
        // TODO
        int suma = 0;
        Iterator<Tarjeta> it = tarjetas.iterator();
        while (it.hasNext() && suma < puntos) {
            Tarjeta tarjeta = it.next();
            int valor = tarjeta.getValor();
            try {
                tarjeta.usar();
                suma += valor;
            } catch (TarjetaYaUsadaException e) {
                logger.debug("seguimos buscando tarjetas", e);
                // seguimos buscando tarjetas
            }
        }
    }

    private void crearTarjetas(Partido partido, Integer rojas, TipoTarjeta tipo) {
        for (int i = 0; i < rojas; ++i) {
            tarjetas.add(new Tarjeta(tipo, partido));
        }
    }

    /**
     * Obtiene el apellido del jugador
     * 
     * @return apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Obtiene el nombre del jugador
     * 
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    public Sector getSector() {
        return sector;
    }

    public Division getDivision() {
        return division;
    }

    /**
     * Este método no debería ser usado por los clientes
     * 
     * @param id
     */
    public void setFicha(Integer id) {
        this.ficha = id;
    }

    /**
     * Constructor default para la persistencia. No debe ser llamado por los
     * clientes.
     */
    protected Jugador() {
    }

    public String getLetraJugador() {
        return letraJugador;
    }

    public String getTipoDocumento() {
        return this.documento.getTipo();
    }

    public String getDocumento() {
        return this.documento.getNumero();
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.documento.setTipo(tipoDocumento);
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.documento.setNumero(numeroDocumento);
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setLetraJugador(String letraJugador) {
        this.letraJugador = letraJugador;
    }

    public LocalDate getFechaAlta() {
        return this.fechaAlta;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setClub(Club club) {
        Validate.notNull(club);
        if (!(club == this.club)) {
            this.liberar();
            club.agregarJugador(this);
        }
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

}
