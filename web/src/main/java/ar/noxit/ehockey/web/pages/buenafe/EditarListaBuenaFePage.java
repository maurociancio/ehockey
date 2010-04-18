package ar.noxit.ehockey.web.pages.buenafe;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.ListaBuenaFe;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.models.TodosEquiposModel;
import ar.noxit.ehockey.web.pages.renderers.EquipoRenderer;
import ar.noxit.ehockey.web.pages.renderers.JugadorRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.model.IdLDM;
import ar.noxit.web.wicket.model.LDM;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class EditarListaBuenaFePage extends AbstractContentPage {

    @SpringBean
    private IClubService clubService;
    @SpringBean
    private IEquiposService equiposService;
    private Integer equipoId;
    private Integer clubId;
    private List<Integer> seleccionados = new ArrayList<Integer>();

    public EditarListaBuenaFePage() {
        // editar lista
        final Form<Void> formInclusion = new Form<Void>("inclusionForm") {

            @Override
            protected void onSubmit() {
                try {
                    equiposService.asignarListaBuenaFe(equipoId, seleccionados);
                } catch (NoxitException e) {
                    throw new NoxitRuntimeException(e);
                }
            }
        };

        formInclusion.add(new Palette<Jugador>("palette",
                new JugadoresSeleccionadosModel(),
                new TodosJugadoresPorClubModel(),
                JugadorRenderer.get(),
                10,
                false));

        formInclusion.setVisible(false);
        add(formInclusion);

        // seleccionar club
        final IModel<Equipo> equipoSeleccionado = new SelectedEquipoModel(new PropertyModel<Integer>(this, "equipoId"));
        Form<Equipo> form = new Form<Equipo>("form") {

            @Override
            protected void onSubmit() {
                formInclusion.setVisible(true);

                seleccionados.clear();
                ListaBuenaFe listaBuenaFe = equipoSeleccionado.getObject().getListaBuenaFe();
                Iterator<Jugador> it = listaBuenaFe.iterator();
                while (it.hasNext()) {
                    seleccionados.add(it.next().getFicha());
                }
            }
        };

        form.add(new DropDownChoice<Equipo>("equipos",
                equipoSeleccionado,
                new TodosEquiposModel(equiposService),
                EquipoRenderer.get())
                .setRequired(true));

        add(form);
    }

    private final class JugadoresSeleccionadosModel implements IModel<List<Jugador>> {

        @Override
        public List<Jugador> getObject() {
            try {
                Equipo equipo = equiposService.get(equipoId);
                return clubService.getJugadoresPorClub(equipo.getClub().getId(), seleccionados);
            } catch (NoxitException e) {
                throw new NoxitRuntimeException(e);
            }
        }

        @Override
        public void setObject(List<Jugador> object) {
            seleccionados.clear();
            for (Jugador jugador : object) {
                seleccionados.add(jugador.getFicha());
            }
        }

        @Override
        public void detach() {
        }
    }

    private final class SelectedEquipoModel extends IdLDM<Equipo, Integer> {

        private SelectedEquipoModel(IModel<Integer> idModel) {
            super(idModel);
        }

        @Override
        public void setObject(Equipo object) {
            super.setObject(object);
            if (object != null) {
                clubId = object.getClub().getId();
            } else {
                clubId = null;
            }
        }

        @Override
        protected Equipo doLoad(Integer id) throws NoxitException {
            return equiposService.get(id);
        }

        @Override
        protected Integer getObjectId(Equipo equipo) {
            if (equipo == null) {
                return null;
            }
            return equipo.getId();
        }
    }

    private class TodosJugadoresPorClubModel extends LDM<List<Jugador>> {

        @Override
        protected List<Jugador> doLoad() throws NoxitException {
            return clubService.getJugadoresPorClub(clubId);
        }
    }
}
