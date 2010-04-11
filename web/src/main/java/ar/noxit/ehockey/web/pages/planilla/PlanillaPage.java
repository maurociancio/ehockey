package ar.noxit.ehockey.web.pages.planilla;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.ehockey.web.pages.AbstractContentPage;

public class PlanillaPage extends AbstractContentPage {

    private String FEDERACION = "Federación de Hockey - FIUBA - 75.47";
    @SpringBean
    private IEquiposService equipoService;

    public PlanillaPage() {
        super();
        add(new Label("federacion", this.FEDERACION));
        add(new Label("torneo", "Torneo"));
        add(new Label("rueda", "Rueda"));
        add(new Label("fecha", "Fecha"));
        add(new Label("partido", "Partido"));
        add(new Label("sector", "Sector"));
        add(new Label("categoria", "Categoria"));
        add(new Label("division", "Division"));
        add(new Label("zona", "Zona"));
        add(new Label("diamesaño", new Date().toString()));
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