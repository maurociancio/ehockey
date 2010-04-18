package ar.noxit.ehockey.web.pages.torneo;

import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.extensions.wizard.WizardModel;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.markup.html.form.RequiredTextField;
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
        }
    }
}
