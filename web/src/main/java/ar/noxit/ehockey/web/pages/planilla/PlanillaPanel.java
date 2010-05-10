package ar.noxit.ehockey.web.pages.planilla;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Planilla;
import ar.noxit.web.wicket.model.AbstractLocalDateTimeFormatModel;

public class PlanillaPanel extends Panel {

    private String FEDERACION = "Federación de Hockey - FIUBA - 75.47";

    public PlanillaPanel(String id, IModel<Planilla> planillaModel) {
        super(id);

        add(new Label("federacion", this.FEDERACION));
        add(new Label("torneo", new PropertyModel<String>(planillaModel, "partido.torneo.nombre")));
        add(new Label("rueda", new PropertyModel<Integer>(planillaModel, "partido.rueda")));
        add(new Label("fecha", new PropertyModel<Integer>(planillaModel, "partido.fechaDelTorneo")));
        add(new Label("partido", "Partido 1"));
        // TODO GUARDAR NUMERO DE PARTIDO
        add(new Label("sector", "Sector"));
        // TODO GUARDAR EL SECTOR EN TORNEO
        add(new Label("categoria", "Campeonato"));
        add(new Label("division", "Division"));
        // TODO GUARDAR LA DIVISION EN EL TORNEO
        add(new Label("zona", ""));
        IModel<LocalDateTime> modelTime = new PropertyModel<LocalDateTime>(planillaModel, "partido.inicio");
        add(new Label("dia", new DiaAdapterModel(modelTime)));
        add(new Label("mes", new MesAdapterModel(modelTime)));
        add(new Label("año", new AnoAdapterModel(modelTime)));
        add(new Label("lugar", "Paseo Colón"));
        add(new Label("nombreLocal", new PropertyModel<String>(planillaModel, "local.nombre")));
        add(new Label("nombreVisitante", new PropertyModel<String>(planillaModel, "visitante.nombre")));

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

        private IModel<Planilla> planillaModel;

        public JugadorLocalModelItem(IModel<Planilla> planillaModel) {
            this.planillaModel = planillaModel;
        }

        @Override
        protected List<Jugador> load() {
            List<Jugador> result = new ArrayList<Jugador>();
            result.addAll(planillaModel.getObject().getJugadoresL());
            return (result);
        }
    }

    private class JugadorVisitanteModelItem extends LoadableDetachableModel<List<Jugador>> {

        private IModel<Planilla> planillaModel;

        public JugadorVisitanteModelItem(IModel<Planilla> planillaModel) {
            this.planillaModel = planillaModel;
        }

        @Override
        protected List<Jugador> load() {
            List<Jugador> result = new ArrayList<Jugador>();
            result.addAll(planillaModel.getObject().getJugadoresV());
            return result;
        }
    }

    private class DiaAdapterModel extends AbstractLocalDateTimeFormatModel {

        public DiaAdapterModel(IModel<LocalDateTime> delegate) {
            super(delegate);
        }

        @Override
        protected DateTimeFormatter getFormatter() {
            return DateTimeFormat.forPattern("dd");
        }
    }

    private class MesAdapterModel extends AbstractLocalDateTimeFormatModel {

        public MesAdapterModel(IModel<LocalDateTime> delegate) {
            super(delegate);
        }

        @Override
        protected DateTimeFormatter getFormatter() {
            return DateTimeFormat.forPattern("MM");
        }
    }

    private class AnoAdapterModel extends AbstractLocalDateTimeFormatModel {

        public AnoAdapterModel(IModel<LocalDateTime> delegate) {
            super(delegate);
        }

        @Override
        protected DateTimeFormatter getFormatter() {
            return DateTimeFormat.forPattern("YYYY");
        }
    }
}
