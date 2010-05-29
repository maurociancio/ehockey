package ar.noxit.ehockey.web.pages.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.models.ClubListModel;
import ar.noxit.ehockey.web.pages.models.IdClubModel;
import ar.noxit.ehockey.web.pages.models.TodosEquiposPorClubModel;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import ar.noxit.ehockey.web.pages.renderers.EquipoRenderer;

public class EquipoSelectorPanel extends Panel {

    private Integer idClub;
    @SpringBean
    private IClubService clubService;

    public EquipoSelectorPanel(String id, IModel<Equipo> equipo) {
        super(id);

        final DropDownChoice<Equipo> dropDownEquipo = new DropDownChoice<Equipo>("equipo", equipo,
                new TodosEquiposPorClubModel(new PropertyModel<Integer>(this, "idClub"), clubService), 
                EquipoRenderer.get());

        dropDownEquipo.setRequired(true).setOutputMarkupId(true);

        add(new DropDownChoice<Club>("club", new IdClubModel(new PropertyModel<Integer>(this, "idClub"), clubService),
                new ClubListModel(clubService), new ClubRenderer()).add(new AjaxFormComponentUpdatingBehavior(
                "onchange") {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.addComponent(dropDownEquipo);
            }
        }));

        add(dropDownEquipo);
    }
}
