package ar.noxit.ehockey.web.pages.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import ar.noxit.ehockey.model.ISancion;
import ar.noxit.ehockey.model.Tarjeta;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.models.JugadorModel;
import ar.noxit.ehockey.web.pages.providers.SancionProvider;
import ar.noxit.ehockey.web.pages.providers.TarjetasProvider;

public class JugadorReportePanel extends Panel {

    public JugadorReportePanel(String id, IJugadorService jugadorService, IModel<Integer> idJugador) {
        super(id);
        add(new Label("ficha", new PropertyModel<Integer>(new JugadorModel(idJugador, jugadorService), "ficha")));
        add(new Label("nombre", new PropertyModel<String>(new JugadorModel(idJugador, jugadorService), "nombre")));
        add(new Label("apellido", new PropertyModel<String>(new JugadorModel(idJugador, jugadorService), "apellido")));
        List<IColumn<Tarjeta>> columnasTarjeta = new ArrayList<IColumn<Tarjeta>>();
        columnasTarjeta.add(new PropertyColumn<Tarjeta>(Model.of("Tarjetas"), "tipo"));
        add(new DefaultDataTable<Tarjeta>("tarjetas", columnasTarjeta, new TarjetasProvider(jugadorService, idJugador),
                10));

        List<IColumn<ISancion>> columnasSancion = new ArrayList<IColumn<ISancion>>();
        columnasSancion.add(new PropertyColumn<ISancion>(Model.of("Sanciones"), "partidosInhabilitados.size"));
        add(new DefaultDataTable<ISancion>("sanciones", columnasSancion,
                new SancionProvider(jugadorService, idJugador), 10));
    }

    // @Override
    // public boolean isVisible() {
    // return idJugador.getObject() != null;
    // }
}
