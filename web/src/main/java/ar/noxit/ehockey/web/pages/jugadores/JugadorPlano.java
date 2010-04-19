package ar.noxit.ehockey.web.pages.jugadores;

import java.io.Serializable;

import org.joda.time.LocalDate;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Sector;

public class JugadorPlano implements Serializable {
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

    private String tipoDocumento;
    private String numeroDocumento;
    private LocalDate fechaNacimiento;
    private String telefono;
    private LocalDate fechaAlta; // #TODO
    private String letraJugador;

    private DivisionPlano division;
    private ClubPlano club;
    private SectorPlano sector;

    public JugadorPlano() {
    }

    public Integer getFicha() {
        return ficha;
    }

    public void setFicha(Integer ficha) {
        this.ficha = ficha;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getLetraJugador() {
        return letraJugador;
    }

    public void setLetraJugador(String letraJugador) {
        this.letraJugador = letraJugador;
    }

    public DivisionPlano getDivision() {
        return division;
    }

    public void setDivision(DivisionPlano division) {
        this.division = division;
    }

    public void setDivision(Division division) {
        this.division = new DivisionPlano();
        this.division.setDivision(division.getNombre());
    }

    public ClubPlano getClub() {
        return club;
    }

    public void setClub(ClubPlano club) {
        this.club = club;
    }

    public void setClub(Club club) {
        this.club = new ClubPlano();
        this.club.setNombre(club.getNombre());
    }

    public SectorPlano getSector() {
        return sector;
    }

    public void setSector(SectorPlano sector) {
        this.sector = sector;
    }

    public void setSector(Sector sector) {
        this.sector = new SectorPlano();
        this.sector.setSector(sector.getSector());
    }

}
