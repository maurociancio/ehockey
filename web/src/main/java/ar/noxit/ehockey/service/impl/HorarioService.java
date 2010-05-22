package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.service.IHorarioService;
import org.apache.commons.lang.Validate;
import org.joda.time.LocalDateTime;

public class HorarioService implements IHorarioService {

    private static LocalDateTime instante = null;

    @Override
    public void definirHoraSistema(LocalDateTime instante) {
        Validate.notNull(instante);

        HorarioService.instante = instante;
    }

    public static LocalDateTime getInstante() {
        if (instante == null) {
            instante = new LocalDateTime();
        }
        return instante;
    }
}
