package ar.noxit.ehockey.web.pages.planilla;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Planilla;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;

public class ModificarPlanillaPage extends AbstractContentPage {

    private Integer golesLocal;
    private Integer golesVisitante;
    private EquipoInfo infoLocal = new EquipoInfo();
    private EquipoInfo infoVisitante = new EquipoInfo();

    public ModificarPlanillaPage(IModel<Planilla> planilla) {
        add(new FeedbackPanel("feedback"));
        Form<Void> form = new Form<Void>("edicion_planilla") {

            @Override
            protected void onSubmit() {
                // TODO graba lo que se hizo
            }
        };
        form.add(new PlanillaGeneralPanel("planillaGeneral", planilla, new PropertyModel<Integer>(this, "golesLocal"),
                new PropertyModel<Integer>(this, "golesVisitante")));
        form.add(new PlanillaEquipoPanel("planillaLocal", new PropertyModel<Equipo>(planilla, "local"), planilla, Model
                .of(infoLocal)));
        form.add(new PlanillaEquipoPanel("planillaVisitante", new PropertyModel<Equipo>(planilla, "visitante"),
                planilla, Model.of(infoVisitante)));

        this.add(form);
    }
}
