package ar.noxit.ehockey.web.pages.clubes;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public abstract class ClubFormPanel extends Panel {

    private IModel<ClubPlano> clubPlano;

    public ClubFormPanel(String id, IModel<ClubPlano> clubModel) {
        super(id);
        this.clubPlano = clubModel;
        Form<ClubPlano> clubForm = new Form<ClubPlano>("formulario") {
            @Override
            protected void onSubmit() {
                ClubFormPanel.this.onSubmit(clubPlano);
            }
        };

        clubForm.add(new RequiredTextField<ClubPlano>("nombre", new PropertyModel<ClubPlano>(clubPlano, "nombre")));
        clubForm.add(new RequiredTextField<ClubPlano>("nombrecompleto", new PropertyModel<ClubPlano>(clubPlano,
                "nombreCompleto")));
        clubForm.add(new RequiredTextField<ClubPlano>("direccion", new PropertyModel<ClubPlano>(clubPlano, "direccion")));
        clubForm.add(new RequiredTextField<ClubPlano>("ciudad", new PropertyModel<ClubPlano>(clubPlano, "ciudad")));
        clubForm.add(new RequiredTextField<ClubPlano>("codigopostal", new PropertyModel<ClubPlano>(clubPlano,
                "codigoPostal")));
        clubForm.add(new RequiredTextField<ClubPlano>("provincia", new PropertyModel<ClubPlano>(clubPlano, "provincia")));
        clubForm.add(new RequiredTextField<ClubPlano>("telefono", new PropertyModel<ClubPlano>(clubPlano, "telefono")));
        clubForm.add(new TextField<ClubPlano>("email", new PropertyModel<ClubPlano>(clubPlano, "email")));
        clubForm.add(new TextField<ClubPlano>("web", new PropertyModel<ClubPlano>(clubPlano, "web")));
        clubForm.add(new TextArea<ClubPlano>("observaciones", new PropertyModel<ClubPlano>(clubPlano, "observaciones")));

        add(clubForm);
        add(new FeedbackPanel("feedback"));
    }

    public abstract void onSubmit(IModel<ClubPlano> clubPlano);

}
