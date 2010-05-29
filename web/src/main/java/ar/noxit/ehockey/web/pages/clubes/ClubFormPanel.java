package ar.noxit.ehockey.web.pages.clubes;

import org.apache.wicket.markup.html.form.Form;
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
        add(clubForm);
    }
    
    public abstract void onSubmit();

}
