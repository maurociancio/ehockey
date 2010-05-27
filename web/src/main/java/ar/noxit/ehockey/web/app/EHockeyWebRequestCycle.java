package ar.noxit.ehockey.web.app;

import ar.noxit.ehockey.service.IPartidoService;
import ar.noxit.exceptions.NoxitException;
import org.apache.wicket.Response;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EHockeyWebRequestCycle extends WebRequestCycle {

    @SpringBean
    private transient IPartidoService partidoService;
    private static final Logger logger = LoggerFactory.getLogger(EHockeyWebRequestCycle.class);

    public EHockeyWebRequestCycle(WebApplication application, WebRequest request, Response response) {
        super(application, request, response);
        InjectorHolder.getInjector().inject(this);

        try {
            partidoService.actualizarEstados();
        } catch (NoxitException e) {
            logger.warn("error actualizando estados", e);
        }

        partidoService = null;
    }
}