package ar.noxit.ehockey.web.pages;

import ar.noxit.ehockey.model.Equipo;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class EquipoRenderer implements IChoiceRenderer<Equipo> {

    private static final IChoiceRenderer<Equipo> EQUIPORENDERER = new EquipoRenderer();

    public static IChoiceRenderer<Equipo> get() {
        return EQUIPORENDERER;
    }

    private EquipoRenderer() {
    }

    @Override
    public Object getDisplayValue(Equipo object) {
        return object.getNombre();
    }

    @Override
    public String getIdValue(Equipo object, int index) {
        return object.getId().toString();
    }
}