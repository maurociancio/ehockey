package ar.noxit.ehockey.web.pages.models;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Planilla;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public abstract class JugadorPlanillaModel extends LoadableDetachableModel<List<Jugador>> {

    private IModel<? extends Planilla> planillaModel;

    public JugadorPlanillaModel(IModel<? extends Planilla> planillaModel) {
        this.planillaModel = planillaModel;
    }

    @Override
    protected List<Jugador> load() {
        List<Jugador> result = new ArrayList<Jugador>();
        Planilla object = planillaModel.getObject();
        result.addAll(getJugadores(object));
        return (result);
    }

    protected abstract Set<Jugador> getJugadores(Planilla object);
}