package ar.noxit.ehockey.service;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public interface IDateTimeProvider {

    LocalDateTime getLocalDateTime();

    LocalDate getLocalDate();
}
