package ar.noxit.ehockey.exception;

import ar.noxit.exceptions.NoxitException;

public class UsuarioExistenteException extends NoxitException {

    public UsuarioExistenteException() {
    }

    public UsuarioExistenteException(String message) {
        super(message);
    }

    public UsuarioExistenteException(Throwable cause) {
        super(cause);
    }

    public UsuarioExistenteException(String message, Throwable cause) {
        super(message, cause);
    }

}
