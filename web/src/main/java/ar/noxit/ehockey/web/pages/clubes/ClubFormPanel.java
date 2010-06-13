package ar.noxit.ehockey.web.pages.clubes;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.UrlValidator;

public abstract class ClubFormPanel extends Panel {

    private IModel<ClubPlano> clubPlano;
    // #TODO @repetido
    private final static String textPattern = "([a-zñáéíóúA-ZÑÁÉÍÓÚ ])+";
    private static final String textAndNumberPattern = "([\\da-zñáéíóúA-ZÑÁÉÍÓÚ ])+";
    private static final String phonePattern = "\\d{4,12}";

    public ClubFormPanel(String id, IModel<ClubPlano> clubModel) {
        super(id);
        this.clubPlano = clubModel;
        Form<ClubPlano> clubForm = new Form<ClubPlano>("formulario") {
            @Override
            protected void onSubmit() {
                ClubFormPanel.this.onSubmit(clubPlano);
            }
        };

        clubForm.add(new RequiredTextField<String>("nombre", new PropertyModel<String>(clubPlano, "nombre"))
                .add(new PatternValidator(textPattern)));
        clubForm.add(new RequiredTextField<String>("nombrecompleto", new PropertyModel<String>(clubPlano,
                "nombreCompleto")).add(new PatternValidator(textPattern)));
        clubForm.add(new RequiredTextField<String>("direccion", new PropertyModel<String>(clubPlano, "direccion"))
                .add(new PatternValidator(textAndNumberPattern)));
        clubForm.add(new RequiredTextField<String>("ciudad", new PropertyModel<String>(clubPlano, "ciudad"))
                .add(new PatternValidator(textAndNumberPattern)));
        clubForm
                .add(new RequiredTextField<String>("codigopostal", new PropertyModel<String>(clubPlano, "codigoPostal"))
                        .add(new PatternValidator(textAndNumberPattern)));
        clubForm.add(new RequiredTextField<String>("provincia", new PropertyModel<String>(clubPlano, "provincia"))
                .add(new PatternValidator(textAndNumberPattern)));
        clubForm.add(new RequiredTextField<String>("telefono", new PropertyModel<String>(clubPlano, "telefono"))
                .add(new PatternValidator(phonePattern)));
        clubForm.add(new TextField<String>("email", new PropertyModel<String>(clubPlano, "email"))
                .add(EmailAddressValidator.getInstance()));
        clubForm.add(new TextField<String>("web", new PropertyModel<String>(clubPlano, "web")).add(new UrlValidator(
                UrlValidator.ALLOW_2_SLASHES)));
        clubForm.add(new TextField<String>("observaciones", new PropertyModel<String>(clubPlano, "observaciones")));

        add(clubForm);
        add(new FeedbackPanel("feedback"));
    }

    public abstract void onSubmit(IModel<ClubPlano> clubPlano);

}
