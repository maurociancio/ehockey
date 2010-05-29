package ar.noxit.ehockey.web.pages.buenafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.ListaBuenaFe;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.web.pages.components.EquipoSelectorPanel;
import ar.noxit.ehockey.web.pages.models.EquipoModel;
import ar.noxit.ehockey.web.pages.models.TodosJugadoresParaEquipoModel;
import ar.noxit.ehockey.web.pages.renderers.JugadorRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class EditarListaBuenaFePage extends AbstractListaBuenaFePage {

    @SpringBean
    private IClubService clubService;
    @SpringBean
    private IEquipoService equipoService;
    private Integer equipoId;
    private Integer clubId;
    private List<Integer> seleccionados = new ArrayList<Integer>();

    public EditarListaBuenaFePage() {
        // editar lista
        final Form<Void> formInclusion = new Form<Void>("inclusionForm") {

            @Override
            protected void onSubmit() {
                try {
                    equipoService.asignarListaBuenaFe(equipoId, seleccionados);
                } catch (NoxitException e) {
                    throw new NoxitRuntimeException(e);
                }
            }
        };

        formInclusion.add(new Palette<Jugador>("palette",
                new JugadoresSeleccionadosModel(),
                new TodosJugadoresParaEquipoModel(new PropertyModel<Integer>(this, "equipoId"), clubService),
                JugadorRenderer.get(),
                10,
                false));

        formInclusion.setVisible(false);
        add(formInclusion);

        // seleccionar club
        final IModel<Equipo> equipoSeleccionado = new EquipoModel(new PropertyModel<Integer>(this, "equipoId"),
                equipoService);
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

        form.add(new EquipoSelectorPanel("equipo", equipoSeleccionado));

        add(form);
    }

    private final class JugadoresSeleccionadosModel implements IModel<List<Jugador>> {

        @Override
        public List<Jugador> getObject() {
            try {
                Equipo equipo = equipoService.get(equipoId);
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
}
