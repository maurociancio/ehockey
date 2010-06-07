package ar.noxit.ehockey.exception;

import ar.noxit.ehockey.service.IReportable;

public interface Descriptible {

    String getDescripcion();

    void reportar(IReportable reportable);
}
