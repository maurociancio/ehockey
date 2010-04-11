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
    private String LOCAL = "Local";
    private String VISITANTE = "Visitante";
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
        add(new Label("local", this.LOCAL));
        add(new Label("diamesaño", new Date().toString()));
        add(new Label("resultado", "Resultado"));
        add(new Label("lugar", "Lugar"));
        add(new Label("visitante", this.VISITANTE));
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
        
        add(new Label("goleadoresLocales", "Goleadores"));
        add(new Label("goleadoresVisitantes", "Goleadores"));
    }
}