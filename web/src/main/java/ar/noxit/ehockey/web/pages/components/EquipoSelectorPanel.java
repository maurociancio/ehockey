package ar.noxit.ehockey.web.pages.components;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.models.ClubModel;
import ar.noxit.ehockey.web.pages.models.ClubesListModel;
import ar.noxit.ehockey.web.pages.models.EquiposPorClubListModel;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import ar.noxit.ehockey.web.pages.renderers.EquipoRenderer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EquipoSelectorPanel extends FormComponentPanel<Equipo> {

    @SpringBean
    private IClubService clubService;
    private HybridSingleAndMultipleChoicePanel<Equipo> dropDownEquipo;

    public EquipoSelectorPanel(String id, IModel<Equipo> equipo) {
        super(id, equipo);
        IModel<Integer> idClub = new Model<Integer>();

        this.dropDownEquipo =
                new AjaxHybridSingleAndMultipleChoicePanel<Equipo>(
                        "equipo",
                        equipo,
                        new EquiposPorClubListModel(idClub, clubService),
                        EquipoRenderer.get()) {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                EquipoSelectorPanel.this.onUpdate(target);
            }
        };

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
                EquipoSelectorPanel.this.onUpdate(target);
            };
        });
    }

    @Override
    protected void convertInput() {
        Equipo convertedInput = dropDownEquipo.getConvertedInput();
        setConvertedInput(convertedInput);
    }

    @Override
    protected boolean getStatelessHint() {
        return false;
    }

    //Redefinir si se quiere hacer algo en el update
    protected void onUpdate(AjaxRequestTarget target) {
    }
}
