package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitException;

public class ClubYaExistenteException extends NoxitException {

    public ClubYaExistenteException() {
        super();
    }

    public ClubYaExistenteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClubYaExistenteException(String message) {
        super(message);
    }

    public ClubYaExistenteException(Throwable cause) {
        super(cause);
    }

}
