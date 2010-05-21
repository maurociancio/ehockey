package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Representante;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.models.ClubListModel;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;

public class UsuarioRepresentantePanel extends Panel {

    @SpringBean
    IClubService clubService;

    public UsuarioRepresentantePanel(String id, IModel<Representante> usuarioRepres) {
        super(id);

        ClubModel club = new ClubModel(new Model<Integer>());
        add(new DropDownChoice<Club>("clubes", club, new ClubListModel(clubService), new ClubRenderer()).setRequired(true));
        add(new RequiredTextField<String>("cargo", new PropertyModel<String>(usuarioRepres, "cargo")));
    }

    public class ClubModel extends IdLDM<Club, Integer> {

        public ClubModel(IModel<Integer> idModel) {
            super(idModel);
        }

        @Override
        protected Club doLoad(Integer id) throws NoxitException {
            return clubService.get(id);
        }

        @Override
        protected Integer getObjectId(Club club) {
            return club.getId();
        }

    }
}
