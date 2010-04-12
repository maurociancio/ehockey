package ar.noxit.ehockey.web.pages.planilla;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.joda.time.LocalDateTime;

public class PlanillaPanel extends Panel {

    private String FEDERACION = "Federación de Hockey - FIUBA - 75.47";

    public PlanillaPanel() {
        super("panelPlanilla");
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
        add(new Label("nombreLocal", "Belgrano"));
        add(new Label("nombreVisitante", "GEBA"));

        List<String> lista = new ArrayList<String>();
        lista.add("PEPE");
        lista.add("PEPE2");
        lista.add("PEPE3");
        add(new ListView<String>("fichasJugadoresLocales", lista) {

            @Override
            protected void populateItem(ListItem<String> item) {
                String it = item.getModelObject();
                item.add(new Label("fichasLocales", it));
            }
        });

        add(new ListView<String>("jugadoresLocales", lista) {

            @Override
            protected void populateItem(ListItem<String> item) {
                String it = item.getModelObject();
                item.add(new Label("locales", it));
            }
        });

        add(new ListView<String>("numerosJugadoresLocales", lista) {

            @Override
            protected void populateItem(ListItem<String> item) {
                String it = item.getModelObject();
                item.add(new Label("numerosLocales", it));
            }
        });
        add(new ListView<String>("fichasJugadoresVisitantes", lista) {

            @Override
            protected void populateItem(ListItem<String> item) {
                String it = item.getModelObject();
                item.add(new Label("fichasVisitantes", it));
            }
        });
        add(new ListView<String>("jugadoresVisitantes", lista) {

            @Override
            protected void populateItem(ListItem<String> item) {
                String it = item.getModelObject();
                item.add(new Label("visitantes", it));
            }
        });
        add(new ListView<String>("numerosJugadoresVisitantes", lista) {

            @Override
            protected void populateItem(ListItem<String> item) {
                String it = item.getModelObject();
                item.add(new Label("numerosVisitantes", it));
            }
        });
    }

}