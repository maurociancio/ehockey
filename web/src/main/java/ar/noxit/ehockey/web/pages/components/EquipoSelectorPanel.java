package ar.noxit.ehockey.web.pages.components;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.models.ClubListModel;
import ar.noxit.ehockey.web.pages.models.ClubModel;
import ar.noxit.ehockey.web.pages.models.TodosEquiposPorClubModel;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import ar.noxit.ehockey.web.pages.renderers.EquipoRenderer;

public class EquipoSelectorPanel extends Panel {

    @SpringBean
    private IClubService clubService;

    public EquipoSelectorPanel(String id, IModel<Equipo> equipo) {
        super(id);
        IModel<Integer> idClub = new Model<Integer>();

        final HybridSingleAndMultipleChoicePanel<Equipo> dropDownEquipo =
                new HybridSingleAndMultipleChoicePanel<Equipo>(
                        "equipo",
                        equipo,
                        new TodosEquiposPorClubModel(idClub, clubService),
                        EquipoRenderer.get());

        dropDownEquipo.setRequired(true);
        dropDownEquipo.setOutputMarkupId(true);
        add(dropDownEquipo);

        add(new HybridSingleAndMultipleChoicePanel<Club>("club",
                new ClubModel(idClub, clubService),
                new ClubListModel(clubService),
                new ClubRenderer()) {

            @Override
            protected FormComponent<Club> createMultivalueComponent(String id,
                    IModel<Club> model,
                    IModel<? extends List<? extends Club>> choices,
                    IChoiceRenderer<? super Club> renderer) {

                FormComponent<Club> obj = super.createMultivalueComponent(
                        id, model, choices, renderer);

                obj.add(new AjaxFormComponentUpdatingBehavior("onchange") {

                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        target.addComponent(dropDownEquipo);
                    }
                });
                return obj;
            };
        });
    }
    
    @Override
    protected boolean getStatelessHint() {
        return false;
    }
}
