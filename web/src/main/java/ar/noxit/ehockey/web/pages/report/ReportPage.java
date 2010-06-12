package ar.noxit.ehockey.web.pages.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.ISancion;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Tarjeta;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.components.AjaxHybridSingleAndMultipleChoicePanel;
import ar.noxit.ehockey.web.pages.models.ClubModel;
import ar.noxit.ehockey.web.pages.models.ClubesListModel;
import ar.noxit.ehockey.web.pages.models.EquipoModel;
import ar.noxit.ehockey.web.pages.models.EquiposPorClubListModel;
import ar.noxit.ehockey.web.pages.models.JugadorModel;
import ar.noxit.ehockey.web.pages.models.JugadoresParaEquipoListModel;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import ar.noxit.ehockey.web.pages.renderers.EquipoRenderer;
import ar.noxit.ehockey.web.pages.renderers.JugadorRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import ar.noxit.web.wicket.provider.DataProvider;

public class ReportPage extends AbstractReportPage {

    @SpringBean
    private IJugadorService jugadorService;
    @SpringBean
    private IEquipoService equipoService;
    @SpringBean
    private IClubService clubService;
    private IModel<Integer> idJugador = new Model<Integer>();
    private IModel<Integer> idClub = new Model<Integer>();
    private IModel<Integer> idEquipo = new Model<Integer>();
    private JugadorFragment jugadorFragment;

    public ReportPage() {
        add(new JugadorSelectorPanel("selectorClubEquipoJugador", "fragment", new JugadorModel(idJugador,
                jugadorService)));
        jugadorFragment = new JugadorFragment("jugador", "jugadorFragment");
        jugadorFragment.setOutputMarkupId(true);
        add(jugadorFragment);
    }

    private class JugadorSelectorPanel extends Fragment {

        private AjaxHybridSingleAndMultipleChoicePanel<Jugador> dropDownJugador;
        private AjaxHybridSingleAndMultipleChoicePanel<Equipo> dropDownEquipo;

        public JugadorSelectorPanel(String id, String fragmentId, IModel<Jugador> jugador) {
            super(id, fragmentId, ReportPage.this);

            dropDownJugador = new AjaxHybridSingleAndMultipleChoicePanel<Jugador>("jugador", jugador,
                    new JugadoresPorEquipoListModel(idEquipo, clubService), JugadorRenderer.get()) {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                    onUpdateClub(target);
                }
            };

            dropDownJugador.setRequired(true);
            dropDownJugador.setOutputMarkupId(true);
            add(dropDownJugador);

            dropDownEquipo = new AjaxHybridSingleAndMultipleChoicePanel<Equipo>("equipo", new EquipoModel(idEquipo,
                    equipoService), new EquiposPorClubListModel(idClub, clubService), EquipoRenderer.get()) {

                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                    onUpdateEquipo(target);
                }

            };

            dropDownEquipo.setRequired(true);
            dropDownEquipo.setOutputMarkupId(true);
            add(dropDownEquipo);

            add(new AjaxHybridSingleAndMultipleChoicePanel<Club>("club", new ClubModel(idClub, clubService),
                    new ClubesListModel(clubService), new ClubRenderer()) {

                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                    onUpdateJugador(target);
                }

            });
        }

        protected void onUpdateClub(AjaxRequestTarget target) {
            idClub.setObject(null);
            target.addComponent(jugadorFragment);
        }

        protected void onUpdateEquipo(AjaxRequestTarget target) {
            idEquipo.setObject(null);
            target.addComponent(dropDownJugador);
            target.addComponent(jugadorFragment);
        }

        protected void onUpdateJugador(AjaxRequestTarget target) {
            idJugador.setObject(null);
            target.addComponent(dropDownEquipo);
            target.addComponent(dropDownJugador);
            target.addComponent(jugadorFragment);
        }

        @Override
        protected boolean getStatelessHint() {
            return false;
        }
    }

    private class JugadoresPorEquipoListModel extends JugadoresParaEquipoListModel {

        public JugadoresPorEquipoListModel(IModel<Integer> equipoId, IClubService service) {
            super(equipoId, service);
        }

        @Override
        protected List<Jugador> doLoad() throws NoxitException {
            if (idEquipo.getObject() != null)
                return super.doLoad();
            else
                return new ArrayList<Jugador>();
        }
    }

    private class JugadorFragment extends Fragment {

        public JugadorFragment(String id, String fragmentId) {
            super(id, fragmentId, ReportPage.this);

            add(new Label("ficha", new PropertyModel<Integer>(new JugadorModel(idJugador, jugadorService), "ficha")));
            add(new Label("nombre", new PropertyModel<String>(new JugadorModel(idJugador, jugadorService), "nombre")));
            add(new Label("apellido",
                    new PropertyModel<String>(new JugadorModel(idJugador, jugadorService), "apellido")));
            List<IColumn<Tarjeta>> columnasTarjeta = new ArrayList<IColumn<Tarjeta>>();
            columnasTarjeta.add(new PropertyColumn<Tarjeta>(Model.of("Tarjetas"), "tipo"));
            add(new DefaultDataTable<Tarjeta>("tarjetas", columnasTarjeta, new TarjetasProvider(), 10));

            List<IColumn<ISancion>> columnasSancion = new ArrayList<IColumn<ISancion>>();
            columnasSancion.add(new PropertyColumn<ISancion>(Model.of("Sanciones"), "partidosInhabilitados.size"));
            add(new DefaultDataTable<ISancion>("sanciones", columnasSancion, new SancionProvider(), 10));
        }

        // @Override
        // public boolean isVisible() {
        // return idJugador.getObject() != null;
        // }
    }

    private class SancionProvider extends DataProvider<ISancion> {

        @Override
        protected List<ISancion> loadList() {
            try {
                if (idJugador.getObject() == null)
                    return new ArrayList<ISancion>();
                Jugador jugador = jugadorService.get(idJugador.getObject());
                return jugador.getSanciones();
            } catch (NoxitException e) {
                return new ArrayList<ISancion>();
            }
        }

        @Override
        public IModel<ISancion> model(ISancion object) {
            final Integer id = object.getId();
            return new AbstractReadOnlyModel<ISancion>() {

                @Override
                public ISancion getObject() {
                    if (idJugador.getObject() == null) {
                        return null;
                    }
                    Jugador jugador;
                    try {
                        jugador = jugadorService.get(idJugador.getObject());
                        return jugador.getSancion(id);
                    } catch (NoxitException e) {
                        return null;
                    }
                }
            };
        }
    }

    private class TarjetasProvider extends DataProvider<Tarjeta> {

        @Override
        protected List<Tarjeta> loadList() {
            try {
                if (idJugador.getObject() == null)
                    return new ArrayList<Tarjeta>();
                Jugador jugador = jugadorService.get(idJugador.getObject());
                return jugador.getTarjetas();
            } catch (NoxitException e) {
                return new ArrayList<Tarjeta>();
            }
        }

        @Override
        public IModel<Tarjeta> model(Tarjeta object) {
            return new TarjetaModel(Model.of(object.getId()));
        }

    }

    // Puede ser un readonlymodel
    private class TarjetaModel extends IdLDM<Tarjeta, Integer> {

        public TarjetaModel(IModel<Integer> model) {
            super(model);
        }

        @Override
        protected Tarjeta doLoad(Integer id) throws NoxitException {
            Jugador jugador = jugadorService.get(idJugador.getObject());
            return jugador.getTarjeta(id);
        }

        @Override
        protected Integer getObjectId(Tarjeta object) {
            return object.getId();
        }
    }

}
