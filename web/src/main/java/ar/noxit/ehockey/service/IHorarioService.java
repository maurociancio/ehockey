package ar.noxit.ehockey.service;

import ar.noxit.exceptions.NoxitException;
import org.joda.time.LocalDateTime;

public interface IHorarioService {

    void definirHoraSistema(LocalDateTime instante) throws NoxitException;
}
