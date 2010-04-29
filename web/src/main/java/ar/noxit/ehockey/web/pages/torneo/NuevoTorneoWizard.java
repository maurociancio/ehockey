package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.ehockey.service.ITorneoService;
import ar.noxit.ehockey.service.transfer.PartidoInfo;
import ar.noxit.ehockey.web.pages.models.DivisionListModel;
import ar.noxit.ehockey.web.pages.models.IdDivisionModel;
import ar.noxit.ehockey.web.pages.models.IdSectorModel;
import ar.noxit.ehockey.web.pages.models.SectorListModel;
import ar.noxit.ehockey.web.pages.models.SelectedEquipoModel;
import ar.noxit.ehockey.web.pages.renderers.DivisionRenderer;
import ar.noxit.ehockey.web.pages.renderers.SectorRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LocalDateTimeFormatModel;
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
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NuevoTorneoWizard extends Wizard {

    @SpringBean
    private IEquiposService equiposService;
    @SpringBean
    private ITorneoService torneoService;
    @SpringBean
    private IExceptionConverter exceptionConverter;
    @SpringBean
    private IDivisionService divisionService;
    @SpringBean
    private ISectorService sectorService;
    private static final Logger logger = LoggerFactory.getLogger(NuevoTorneoWizard.class);
    private String nombre;
    private IModel<? extends List<PartidoInfo>> partidos = new Model<ArrayList<PartidoInfo>>(
            new ArrayList<PartidoInfo>());
    private Integer divisionId;
    private Integer sectorId;

    public NuevoTorneoWizard(String id) {
        super(id);

        WizardModel wizardModel = new WizardModel() {

            @Override
            public void finish() {
                super.finish();

                try {
                    List<PartidoInfo> partidosInfo = partidos.getObject();
                    torneoService.crearTorneo(nombre, partidosInfo);
                    setResponsePage(new NuevoTorneoPage(Model.of("Torneo creado correctamente.")));
                } catch (NoxitException e) {
                    logger.debug("Excepción creando torneo", e);
                    error(exceptionConverter.convert(e));
                }
            }

            @Override
            public void cancel() {
                super.cancel();

                setResponsePage(new NuevoTorneoPage(Model.of("Se canceló la creación del torneo.")));
            }
        };

        wizardModel.add(new NombreTorneoStep());
        wizardModel.add(new CaracteristicasEquiposStep());
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

    public class CaracteristicasEquiposStep extends WizardStep {

        public CaracteristicasEquiposStep() {
            setTitleModel(Model.of("Características del torneo"));
            setSummaryModel(Model.of("Defina la sección y la división del torneo"));

            add(new DropDownChoice<Division>("division", new IdDivisionModel(
                    new PropertyModel<Integer>(NuevoTorneoWizard.this, "divisionId"),
                    divisionService), new DivisionListModel(divisionService),
                    new DivisionRenderer()).setRequired(true));

            add(new DropDownChoice<Sector>("sector", new IdSectorModel(
                    new PropertyModel<Integer>(NuevoTorneoWizard.this, "sectorId"),
                    sectorService), new SectorListModel(sectorService),
                    new SectorRenderer()).setRequired(true));
        }
    }

    public class CrearPartidosStep extends WizardStep {

        public CrearPartidosStep() {
            setTitleModel(Model.of("Partidos del Torneo"));
            setSummaryModel(Model.of("Defina los partidos que habrá en este torneo"));

            final WebMarkupContainer wmc = new WebMarkupContainer("partidos");
            wmc.setOutputMarkupId(true);

            wmc.add(new ListView<PartidoInfo>("partidos", partidos) {

                @Override
                protected void populateItem(ListItem<PartidoInfo> item) {
                    IModel<PartidoInfo> model = item.getModel();

                    item.add(new Label("local", getEquipoModel(model, "equipoLocalId")));
                    item.add(new Label("visitante", getEquipoModel(model, "equipoVisitanteId")));
                    item.add(new Label("numero", new PropertyModel<Integer>(model, "numeroFecha")));
                    item.add(new Label("fecha", new LocalDateTimeFormatModel(new PropertyModel<LocalDateTime>(model,
                            "fecha"))));
                    item.add(removeLink("borrar", item));
                }

                private IModel<String> getEquipoModel(IModel<PartidoInfo> model, String expression) {
                    return new PropertyModel<String>(
                            new SelectedEquipoModel(new PropertyModel<Integer>(model, expression), equiposService),
                            "nombre");
                }
            });
            wmc.add(new WebMarkupContainer("sin_partidos") {

                @Override
                public boolean isVisible() {
                    return partidos.getObject().isEmpty();
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
