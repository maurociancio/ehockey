package ar.noxit.ehockey.web.pages.renderers;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import ar.noxit.ehockey.model.Sector;

public class SectorRenderer implements IChoiceRenderer<Sector> {
    @Override
    public Object getDisplayValue(Sector arg0) {
        return arg0.getSector();
    }

    @Override
    public String getIdValue(Sector arg0, int arg1) {
        return arg0.getId().toString();
    }
}
