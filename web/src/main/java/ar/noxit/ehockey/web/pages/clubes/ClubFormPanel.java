package ar.noxit.ehockey.web.pages.clubes;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

import ar.noxit.ehockey.model.Club;

public abstract class ClubFormPanel extends Panel {
    
    public ClubFormPanel(String id) {
        super(id);
        
        Form<Club> clubForm = new Form<Club>("formulario"){
            @Override
            protected void onSubmit() {
                ClubFormPanel.this.onSubmit();
            }
        };
        
        clubForm.add(new RequiredTextField<String>("nombre"));
        clubForm.add(new RequiredTextField<String>("nombrecompleto"));
        clubForm.add(new RequiredTextField<String>("direccion"));
        clubForm.add(new RequiredTextField<String>("ciudad"));
        clubForm.add(new RequiredTextField<String>("codigopostal"));
        clubForm.add(new RequiredTextField<String>("provincia"));
        clubForm.add(new RequiredTextField<String>("telefono"));
        clubForm.add(new TextField<String>("email"));
        clubForm.add(new TextField<String>("web"));
        clubForm.add(new TextArea<String>("observaciones"));
        
        add(clubForm);
        add(new FeedbackPanel("feedback"));
    }
    
    public abstract void onSubmit();

}
