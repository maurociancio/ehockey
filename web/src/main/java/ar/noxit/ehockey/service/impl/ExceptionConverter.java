package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.exception.Descriptible;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.IReportable;
import ar.noxit.exceptions.NoxitException;

public class ExceptionConverter implements IExceptionConverter {

    @Override
    public String convert(NoxitException exception) {
        if (exception == null)
            return "";

        if (exception instanceof Descriptible) {
            return ((Descriptible) exception).getDescripcion();
        } else {
            return exception.toString() + "[" + exception.getStackTrace().toString() + "]";
        }
    }

    @Override
    public void reportar(IReportable reportable, NoxitException e) {
        if (e instanceof Descriptible) {
            ((Descriptible) e).reportar(reportable);
        } else {
            reportable.reportar(e.toString());
        }
    }
}
