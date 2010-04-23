package ar.noxit.ehockey.service;

import java.util.List;

import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.web.pages.jugadores.DivisionPlano;
import ar.noxit.exceptions.NoxitException;

public interface IDivisionService {

    public Division get(Integer id) throws NoxitException;

    public List<Division> getAll() throws NoxitException;
}
