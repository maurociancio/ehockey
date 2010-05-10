package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.service.IJugadorService;
import org.apache.wicket.model.Model;

public class JugadorModel extends JugadorIdModel {

    public JugadorModel(Integer jugadorId, IJugadorService jugadorService) {
        super(jugadorService, Model.of(jugadorId));
    }
}
