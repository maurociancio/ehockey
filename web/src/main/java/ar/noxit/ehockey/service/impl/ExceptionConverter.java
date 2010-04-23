package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.exceptions.NoxitException;

public class ExceptionConverter implements IExceptionConverter {

    @Override
    public String convert(NoxitException exception) {
        return exception.toString() + "[" + exception.getStackTrace().toString() + "]";
    }
}
