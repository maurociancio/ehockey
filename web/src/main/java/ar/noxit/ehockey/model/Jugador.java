package ar.noxit.ehockey.model;

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
     */
    public Jugador(String ficha, String apellido, String nombre) {
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
