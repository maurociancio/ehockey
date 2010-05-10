package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Planilla;
import ar.noxit.web.wicket.model.AbstractLocalDateTimeFormatModel;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class PlanillaPanel extends Panel {

    private String FEDERACION = "Federación de Hockey - FIUBA - 75.47";

    public PlanillaPanel(String id, IModel<Planilla> planillaModel) {
        super(id);

        add(new Label("federacion", this.FEDERACION));
        add(new Label("torneo", new PropertyModel<String>(planillaModel, "partido.torneo.nombre")));
        add(new Label("rueda", new PropertyModel<Integer>(planillaModel, "partido.rueda")));
        add(new Label("fecha", new PropertyModel<Integer>(planillaModel, "partido.fechaDelTorneo")));
        add(new Label("partido", new PropertyModel<Integer>(planillaModel, "partido.partido")));
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

        add(new MyLoop("filasLocales", Model.of(18), modelLocal));
        add(new MyLoop("filasVisitantes", Model.of(18), modelVisitante));
    }

    private class JugadorLocalModelItem extends
            LoadableDetachableModel<List<Jugador>> {

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

    private class JugadorVisitanteModelItem extends
            LoadableDetachableModel<List<Jugador>> {

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

    private class MyLoop extends Loop {

        private IModel<List<Jugador>> jugadores;

        public MyLoop(String id, Model<Integer> model, IModel<List<Jugador>> jugadores) {
            super(id, model);
            this.jugadores = jugadores;
        }

        @Override
        protected void populateItem(LoopItem item) {
            final Integer iteration = item.getIteration();
            item.add(new Label("fichas", new PropertyModel<Integer>(new JugadorListModel(iteration), "ficha")));
            item.add(new Label("nombres", new PropertyModel<String>(new JugadorListModel(iteration), "nombre")));
            item.add(new Label("numeros", new PropertyModel<String>(new JugadorListModel(iteration), "letraJugador")));
        }

        private final class JugadorListModel extends AbstractReadOnlyModel<Jugador> {

            private final Integer iteration;

            private JugadorListModel(Integer iteration) {
                this.iteration = iteration;
            }

            @Override
            public Jugador getObject() {
                try {
                    List<Jugador> object = jugadores.getObject();
                    return object.get(iteration);
                } catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }
        }
    }
}
