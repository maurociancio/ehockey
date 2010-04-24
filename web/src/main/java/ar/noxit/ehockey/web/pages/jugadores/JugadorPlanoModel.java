package ar.noxit.ehockey.web.pages.jugadores;

import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;

public class JugadorPlanoModel extends LDM<JugadorPlano> {
    @Override
    protected JugadorPlano doLoad() throws NoxitException {
        return getObject();
    }
}
