package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.models.JugadorIdModel;
import ar.noxit.ehockey.web.pages.models.TodosJugadoresPorClubModel;
import ar.noxit.ehockey.web.pages.renderers.JugadorRenderer;
import ar.noxit.ehockey.web.pages.torneo.NuevaAmonestacionPage;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.column.AbstractLabelColumn;
import ar.noxit.web.wicket.model.AdapterModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PlanillaEquipoPanel extends Panel {

    @SpringBean
    private IClubService clubService;
    @SpringBean
    private IJugadorService jugadorService;
    private List<AmonestacionInfo> amonestaciones = new ArrayList<AmonestacionInfo>();

    public PlanillaEquipoPanel(String id, IModel<Equipo> equipo, IModel<EquipoInfo> info) {
        super(id);

        final IModel<List<Jugador>> jugadoresModel = new TodosJugadoresPorClubModel(
                new PropertyModel<Integer>(equipo, "club.id"), clubService);

        add(new Palette<Jugador>("palette",
                new JugadoresSeleccionadosModel(info, equipo),
                jugadoresModel,
                JugadorRenderer.get(),
                10,
                false));

        add(new RequiredTextField<String>("goleadores", new PropertyModel<String>(info, "goleadores")));
        add(new RequiredTextField<String>("dt", new PropertyModel<String>(info, "dt")));
        add(new RequiredTextField<String>("pf", new PropertyModel<String>(info, "pf")));
        add(new RequiredTextField<String>("medico", new PropertyModel<String>(info, "medico")));
        add(new RequiredTextField<String>("juezmesa", new PropertyModel<String>(info, "juezMesa")));
        add(new RequiredTextField<String>("arbitro", new PropertyModel<String>(info, "arbitro")));

        List<IColumn<AmonestacionInfo>> columns = new ArrayList<IColumn<AmonestacionInfo>>();
        columns.add(new AbstractLabelColumn<AmonestacionInfo>(Model.of("Nombre y Apellido")) {

            @Override
            protected IModel<String> createDisplayModel(IModel<AmonestacionInfo> rowModel) {
                IModel<Integer> id = new PropertyModel<Integer>(rowModel, "jugadorId");
                return new PropertyModel<String>(new JugadorIdModel(jugadorService, id), "apellido");
            }
        });
        columns.add(new PropertyColumn<AmonestacionInfo>(Model.of("Rojas"), "rojas"));
        columns.add(new PropertyColumn<AmonestacionInfo>(Model.of("Amarillas"), "amarillas"));
        columns.add(new PropertyColumn<AmonestacionInfo>(Model.of("Verdes"), "verdes"));
        final DefaultDataTable<AmonestacionInfo> amonestacionesTable = new DefaultDataTable<AmonestacionInfo>(
                "amonestaciones",
                        columns,
                        new ListDataProvider(),
                        25);
        amonestacionesTable.setOutputMarkupId(true);
        add(amonestacionesTable);

        final ModalWindow modal = new ModalWindow("modal");
        modal.setPageMapName("amonestaciones");
        modal.setCookieName("amonestaciones");
        modal.setWindowClosedCallback(new WindowClosedCallback() {

            @Override
            public void onClose(AjaxRequestTarget target) {
                target.addComponent(amonestacionesTable);
            }
        });
        add(modal);

        add(new AjaxLink<Void>("nueva_amonestacion") {

            @Override
            public void onClick(AjaxRequestTarget target) {
                modal.setPageCreator(new PageCreator() {

                    @Override
                    public Page createPage() {
                        return new NuevaAmonestacionPage(jugadoresModel, modal) {

                            @Override
                            protected void onSubmit(AmonestacionInfo amonestacionInfo) {
                                amonestaciones.add(amonestacionInfo);
                            }
                        };
                    }
                });
                modal.show(target);
            }
        });
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

    private class ListDataProvider extends SortableDataProvider<AmonestacionInfo> {

        @Override
        public Iterator<? extends AmonestacionInfo> iterator(int first, int count) {
            int toIndex = first + count;
            if (toIndex > amonestaciones.size()) {
                toIndex = amonestaciones.size();
            }
            return amonestaciones.subList(first, toIndex).listIterator();
        }

        @Override
        public IModel<AmonestacionInfo> model(AmonestacionInfo object) {
            return Model.of(object);
        }

        @Override
        public int size() {
            return amonestaciones.size();
        }
    }
}
