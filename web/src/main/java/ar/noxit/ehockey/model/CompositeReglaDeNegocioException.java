package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ReglaNegocioException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.Validate;

public class CompositeReglaDeNegocioException extends ReglaNegocioException implements Iterable<ReglaNegocioException> {

    private List<ReglaNegocioException> exceptions = new ArrayList<ReglaNegocioException>();

    public CompositeReglaDeNegocioException() {
        super();
    }

    public CompositeReglaDeNegocioException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompositeReglaDeNegocioException(String message) {
        super(message);
    }

    public CompositeReglaDeNegocioException(Throwable cause) {
        super(cause);
    }

    @Override
    public Iterator<ReglaNegocioException> iterator() {
        return exceptions.iterator();
    }

    public boolean isEmpty() {
        return exceptions.isEmpty();
    }

    public void throwsIfNotEmpty() throws CompositeReglaDeNegocioException {
        if (!isEmpty()) {
            throw this;
        }
    }

    public CompositeReglaDeNegocioException add(ReglaNegocioException reglaNegocioException) {
        Validate.notNull(reglaNegocioException);

        exceptions.add(reglaNegocioException);
        return this;
    }

    @Override
    public String getMessage() {
        String message = new String();
        for (ReglaNegocioException ex : exceptions) {
            message = message + " | " + ex.getMessage();
        }

        return message;
    }
}
