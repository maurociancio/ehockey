package ar.noxit.ehockey.exception;

public class SinClubException extends ReglaNegocioException {

    public SinClubException() {
    }

    public SinClubException(String message) {
        super(message);
    }

    public SinClubException(Throwable cause) {
        super(cause);
    }

    public SinClubException(String message, Throwable cause) {
        super(message, cause);
    }
}
