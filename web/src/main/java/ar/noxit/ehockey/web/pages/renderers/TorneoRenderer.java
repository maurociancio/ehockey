package ar.noxit.ehockey.web.pages.renderers;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

import ar.noxit.ehockey.model.Torneo;

public class TorneoRenderer implements IChoiceRenderer<Torneo> {

    @Override
    public Object getDisplayValue(Torneo object) {
        return object.getNombre();
    }

    @Override
    public String getIdValue(Torneo object, int index) {
        return object.getId().toString();
    }

}
