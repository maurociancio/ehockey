package ar.noxit.ehockey.web.pages.planilla;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.joda.time.LocalDateTime;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Planilla;

public class PlanillaPanel extends Panel {

    private String FEDERACION = "Federación de Hockey - FIUBA - 75.47";

    public PlanillaPanel(IModel<? extends Planilla> planillaModel) {
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
        add(new Label("nombreLocal", planillaModel.getObject().getLocal().getNombre()));
        add(new Label("nombreVisitante", planillaModel.getObject().getVisitante().getNombre()));

        List<String> lista = new ArrayList<String>();
        Iterator<Jugador> it = planillaModel.getObject().getJugadoresL().iterator();
        while (it.hasNext()) {
            lista.add(it.next().getFicha().toString());
        }
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
