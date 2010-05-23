package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitException;

public class SessionClosedException extends NoxitException {

    public SessionClosedException() {
        super();
    }

    public SessionClosedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionClosedException(String message) {
        super(message);
    }

    public SessionClosedException(Throwable cause) {
        super(cause);
    }

}
