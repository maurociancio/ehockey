package ar.noxit.ehockey.web.pages;

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

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import ar.noxit.web.wicket.model.LDM;

public class EditarListaBuenaFePage extends AbstractContentPage {

    private static final IChoiceRenderer<Jugador> JUGADORRENDERER = new JugadorRenderer();
    private static final IChoiceRenderer<Equipo> EQUIPORENDERER = new EquipoRenderer();
    @SpringBean
    private IClubService clubService;
    @SpringBean
    private IEquiposService equiposService;
    
    private Integer equipoId;

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
                Equipo equipo;
                try {
                    equipo = equiposService.get(equipoId);
                    return clubService.getJugadoresPorClub(equipo.getClub().getId(), seleccionados);
                } catch (NoxitException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
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

        IModel<Equipo> equipoModel = new SelectedEquipoModel(new PropertyModel<Integer>(this, "equipoId"));
        form.add(new DropDownChoice<Equipo>("equipos",
                equipoModel,
                new TodosEquiposModel(),
                EQUIPORENDERER)
                .setRequired(true));

        add(form);
    }

    private class TodosEquiposModel extends LDM<List<Equipo>> {

        @Override
        protected List<Equipo> doLoad() throws NoxitException {
            return equiposService.getAll();
        }
    }

    private final class SelectedEquipoModel extends IdLDM<Equipo, Integer> {

        private SelectedEquipoModel(IModel<Integer> idModel) {
            super(idModel);
        }

        @Override
        protected Equipo doLoad(Integer id) throws NoxitException {
            return equiposService.get(id);
        }

        @Override
        protected Integer getObjectId(Equipo equipo) {
            return equipo.getId();
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

    private static class EquipoRenderer implements IChoiceRenderer<Equipo> {

        @Override
        public Object getDisplayValue(Equipo object) {
            return object.getNombre();
        }

        @Override
        public String getIdValue(Equipo object, int index) {
            return object.getId().toString();
        }
    }
}
