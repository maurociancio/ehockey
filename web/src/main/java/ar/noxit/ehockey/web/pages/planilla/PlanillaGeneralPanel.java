package ar.noxit.ehockey.web.pages.planilla;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import ar.noxit.ehockey.model.PlanillaFinal;

public class PlanillaGeneralPanel extends Panel {

    public PlanillaGeneralPanel(String id, IModel<PlanillaFinal> planilla, IModel<Integer> golesLocal,
            IModel<Integer> golesVisitante) {
        super(id);

        add(new Label("local", new PropertyModel<String>(planilla, "local.nombre")));
        add(new RequiredTextField<Integer>("golesLocal", golesLocal, Integer.class));
        add(new Label("visitante", new PropertyModel<String>(planilla, "visitante.nombre")));
        add(new RequiredTextField<Integer>("golesVisitante", golesVisitante, Integer.class));
    }
}
