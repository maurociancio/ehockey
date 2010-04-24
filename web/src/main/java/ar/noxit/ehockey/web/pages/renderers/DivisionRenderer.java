package ar.noxit.ehockey.web.pages.renderers;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import ar.noxit.ehockey.model.Division;

public class DivisionRenderer implements IChoiceRenderer<Division> {
    @Override
    public Object getDisplayValue(Division arg0) {
        return arg0.getNombre();
    }

    @Override
    public String getIdValue(Division arg0, int arg1) {
        return arg0.getId().toString();
    }
}