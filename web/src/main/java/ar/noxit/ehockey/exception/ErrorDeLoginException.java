package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitException;

public class ErrorDeLoginException extends NoxitException {

    public ErrorDeLoginException() {
    }

    public ErrorDeLoginException(String message) {
        super(message);
    }

    public ErrorDeLoginException(Throwable cause) {
        super(cause);
    }

    public ErrorDeLoginException(String message, Throwable cause) {
        super(message, cause);
    }

}
