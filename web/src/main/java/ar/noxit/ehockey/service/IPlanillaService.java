package ar.noxit.ehockey.service;

import ar.noxit.ehockey.model.Planilla;
import ar.noxit.exceptions.NoxitException;

public interface IPlanillaService {

    Planilla get(Integer id) throws NoxitException;

}
