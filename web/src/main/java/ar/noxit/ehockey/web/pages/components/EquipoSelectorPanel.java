package ar.noxit.ehockey.web.pages.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.models.ClubModel;
import ar.noxit.ehockey.web.pages.models.ClubesListModel;
import ar.noxit.ehockey.web.pages.models.EquiposPorClubListModel;
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
                        new EquiposPorClubListModel(idClub, clubService),
                        EquipoRenderer.get());

        dropDownEquipo.setRequired(true);
        dropDownEquipo.setOutputMarkupId(true);
        add(dropDownEquipo);

        add(new AjaxHybridSingleAndMultipleChoicePanel<Club>("club",
                new ClubModel(idClub, clubService),
                new ClubesListModel(clubService),
                new ClubRenderer()) {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.addComponent(dropDownEquipo);
            };
        });
    }

    @Override
    protected boolean getStatelessHint() {
        return false;
    }
}
