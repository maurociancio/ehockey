package ar.noxit.ehockey.web.pages.usuarios;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.models.ClubListModel;
import ar.noxit.ehockey.web.pages.models.IdClubModel;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class UsuarioRepresentantePanel extends Panel {

    @SpringBean
    private IClubService clubService;

    public UsuarioRepresentantePanel(String id, IModel<UsuarioDTO> usuarioRepres) {
        super(id);

        IModel<Club> club = new IdClubModel(new Model<Integer>(), clubService);
        add(new DropDownChoice<Club>("clubes", club, new ClubListModel(clubService), new ClubRenderer())
                .setRequired(true));
        add(new RequiredTextField<String>("cargo", new PropertyModel<String>(usuarioRepres, "cargo")));
    }
}
