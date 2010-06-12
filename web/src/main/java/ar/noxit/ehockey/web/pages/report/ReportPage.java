package ar.noxit.ehockey.web.pages.report;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.components.AjaxHybridSingleAndMultipleChoicePanel;
import ar.noxit.ehockey.web.pages.models.ClubModel;
import ar.noxit.ehockey.web.pages.models.ClubesListModel;
import ar.noxit.ehockey.web.pages.models.EquipoModel;
import ar.noxit.ehockey.web.pages.models.EquiposPorClubListModel;
import ar.noxit.ehockey.web.pages.models.JugadorModel;
import ar.noxit.ehockey.web.pages.models.JugadoresPorEquipoListModel;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import ar.noxit.ehockey.web.pages.renderers.EquipoRenderer;
import ar.noxit.ehockey.web.pages.renderers.JugadorRenderer;

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
    private JugadorReportePanel jugadorPanel;

    public ReportPage() {
        add(new JugadorSelectorPanel("selectorClubEquipoJugador", "fragment", new JugadorModel(idJugador,
                jugadorService)));
        jugadorPanel = new JugadorReportePanel("jugadorpanel", jugadorService, idJugador);
        jugadorPanel.setOutputMarkupId(true);
        add(jugadorPanel);
    }

    private class JugadorSelectorPanel extends Fragment {

        private AjaxHybridSingleAndMultipleChoicePanel<Jugador> dropDownJugador;
        private AjaxHybridSingleAndMultipleChoicePanel<Equipo> dropDownEquipo;

        public JugadorSelectorPanel(String id, String fragmentId, IModel<Jugador> jugador) {
            super(id, fragmentId, ReportPage.this);

            add(new Link<Void>("htmlreporte") {

                @Override
                public void onClick() {
                    setResponsePage(new ReportePrinterFriendly());
                }
            });

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
            target.addComponent(jugadorPanel);
        }

        protected void onUpdateEquipo(AjaxRequestTarget target) {
            idEquipo.setObject(null);
            target.addComponent(dropDownJugador);
            target.addComponent(jugadorPanel);
        }

        protected void onUpdateJugador(AjaxRequestTarget target) {
            idJugador.setObject(null);
            target.addComponent(dropDownEquipo);
            target.addComponent(dropDownJugador);
            target.addComponent(jugadorPanel);
        }

        @Override
        protected boolean getStatelessHint() {
            return false;
        }
    }

}
