package ar.noxit.ehockey.service;

import ar.noxit.exceptions.NoxitException;

public interface IExceptionConverter {

    String convert(NoxitException exception);
}
