package ar.noxit.ehockey.web.pages.planilla;

import java.util.Date;

import org.apache.wicket.markup.html.basic.Label;

import ar.noxit.ehockey.web.pages.AbstractContentPage;

public class PlanillaPage extends AbstractContentPage {

    private String FEDERACION = "Federación de Hockey - FIUBA - 75.47";
    private String LOCAL = "Local";
    private String VISITANTE = "Visitante";

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
    }
}