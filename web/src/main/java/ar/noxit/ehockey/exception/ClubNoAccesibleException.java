package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitException;

/**
 * Ocurre cuando hay una violación de acceso según los permisos.
 * @author tito
 *
 */
public class ClubNoAccesibleException extends NoxitException {

    public ClubNoAccesibleException() {
        super();
    }

    public ClubNoAccesibleException(String message) {
        super(message);
    }

    public ClubNoAccesibleException(Throwable cause) {
        super(cause);
    }

    public ClubNoAccesibleException(String message, Throwable cause) {
        super(message, cause);
    }

}
