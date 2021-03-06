package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.ehockey.service.ITorneoService;
import ar.noxit.ehockey.service.transfer.PartidoInfo;
import ar.noxit.ehockey.web.pages.models.DivisionListModel;
import ar.noxit.ehockey.web.pages.models.DivisionModel;
import ar.noxit.ehockey.web.pages.models.EquipoModel;
import ar.noxit.ehockey.web.pages.models.EquiposDeSectorYDivisionListModel;
import ar.noxit.ehockey.web.pages.models.EquiposSeleccionadosListModel;
import ar.noxit.ehockey.web.pages.models.SectorModel;
import ar.noxit.ehockey.web.pages.models.SectoresListModel;
import ar.noxit.ehockey.web.pages.renderers.DivisionRenderer;
import ar.noxit.ehockey.web.pages.renderers.EquipoRenderer;
import ar.noxit.ehockey.web.pages.renderers.SectorRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.Date2LocalDateTimeAdapterModel;
import ar.noxit.web.wicket.model.LocalDateTimeFormatModel;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Page;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.extensions.markup.html.form.palette.component.Recorder;
import org.apache.wicket.extensions.wizard.IWizardModelListener;
import org.apache.wicket.extensions.wizard.IWizardStep;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.extensions.wizard.WizardModel;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NuevoTorneoWizard extends Wizard {

    @SpringBean
    private IEquipoService equipoService;
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

    private IModel<Integer> division = new Model<Integer>();
    private IModel<Integer> sector = new Model<Integer>();
    private IModel<? extends List<Integer>> equipos = new Model<ArrayList<Integer>>(new ArrayList<Integer>());

    private LocalDateTime inicioTorneo;
    private String diaInicio;
    private static final String DOMINGO = "Domingo";
    private static final String SABADO = "Sábado";
    private final static List<String> dias;
    static {
        dias = new ArrayList<String>();
        dias.add(SABADO);
        dias.add(DOMINGO);
    }

    private static final ResourceReference EDITIMAGE = new ResourceReference(NuevoTorneoWizard.class, "edit.png");

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
        wizardModel.add(new EquiposDisponiblesStep());
        wizardModel.add(new FechasPartidos());
        wizardModel.add(new CrearPartidosStep());

        wizardModel.addListener(new IWizardModelListener() {

            @Override
            public void onFinish() {
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onActiveStepChanged(IWizardStep newStep) {
                if (newStep.getClass().equals(CrearPartidosStep.class)) {
                    // rueda fecha partido local visitante
                    Integer[][] cronograma = { { 1, 1, 1, 1, 2 },
                            { 1, 1, 2, 4, 3 },
                            { 1, 2, 1, 3, 1 },
                            { 1, 2, 2, 2, 4 },
                            { 1, 3, 1, 1, 4 },
                            { 1, 3, 2, 3, 2 },
                            { 2, 1, 1, 2, 1 },
                            { 2, 1, 2, 3, 4 },
                            { 2, 2, 1, 1, 3 },
                            { 2, 2, 2, 4, 2 },
                            { 2, 3, 1, 4, 1 },
                            { 2, 3, 2, 2, 3 } };

                    LocalDateTime now = getInicioTorneo();

                    List<PartidoInfo> infoPartidos = partidos.getObject();
                    infoPartidos.clear();
                    for (Integer[] partidoActual : cronograma) {
                        PartidoInfo pi = new PartidoInfo();

                        Integer rueda = partidoActual[0];
                        Integer numeroFecha = partidoActual[1];
                        Integer partido = partidoActual[2];
                        Integer localIndex = partidoActual[3] - 1;
                        Integer visitanteIndex = partidoActual[4] - 1;

                        List<Integer> equiposIds = equipos.getObject();
                        Integer local = equiposIds.get(localIndex);
                        Integer visitante = equiposIds.get(visitanteIndex);

                        Integer ruedaYFecha = (rueda - 1) * 3 + numeroFecha;

                        pi.setNumeroFecha(numeroFecha);
                        pi.setEquipoLocalId(local);
                        pi.setEquipoVisitanteId(visitante);
                        pi.setRueda(rueda);
                        pi.setFecha(now.plusDays((ruedaYFecha - 1) * 7));
                        pi.setPartido(partido);
                        infoPartidos.add(pi);
                    }
                }
            }
        });

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

            add(new DropDownChoice<Division>("division", new DivisionModel(
                    division,
                    divisionService), new DivisionListModel(divisionService),
                    new DivisionRenderer()).setRequired(true));

            add(new DropDownChoice<Sector>("sector", new SectorModel(
                    sector,
                    sectorService), new SectoresListModel(sectorService),
                    new SectorRenderer()).setRequired(true));
        }
    }

    public class FechasPartidos extends WizardStep {

        public FechasPartidos() {
            setTitleModel(Model.of("Fechas de los Partidos"));
            setSummaryModel(Model
                    .of("Defina la fecha de inicio del torneo y los días en los que se juegan los partidos"));

            IModel<LocalDateTime> delegate = new PropertyModel<LocalDateTime>(NuevoTorneoWizard.this, "inicioTorneo");
            add(new DateTimeField("fechainicio", new Date2LocalDateTimeAdapterModel(delegate)) {

                @SuppressWarnings("unchecked")
                @Override
                protected DateTextField newDateTextField(String id, PropertyModel dateFieldModel) {
                    return new DateTextField(id, dateFieldModel, new PatternDateConverter("dd/MM/yyyy", false));
                }
            }.setRequired(true));

            add(new DropDownChoice<String>("dia", new PropertyModel<String>(NuevoTorneoWizard.this, "diaInicio"), dias)
                    .setRequired(true));
        }
    }

    public class EquiposDisponiblesStep extends WizardStep {

        public EquiposDisponiblesStep() {
            setTitleModel(Model.of("Equipos del Torneo"));
            setSummaryModel(Model.of("Elija cuatro equipos para conformar el torneo"));

            final Palette<Equipo> palette = new Palette<Equipo>("equipos",
                    new EquiposSeleccionadosListModel(equipoService, equipos),
                    new EquiposDeSectorYDivisionListModel(equipoService, sector, division),
                    EquipoRenderer.get(),
                    6,
                    true);
            add(palette);

            add(new AbstractFormValidator() {

                @Override
                public void validate(Form<?> form) {
                    Recorder<Equipo> recorderComponent = palette.getRecorderComponent();
                    int length = recorderComponent.getInput().split(",").length;
                    if (length != 4) {
                        error(recorderComponent, "cant_invalida");
                    }
                }

                @Override
                public FormComponent<?>[] getDependentFormComponents() {
                    FormComponent<?>[] components = new FormComponent<?>[1];
                    components[0] = palette.getRecorderComponent();
                    return components;
                }
            });
        }
    }

    public class CrearPartidosStep extends WizardStep {

        public CrearPartidosStep() {
            setTitleModel(Model.of("Partidos del Torneo"));
            setSummaryModel(Model.of("Defina los partidos que habrá en este torneo"));

            // modal window
            final ModalWindow modalWindow = new ModalWindow("modal");
            modalWindow.setPageMapName("modal-1");
            modalWindow.setCookieName("modal-1");

            add(modalWindow);

            final WebMarkupContainer wmc = new WebMarkupContainer("partidos");
            wmc.setOutputMarkupId(true);

            wmc.add(new ListView<PartidoInfo>("partidos", partidos) {

                @Override
                protected void populateItem(ListItem<PartidoInfo> item) {
                    final IModel<PartidoInfo> model = item.getModel();

                    item.add(new Label("local", getEquipoModel(model, "equipoLocalId")).setRenderBodyOnly(true));
                    item.add(new Label("visitante", getEquipoModel(model, "equipoVisitanteId"))
                                    .setRenderBodyOnly(true));
                    item.add(new Label("numero", new PropertyModel<Integer>(model, "numeroFecha"))
                            .setRenderBodyOnly(true));
                    item.add(new Label("rueda", new PropertyModel<Integer>(model, "rueda")).setRenderBodyOnly(true));
                    item.add(new Label("fecha", new LocalDateTimeFormatModel(new PropertyModel<LocalDateTime>(model,
                            "fecha"))).setRenderBodyOnly(true));

                    AjaxLink<Void> editLink = new AjaxLink<Void>("editar") {

                        @Override
                        public void onClick(AjaxRequestTarget target) {
                            modalWindow.setPageCreator(new ModalWindow.PageCreator() {

                                @Override
                                public Page createPage() {
                                    return new AgregarPartidoPage(modalWindow, model);
                                }
                            });
                            modalWindow.show(target);
                        }
                    };
                    editLink.add(new Image("editar", EDITIMAGE));
                    item.add(editLink);
                }

                private IModel<String> getEquipoModel(IModel<PartidoInfo> model, String expression) {
                    return new PropertyModel<String>(
                            new EquipoModel(new PropertyModel<Integer>(model, expression), equipoService),
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

            modalWindow.setWindowClosedCallback(new WindowClosedCallback() {

                @Override
                public void onClose(AjaxRequestTarget target) {
                    target.addComponent(wmc);
                }
            });

            add(new AbstractFormValidator() {

                @Override
                public void validate(Form<?> form) {
                    boolean error = false;
                    for (PartidoInfo pi : partidos.getObject()) {
                        if (pi.getFecha() == null) {
                            error = true;
                        }
                    }
                    if (error)
                        CrearPartidosStep.this.error("No se especificaron las fechas para todos los partidos");
                }

                @Override
                public FormComponent<?>[] getDependentFormComponents() {
                    return new FormComponent<?>[] {};
                }
            });
        }
    }

    private LocalDateTime getInicioTorneo() {
        LocalDateTime inicioActual = inicioTorneo;
        while (!matchaDia(inicioActual)) {
            inicioActual = inicioActual.plusDays(1);
        }
        return inicioActual;
    }

    private boolean matchaDia(LocalDateTime inicioActual) {
        if (diaInicio.equals(DOMINGO)) {
            return inicioActual.getDayOfWeek() == DateTimeConstants.SUNDAY;
        } else if (diaInicio.equals(SABADO)) {
            return inicioActual.getDayOfWeek() == DateTimeConstants.SATURDAY;
        }
        throw new IllegalStateException();
    }
}
