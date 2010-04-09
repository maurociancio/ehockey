package ar.noxit.ehockey.web.pages;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import ar.noxit.web.wicket.model.LDM;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EditarListaBuenaFePage extends AbstractContentPage {

    private static final IChoiceRenderer<Jugador> JUGADORRENDERER = new JugadorRenderer();
    private static final IChoiceRenderer<Club> CLUBRENDERER = new ClubRenderer();
    @SpringBean
    private IClubService clubService;
    private Integer clubId;

    public EditarListaBuenaFePage() {
        // editar lista
        final Form<Void> formInclusion = new Form<Void>("inclusionForm") {

            @Override
            protected void onSubmit() {
            }
        };

        IModel<List<Jugador>> j = new IModel<List<Jugador>>() {

            private List<Integer> seleccionados = new ArrayList<Integer>();

            @Override
            public List<Jugador> getObject() {
                return clubService.getJugadoresPorClub(clubId, seleccionados);
            }

            @Override
            public void setObject(List<Jugador> object) {
                seleccionados.clear();
                for (Jugador j : object) {
                    seleccionados.add(j.getFicha());
                }
            }

            @Override
            public void detach() {
            }
        };

        formInclusion.add(new Palette<Jugador>("palette",
                j,
                new TodosJugadoresPorClubModel(),
                JUGADORRENDERER,
                10,
                false));

        formInclusion.setVisible(false);
        add(formInclusion);

        // seleccionar club
        Form<Void> form = new Form<Void>("form") {

            @Override
            protected void onSubmit() {
                formInclusion.setVisible(true);
            }
        };

        IModel<Club> clubModel = new SelectedClubModel(new PropertyModel<Integer>(this, "clubId"));
        form.add(new DropDownChoice<Club>("clubes",
                clubModel,
                new TodosClubesModel(),
                CLUBRENDERER)
                .setRequired(true));

        add(form);
    }

    private class TodosClubesModel extends LDM<List<Club>> {

        @Override
        protected List<Club> doLoad() throws NoxitException {
            return clubService.getAll();
        }
    }

    private final class SelectedClubModel extends IdLDM<Club, Integer> {

        private SelectedClubModel(IModel<Integer> idModel) {
            super(idModel);
        }

        @Override
        protected Club doLoad(Integer id) throws NoxitException {
            return clubService.getClub(id);
        }

        @Override
        protected Integer getObjectId(Club club) {
            return club.getId();
        }
    }

    private class TodosJugadoresPorClubModel extends LoadableDetachableModel<List<Jugador>> {

        @Override
        protected List<Jugador> load() {
            return clubService.getJugadoresPorClub(1);
        }
    }

    private static class JugadorRenderer implements IChoiceRenderer<Jugador> {

        @Override
        public Object getDisplayValue(Jugador object) {
            return object.getApellido() + " " + object.getNombre();
        }

        @Override
        public String getIdValue(Jugador object, int index) {
            return object.getFicha().toString();
        }
    }

    private static class ClubRenderer implements IChoiceRenderer<Club> {

        @Override
        public Object getDisplayValue(Club object) {
            return object.getNombre();
        }

        @Override
        public String getIdValue(Club object, int index) {
            return object.getId().toString();
        }
    }
}
