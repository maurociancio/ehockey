package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Planilla;
import java.util.Set;
import org.apache.wicket.model.IModel;

public class JugadorLocalModelItem extends JugadorPlanillaModel {

    public JugadorLocalModelItem(IModel<? extends Planilla> planillaModel) {
        super(planillaModel);
    }

    @Override
    protected Set<Jugador> getJugadores(Planilla object) {
        return object.getJugadoresL();
    }
}