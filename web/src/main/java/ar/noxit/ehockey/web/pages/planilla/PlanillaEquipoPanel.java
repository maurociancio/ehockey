package ar.noxit.ehockey.web.pages.planilla;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Planilla;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.models.TodosJugadoresPorClubModel;
import ar.noxit.ehockey.web.pages.renderers.JugadorRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public class PlanillaEquipoPanel extends FormComponentPanel {

    @SpringBean
    private IClubService clubService;
    private IModel<Equipo> equipo;
    private List<Integer> seleccionados = new ArrayList<Integer>();

    public PlanillaEquipoPanel(String id, IModel<Equipo> equipo, IModel<Planilla> planilla, IModel<EquipoInfo> info) {
        super(id);
        this.equipo = equipo;
        add(new Palette<Jugador>("palette", new JugadoresSeleccionadosModel(planilla), new TodosJugadoresPorClubModel(
                new PropertyModel<Integer>(equipo, "club.id"), clubService), JugadorRenderer.get(), 10, false));

        add(new RequiredTextField<String>("goleadores", new PropertyModel<String>(info, "goleadores")));
        add(new RequiredTextField<String>("dt", new PropertyModel<String>(info, "dt")));
        add(new RequiredTextField<String>("pf", new PropertyModel<String>(info, "pf")));
        add(new RequiredTextField<String>("medico", new PropertyModel<String>(info, "medico")));
        add(new RequiredTextField<String>("juezmesa", new PropertyModel<String>(info, "juezMesa")));
        add(new RequiredTextField<String>("arbitro", new PropertyModel<String>(info, "arbitro")));
    }

    private final class JugadoresSeleccionadosModel implements IModel<List<Jugador>> {

        private boolean loaded = false;
        private IModel<Planilla> planilla;

        public JugadoresSeleccionadosModel(IModel<Planilla> planilla) {
            this.planilla = planilla;
        }

        @Override
        public List<Jugador> getObject() {
            Equipo equipoObj = equipo.getObject();
            if (loaded == false) {
                Planilla planillaEquipo = planilla.getObject();

                List<Jugador> jugadores = null;
                if (planillaEquipo.getLocal().equals(equipoObj)) {
                    jugadores = planillaEquipo.getJugadoresL();
                } else {
                    jugadores = planillaEquipo.getJugadoresV();
                }
                setObject(jugadores);
                loaded = true;
            }

            try {
                return clubService.getJugadoresPorClub(equipoObj.getClub().getId(), seleccionados);
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
