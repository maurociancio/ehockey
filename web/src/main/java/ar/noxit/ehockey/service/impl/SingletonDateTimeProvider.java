package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.service.IDateTimeProvider;
import org.joda.time.LocalDateTime;

public class SingletonDateTimeProvider implements IDateTimeProvider {

    @Override
    public LocalDateTime getLocalDateTime() {
        return HorarioService.getInstante();
    }
}
