package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.models.ClubesListModel;
import ar.noxit.ehockey.web.pages.models.ClubModel;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;

public class UsuarioRepresentantePanel extends Panel {

    @SpringBean
    private IClubService clubService;

    public UsuarioRepresentantePanel(String id, IModel<UsuarioDTO> usuarioRepres) {
        super(id);

        IModel<Club> club = new ClubModel(new PropertyModel<Integer>(usuarioRepres, "clubId"), clubService);
        add(new DropDownChoice<Club>("clubes", club, new ClubesListModel(clubService), new ClubRenderer())
                .setRequired(true));
        add(new RequiredTextField<String>("cargo", new PropertyModel<String>(usuarioRepres, "cargo")));
    }
}
