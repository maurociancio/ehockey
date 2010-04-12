package ar.noxit.ehockey.service;

import ar.noxit.exceptions.NoxitException;

public interface IConversionService {

    byte[] convertir(String html) throws NoxitException;
}
