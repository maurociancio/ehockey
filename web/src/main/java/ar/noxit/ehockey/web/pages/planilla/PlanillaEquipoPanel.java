package ar.noxit.ehockey.web.pages.planilla;

import java.util.List;

import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.web.pages.models.TodosJugadoresPorClubModel;
import ar.noxit.ehockey.web.pages.renderers.JugadorRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.model.AdapterModel;

public class PlanillaEquipoPanel extends Panel {

    @SpringBean
    private IClubService clubService;

    public PlanillaEquipoPanel(String id, IModel<Equipo> equipo, IModel<EquipoInfo> info) {
        super(id);

        add(new Palette<Jugador>("palette", new JugadoresSeleccionadosModel(info, equipo), new TodosJugadoresPorClubModel(
                new PropertyModel<Integer>(equipo, "club.id"), clubService), JugadorRenderer.get(), 10, false));

        add(new RequiredTextField<String>("goleadores", new PropertyModel<String>(info, "goleadores")));
        add(new RequiredTextField<String>("dt", new PropertyModel<String>(info, "dt")));
        add(new RequiredTextField<String>("pf", new PropertyModel<String>(info, "pf")));
        add(new RequiredTextField<String>("medico", new PropertyModel<String>(info, "medico")));
        add(new RequiredTextField<String>("juezmesa", new PropertyModel<String>(info, "juezMesa")));
        add(new RequiredTextField<String>("arbitro", new PropertyModel<String>(info, "arbitro")));
    }

    private class JugadoresSeleccionadosModel extends AdapterModel<List<Jugador>, EquipoInfo> {
        private Integer clubId;

        public JugadoresSeleccionadosModel(IModel<EquipoInfo> delegate, IModel<Equipo> equipo) {
            super(delegate);
            this.clubId = equipo.getObject().getClub().getId();
        }

        @Override
        protected List<Jugador> getObject(IModel<EquipoInfo> equipoInfo) {
            try {
                return clubService.getJugadoresPorClub(this.clubId, equipoInfo.getObject().getSeleccionados());
            } catch (NoxitException e) {
                throw new NoxitRuntimeException(e);
            }
        }

        @Override
        protected void setObject(List<Jugador> jugadores, IModel<EquipoInfo> equipoInfo) {
            List<Integer> seleccionados = equipoInfo.getObject().getSeleccionados();
            seleccionados.clear();
            for (Jugador j : jugadores) {
                seleccionados.add(j.getFicha());
            }
        }
        
    }
}
