package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Planilla;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.joda.time.LocalDateTime;

public class PlanillaPanel extends Panel {

    private String FEDERACION = "Federación de Hockey - FIUBA - 75.47";

    public PlanillaPanel(String id, IModel<? extends Planilla> planillaModel) {
        super(id);

        add(new Label("federacion", this.FEDERACION));
        add(new Label("torneo", "Torneo"));
        add(new Label("rueda", "Rueda"));
        add(new Label("fecha", "Fecha"));
        add(new Label("partido", "Partido"));
        add(new Label("sector", "Sector"));
        add(new Label("categoria", "Categoria"));
        add(new Label("division", "Division"));
        add(new Label("zona", "Zona"));
        add(new Label("dia", new LocalDateTime().dayOfMonth().getAsShortText()));
        add(new Label("mes", new LocalDateTime().monthOfYear().getAsShortText()));
        add(new Label("año", new LocalDateTime().year().getAsShortText()));
        add(new Label("lugar", "Paseo Colón"));
        add(new Label("nombreLocal", planillaModel.getObject().getLocal().getNombre()));
        add(new Label("nombreVisitante", planillaModel.getObject().getVisitante().getNombre()));

        IModel<List<Jugador>> modelLocal = new JugadorLocalModelItem(planillaModel);
        IModel<List<Jugador>> modelVisitante = new JugadorVisitanteModelItem(planillaModel);
        add(new ListView<Jugador>("fichasJugadoresLocales", modelLocal) {

            @Override
            protected void populateItem(ListItem<Jugador> item) {
                item.add(new Label("fichasLocales", new PropertyModel<Integer>(item.getModel(), "ficha")));
            }
        });

        add(new ListView<Jugador>("jugadoresLocales", modelLocal) {

            @Override
            protected void populateItem(ListItem<Jugador> item) {
                item.add(new Label("locales", new PropertyModel<String>(item.getModel(), "nombre")));
            }
        });

        add(new ListView<Jugador>("numerosJugadoresLocales", modelLocal) {

            @Override
            protected void populateItem(ListItem<Jugador> item) {
                item.add(new Label("numerosLocales", new PropertyModel<String>(item.getModel(), "letraJugador")));
            }
        });

        add(new ListView<Jugador>("fichasJugadoresVisitantes", modelVisitante) {

            @Override
            protected void populateItem(ListItem<Jugador> item) {
                item.add(new Label("fichasVisitantes", new PropertyModel<Integer>(item.getModel(), "ficha")));
            }
        });

        add(new ListView<Jugador>("jugadoresVisitantes", modelVisitante) {

            @Override
            protected void populateItem(ListItem<Jugador> item) {
                item.add(new Label("visitantes", new PropertyModel<String>(item.getModel(), "nombre")));
            }
        });

        add(new ListView<Jugador>("numerosJugadoresVisitantes", modelVisitante) {

            @Override
            protected void populateItem(ListItem<Jugador> item) {
                item.add(new Label("numerosVisitantes", new PropertyModel<String>(item.getModel(), "letraJugador")));
            }
        });
    }

    private class JugadorLocalModelItem extends LoadableDetachableModel<List<Jugador>> {

        private IModel<? extends Planilla> planillaModel;

        public JugadorLocalModelItem(IModel<? extends Planilla> planillaModel) {
            this.planillaModel = planillaModel;
        }

        @Override
        protected List<Jugador> load() {
            return planillaModel.getObject().getJugadoresL();
        }
    }

    private class JugadorVisitanteModelItem extends LoadableDetachableModel<List<Jugador>> {

        private IModel<? extends Planilla> planillaModel;

        public JugadorVisitanteModelItem(IModel<? extends Planilla> planillaModel) {
            this.planillaModel = planillaModel;
        }

        @Override
        protected List<Jugador> load() {
            return planillaModel.getObject().getJugadoresV();
        }
    }
}
