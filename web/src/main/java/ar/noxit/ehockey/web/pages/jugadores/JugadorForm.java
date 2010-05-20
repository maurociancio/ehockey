package ar.noxit.ehockey.web.pages.jugadores;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.ehockey.web.pages.models.ClubListModel;
import ar.noxit.ehockey.web.pages.models.DivisionListModel;
import ar.noxit.ehockey.web.pages.models.IdClubModel;
import ar.noxit.ehockey.web.pages.models.IdDivisionModel;
import ar.noxit.ehockey.web.pages.models.IdSectorModel;
import ar.noxit.ehockey.web.pages.models.SectorListModel;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import ar.noxit.ehockey.web.pages.renderers.DivisionRenderer;
import ar.noxit.ehockey.web.pages.renderers.SectorRenderer;
import ar.noxit.ehockey.web.util.YearMonthDatePicker;
import ar.noxit.web.wicket.model.Date2LocalDateModelAdapter;
import java.util.Arrays;
import java.util.Date;
import org.apache.commons.lang.Validate;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.joda.time.LocalDate;

public abstract class JugadorForm extends Panel {

    @SpringBean
    private IClubService clubService;
    @SpringBean
    private IDivisionService divisionService;
    @SpringBean
    private ISectorService sectorService;
    private final static String textPattern = "([a-zñáéíóúA-ZÑÁÉÍÓÚ])+";

    public JugadorForm(String id, final IModel<JugadorPlano> jugador) {
        super(id);
        Validate.notNull(jugador);

        Form<JugadorPlano> form = new Form<JugadorPlano>("agregar_jugador",
                jugador) {

            @Override
            protected void onSubmit() {
                JugadorForm.this.onSubmit(jugador);
            }
        };

        PatternValidator textValidator = new PatternValidator(textPattern);
        form.add(new FeedBackLabelAttachedComponent<FormComponent<String>>(
                "nombrefragment", "nombrepanel", "nombrefeedback",
                new RequiredTextField<String>("nombre",
                        new PropertyModel<String>(jugador, "nombre"))
                        .add(textValidator), form));

        form.add(new FeedBackLabelAttachedComponent<FormComponent<String>>(
                "apellidofragment", "apellidopanel", "apellidofeedback",
                new RequiredTextField<String>("apellido",
                        new PropertyModel<String>(jugador, "apellido"))
                        .add(textValidator), form));

        FormComponent<String> tipodoc = new RadioChoice<String>("tipodoc",
                new PropertyModel<String>(jugador, "tipoDocumento"), Arrays
                        .asList(new String[] { "DNI", "LC", "LE" }))
                .setRequired(true);
        form.add(new FeedBackLabelAttachedComponent<FormComponent<String>>(
                "tipodocfragment", "tipodocpanel", "tipodocfeedback", tipodoc,
                form));

        FormComponent<String> numerodoc = new RequiredTextField<String>(
                "numerodoc", new PropertyModel<String>(jugador,
                        "numeroDocumento")).add(new PatternValidator(
                "\\d{1,10}"));
        form.add(new FeedBackLabelAttachedComponent<FormComponent<String>>(
                "numerodocfragment", "numerodocpanel", "numerodocfeedback",
                numerodoc, form));

        PropertyModel<LocalDate> modelFechaNacimiento = new PropertyModel<LocalDate>(
                jugador, "fechaNacimiento");

        FormComponent<Date> fechaNacimiento = new DateTextField("fechanac",
                new Date2LocalDateModelAdapter(modelFechaNacimiento),
                "dd/MM/yyyy").setRequired(true);
        fechaNacimiento.add(new YearMonthDatePicker());

        form.add(new FeedBackLabelAttachedComponent<FormComponent<Date>>(
                "fechanacfragment", "fechanacpanel", "fechanacfeedback",
                fechaNacimiento, form));

        FormComponent<String> tel = new TextField<String>("telefono",
                new PropertyModel<String>(jugador, "telefono"))
                .add(StringValidator.lengthBetween(7, 20));
        form.add(new FeedBackLabelAttachedComponent<FormComponent<String>>(
                "telefonofragment", "telefonopanel", "telefonofeedback", tel,
                form));

        FormComponent<Club> club = new DropDownChoice<Club>("club",
                new IdClubModel(new PropertyModel<Integer>(jugador, "clubId"),
                        clubService), new ClubListModel(clubService),
                new ClubRenderer()).setRequired(true);
        form.add(club);
        form.add(new FeedBackLabelAttachedComponent<FormComponent<Club>>(
                "clubfragment", "clubpanel", "clubfeedback", club, form,
                "onchange"));

        FormComponent<Division> division = new DropDownChoice<Division>(
                "division", new IdDivisionModel(new PropertyModel<Integer>(
                        jugador, "divisionId"), divisionService),
                new DivisionListModel(divisionService), new DivisionRenderer())
                .setRequired(true);
        form.add(division);
        form.add(new FeedBackLabelAttachedComponent<FormComponent<Division>>(
                "divisionfragment", "divisionpanel", "divisionfeedback",
                division, form, "onchange"));

        FormComponent<Sector> sector = new RadioChoice<Sector>("sector",
                new IdSectorModel(new PropertyModel<Integer>(jugador,
                        "sectorId"), sectorService), new SectorListModel(
                        sectorService), new SectorRenderer()).setRequired(true);
        form.add(sector);
        form.add(new FeedbackLabel("sectorfeedback", sector));

        add(form);
        add(new FeedbackPanel("feedback").setFilter(
                new ErrorLevelsFeedbackMessageFilter(
                        new int[] { FeedbackMessage.ERROR }))
                .add(
                        new AttributeModifier("class", true, Model
                                .of("feedbacklabel"))));
    }

    protected abstract void onSubmit(IModel<JugadorPlano> jugador);
}