package ar.noxit.ehockey.web.pages.renderers;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import ar.noxit.ehockey.model.Club;

public class ClubRenderer implements IChoiceRenderer<Club> {
    @Override
    public Object getDisplayValue(Club arg0) {
        return arg0.getNombre();
    }

    @Override
    public String getIdValue(Club arg0, int arg1) {
        return arg0.getId().toString();
    }
}
