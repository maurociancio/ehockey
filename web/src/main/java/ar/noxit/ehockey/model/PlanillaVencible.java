package ar.noxit.ehockey.model;

import ar.noxit.ehockey.exception.PlanillaNoVencidaException;
import org.joda.time.LocalDateTime;

public interface PlanillaVencible {

    void checkVencimiento(LocalDateTime now) throws PlanillaNoVencidaException;
}
