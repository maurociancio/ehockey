package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.Division;
import ar.noxit.exceptions.NoxitException;
import java.util.List;

public interface IDivisionService {

    public Division get(Integer id) throws NoxitException;

    public List<Division> getAll() throws NoxitException;
}
