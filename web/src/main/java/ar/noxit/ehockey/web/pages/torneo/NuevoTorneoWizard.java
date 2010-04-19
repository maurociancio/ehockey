package ar.noxit.ehockey.web.pages.torneo;

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
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
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

            final WebMarkupContainer wmc = new WebMarkupContainer("partidos");
            wmc.setOutputMarkupId(true);

            final IModel<? extends List<PartidoInfo>> partidos = new Model<ArrayList<PartidoInfo>>(
                    new ArrayList<PartidoInfo>());

            wmc.add(new ListView<PartidoInfo>("partidos", partidos) {

                @Override
                protected void populateItem(ListItem<PartidoInfo> item) {
                    IModel<PartidoInfo> model = item.getModel();
                    item.add(new Label("local", new PropertyModel<Integer>(model, "equipoLocalId")));
                    item.add(new Label("visitante", new PropertyModel<Integer>(model, "equipoVisitanteId")));
                    item.add(new Label("numero", new PropertyModel<Integer>(model, "numeroFecha")));
                }
            });
            add(wmc);

            // modal window
            final ModalWindow modalWindow = new ModalWindow("modal");
            modalWindow.setPageMapName("modal-1");
            modalWindow.setCookieName("modal-1");
            modalWindow.setWindowClosedCallback(new WindowClosedCallback() {

                @Override
                public void onClose(AjaxRequestTarget target) {
                    target.addComponent(wmc);
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
