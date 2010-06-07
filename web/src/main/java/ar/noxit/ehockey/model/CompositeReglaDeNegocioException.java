package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.ReglaNegocioException;
import ar.noxit.ehockey.service.IReportable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.Validate;

public class CompositeReglaDeNegocioException extends ReglaNegocioException implements Iterable<ReglaNegocioException> {

    private String descripcionBase = "";
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

    protected String getDescripcionBase() {
        return descripcionBase;
    }

    public void setDescripcionBase(String descripcionBase) {
        this.descripcionBase = descripcionBase;
    }

    @Override
    public String getDescripcion() {
        String message = getDescripcionBase() + "\n";
        for (ReglaNegocioException ex : exceptions) {
            message = message + "\n" + ex.getDescripcion() + ".";
        }
        return message;
    }

    @Override
    public void reportar(IReportable reportable) {
        for (ReglaNegocioException ex : exceptions) {
            ex.reportar(reportable);
        }
    }
}
