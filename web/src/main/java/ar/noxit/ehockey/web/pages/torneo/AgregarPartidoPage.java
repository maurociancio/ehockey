package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.ehockey.web.pages.base.AbstractColorBasePage;
import ar.noxit.ehockey.web.pages.models.SelectedEquipoModel;
import ar.noxit.ehockey.web.pages.models.TodosEquiposModel;
import ar.noxit.ehockey.web.pages.renderers.EquipoRenderer;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AgregarPartidoPage extends AbstractColorBasePage {

    @SpringBean
    private IEquiposService equiposService;
    private PartidoInfo partido = new PartidoInfo();

    public AgregarPartidoPage(final ModalWindow modalWindow, final IModel<? extends List<PartidoInfo>> partidos) {
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);

        Form<Void> form = new Form<Void>("form");

        // choices
        IModel<List<Equipo>> choices = new TodosEquiposModel(equiposService);
        // renderer
        IChoiceRenderer<Equipo> renderer = EquipoRenderer.get();

        form.add(new DropDownChoice<Equipo>("local",
                new SelectedEquipoModel(new PropertyModel<Integer>(partido, "equipoLocalId"), equiposService),
                choices,
                renderer).setRequired(true));

        form.add(new DropDownChoice<Equipo>("visitante",
                new SelectedEquipoModel(new PropertyModel<Integer>(partido, "equipoVisitanteId"), equiposService),
                choices,
                renderer).setRequired(true));

        form.add(new RequiredTextField<Integer>("numero_fecha",
                new PropertyModel<Integer>(partido, "numeroFecha"), Integer.class));

        form.add(new AjaxButton("submit") {

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                partidos.getObject().add(partido);

                modalWindow.close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.addComponent(feedbackPanel);
            }
        });

        add(form);
    }
}
