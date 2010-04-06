package ar.noxit.ehockey.model;

import org.apache.commons.lang.Validate;

/**
 * Jugador
 * 
 * @author Mauro Ciancio
 * 
 */
public class Jugador {

    /**
     * Id del Jugador
     */
    private Integer id;
    /**
     * Ficha del jugador
     */
    private String ficha;
    /**
     * Apellido del jugador
     */
    private String apellido;
    /**
     * Nombre del jugador
     */
    private String nombre;

    /**
     * Construye un nuevo jugador
     * 
     * @param ficha
     *            del jugador
     * @param apellido
     *            del jugador
     * @param nombre
     *            del jugador
     * @throws IllegalArgumentException
     *             si ficha, apellido o nombre son null
     */
    public Jugador(String ficha, String apellido, String nombre) {
        Validate.notNull(ficha, "ficha no puede ser null");
        Validate.notNull(apellido, "apellido no puede ser null");
        Validate.notNull(nombre, "nombre no puede ser null");

        this.ficha = ficha;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    /**
     * Obtiene el id del jugador
     * 
     * @return id
     */
    public Integer getId() {
        return id;
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
     * Obtiene la ficha del jugador
     * 
     * @return ficha
     */
    public String getFicha() {
        return ficha;
    }

    /**
     * Obtiene el nombre del jugador
     * 
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Constructor default para la persistencia. No debe ser llamado por los
     * clientes
     */
    protected Jugador() {
    }
}
