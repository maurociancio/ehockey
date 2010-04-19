package ar.noxit.ehockey.service;

import java.util.List;

import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.web.pages.jugadores.DivisionPlano;
import ar.noxit.exceptions.NoxitException;

public interface IDivisionService {

    List<Division> getAll() throws NoxitException;

    public List<DivisionPlano> getAllPlano() throws NoxitException;

}
