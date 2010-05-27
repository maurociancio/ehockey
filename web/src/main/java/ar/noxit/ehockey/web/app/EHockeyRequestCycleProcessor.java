package ar.noxit.ehockey.web.app;

import ar.noxit.ehockey.exception.PlanillaNoDisponibleRuntimeException;
import ar.noxit.ehockey.web.pages.base.MensajePage;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;

public class EHockeyRequestCycleProcessor extends WebRequestCycleProcessor {

    @Override
    protected Page onRuntimeException(Page page, RuntimeException e) {
        if (e.getCause() instanceof PlanillaNoDisponibleRuntimeException) {
            return new MensajePage("Planilla no disponible",
                    "La planilla no se encuentra disponible. La misma se puede acceder a"
                            + " partir de una semana antes del partido");
        }
        return super.onRuntimeException(page, e);
    }
}