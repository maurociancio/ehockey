package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;
import org.joda.time.LocalDate;

import ar.noxit.ehockey.exception.JugadorInactivoInmutableException;
import ar.noxit.ehockey.exception.JugadorYaActivoException;
import ar.noxit.ehockey.exception.JugadorYaBajaException;
import ar.noxit.ehockey.exception.SinClubException;

/**
 * Jugador
 * 
 * @author Mauro Ciancio
 * 
 */
public class Jugador {

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
    private boolean activo;

    private Division division;
    private Club club;
    private Sector sector;

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
    public Jugador(String apellido, String nombre, Club club, Sector sector,
            Division division) {
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
        this.activo = true;
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
        validarInmutabilidadJugadorInactivo();
        this.ficha = id;
    }

    /**
     * Verifica en runtime si se quiere modificar un jugador que se ha dado de
     * baja
     */
    private void validarInmutabilidadJugadorInactivo() {
        if (!this.activo)
            throw new JugadorInactivoInmutableException();
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
        validarInmutabilidadJugadorInactivo();
        this.documento.setTipo(tipoDocumento);
    }

    public void setNumeroDocumento(String numeroDocumento) {
        validarInmutabilidadJugadorInactivo();
        this.documento.setNumero(numeroDocumento);
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        validarInmutabilidadJugadorInactivo();
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTelefono(String telefono) {
        validarInmutabilidadJugadorInactivo();
        this.telefono = telefono;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        validarInmutabilidadJugadorInactivo();
        this.fechaAlta = fechaAlta;
    }

    public void setLetraJugador(String letraJugador) {
        validarInmutabilidadJugadorInactivo();
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
        validarInmutabilidadJugadorInactivo();
        Validate.notNull(club);
        if (!(club == this.club)) {
            this.liberar();
            club.agregarJugador(this);
        }
    }

    public void setApellido(String apellido) {
        validarInmutabilidadJugadorInactivo();
        this.apellido = apellido;
    }

    public void setDivision(Division division) {
        validarInmutabilidadJugadorInactivo();
        this.division = division;
    }

    public void setNombre(String nombre) {
        validarInmutabilidadJugadorInactivo();
        this.nombre = nombre;
    }

    public void setSector(Sector sector) {
        validarInmutabilidadJugadorInactivo();
        this.sector = sector;
    }

    public void reactivarJugador() throws JugadorYaActivoException {
        if (this.activo)
            throw new JugadorYaActivoException();
        this.activo = true;
    }

    public void bajaJugador() throws JugadorYaBajaException {
        if (!this.activo)
            throw new JugadorYaBajaException();
        this.activo = false;
    }

    public boolean estaActivo() {
        return this.activo;
    }

}
