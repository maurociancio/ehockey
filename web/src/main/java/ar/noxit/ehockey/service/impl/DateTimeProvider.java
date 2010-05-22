package ar.noxit.ehockey.service.impl;

import ar.noxit.ehockey.service.IDateTimeProvider;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class DateTimeProvider implements IDateTimeProvider {

    @Override
    public LocalDateTime getLocalDateTime() {
        return new LocalDateTime();
    }

    @Override
    public LocalDate getLocalDate() {
        return new LocalDate();
    }
}
