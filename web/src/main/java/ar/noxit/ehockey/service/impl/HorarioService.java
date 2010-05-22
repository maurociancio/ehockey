package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.service.IHorarioService;
import ar.noxit.exceptions.NoxitException;
import org.apache.commons.lang.Validate;
import org.joda.time.LocalDateTime;

public class HorarioService implements IHorarioService {

    private static LocalDateTime instante = null;

    @Override
    public void definirHoraSistema(LocalDateTime instante) {
        Validate.notNull(instante);

        HorarioService.instante = instante;
    }

    @Override
    public LocalDateTime getHoraSistema() throws NoxitException {
        return getInstante();
    }

    public static LocalDateTime getInstante() {
        if (instante == null) {
            instante = new LocalDateTime();
        }
        return instante;
    }
}
