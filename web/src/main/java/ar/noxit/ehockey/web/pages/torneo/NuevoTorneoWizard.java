package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.web.pages.renderers.PartidoInfoRenderer;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.extensions.wizard.WizardModel;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class NuevoTorneoWizard extends Wizard {

    private String nombre;

    public NuevoTorneoWizard(String id) {
        super(id);

        WizardModel wizardModel = new WizardModel() {

            @Override
            public void finish() {
                super.finish();

                // #TODO grabar el torneo
            }
        };

        wizardModel.add(new NombreTorneoStep());
        wizardModel.add(new CrearPartidosStep());
        init(wizardModel);
    }

    public class NombreTorneoStep extends WizardStep {

        public NombreTorneoStep() {
            setTitleModel(Model.of("Nombre del Torneo"));
            setSummaryModel(Model.of("Defina el nombre del Torneo"));

            add(new RequiredTextField<String>("nombre", new PropertyModel<String>(NuevoTorneoWizard.this, "nombre")));
        }
    }

    public class CrearPartidosStep extends WizardStep {

        public CrearPartidosStep() {
            setTitleModel(Model.of("Partidos del Torneo"));
            setSummaryModel(Model.of("Defina los partidos que habrá en este torneo"));

            final IModel<? extends List<PartidoInfo>> partidos = new Model<ArrayList<PartidoInfo>>(
                    new ArrayList<PartidoInfo>());

            final DropDownChoice<PartidoInfo> partidosComponent =
                    new DropDownChoice<PartidoInfo>("partidos",
                    new Model<PartidoInfo>(),
                    partidos,
                    new PartidoInfoRenderer());
            partidosComponent.setNullValid(true);
            partidosComponent.setOutputMarkupId(true);
            add(partidosComponent);

            // modal window
            final ModalWindow modalWindow = new ModalWindow("modal");
            modalWindow.setPageMapName("modal-1");
            modalWindow.setCookieName("modal-1");
            modalWindow.setWindowClosedCallback(new WindowClosedCallback() {

                @Override
                public void onClose(AjaxRequestTarget target) {
                    target.addComponent(partidosComponent);
                }
            });
            add(modalWindow);

            add(new AjaxLink<Void>("agregar_partido") {

                @Override
                public void onClick(AjaxRequestTarget target) {
                    modalWindow.setPageCreator(new ModalWindow.PageCreator() {

                        @Override
                        public Page createPage() {
                            return new AgregarPartidoPage(modalWindow, partidos);
                        }
                    });
                    modalWindow.show(target);
                }
            });
        }
    }
}
