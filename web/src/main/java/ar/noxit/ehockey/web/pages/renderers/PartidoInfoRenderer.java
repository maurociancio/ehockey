package ar.noxit.ehockey.web.pages.renderers;

import ar.noxit.ehockey.web.pages.torneo.PartidoInfo;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

public class PartidoInfoRenderer implements IChoiceRenderer<PartidoInfo> {

    public PartidoInfoRenderer() {
    }

    @Override
    public Object getDisplayValue(PartidoInfo object) {
        return object.getEquipoLocalId();
    }

    @Override
    public String getIdValue(PartidoInfo object, int index) {
        return Integer.valueOf(index).toString();
    }
}