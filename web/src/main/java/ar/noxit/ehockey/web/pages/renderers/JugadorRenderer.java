package ar.noxit.ehockey.web.pages.renderers;

import ar.noxit.ehockey.model.Jugador;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class JugadorRenderer implements IChoiceRenderer<Jugador> {

    private static final IChoiceRenderer<Jugador> JUGADORRENDERER = new JugadorRenderer();

    public static IChoiceRenderer<Jugador> get() {
        return JUGADORRENDERER;
    }

    private JugadorRenderer() {
    }

    @Override
    public Object getDisplayValue(Jugador object) {
        return object.getApellido() + " " + object.getNombre();
    }

    @Override
    public String getIdValue(Jugador object, int index) {
        return object.getFicha().toString();
    }
}