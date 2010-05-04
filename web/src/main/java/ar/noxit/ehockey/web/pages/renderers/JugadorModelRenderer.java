package ar.noxit.ehockey.web.pages.renderers;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Jugador;

public class JugadorModelRenderer implements IChoiceRenderer<IModel<Jugador>> {

    @Override
    public Object getDisplayValue(IModel<Jugador> arg0) {
        Jugador object = arg0.getObject();
        return object.getApellido() + ", " + object.getNombre();
    }

    @Override
    public String getIdValue(IModel<Jugador> arg0, int arg1) {
        return arg0.getObject().getFicha().toString();
    }
}
