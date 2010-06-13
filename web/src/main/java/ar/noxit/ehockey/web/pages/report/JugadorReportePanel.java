package ar.noxit.ehockey.web.pages.report;

import ar.noxit.ehockey.model.ISancion;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Tarjeta;
import ar.noxit.ehockey.web.pages.providers.SancionProvider;
import ar.noxit.ehockey.web.pages.providers.TarjetasProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class JugadorReportePanel extends Panel {

    public JugadorReportePanel(String id, final IModel<Jugador> jugadorModel) {
        super(id);
        add(new Label("ficha", new PropertyModel<Integer>(jugadorModel, "ficha")));
        add(new Label("nombre", new PropertyModel<String>(jugadorModel, "nombre")));
        add(new Label("apellido", new PropertyModel<String>(jugadorModel, "apellido")));
        add(new Label("tipodoc", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                if (jugadorModel.getObject() != null)
                    return jugadorModel.getObject().getTipoDocumento();
                return null;
            }
        }));
        add(new Label("nrodoc", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                if (jugadorModel.getObject() != null)
                    return jugadorModel.getObject().getDocumento();
                return null;
            }
        }));
        add(new Label("fechanac", new PropertyModel<String>(jugadorModel, "fechaNacimiento")));
        add(new Label("telefono", new PropertyModel<String>(jugadorModel, "telefono")));
        add(new Label("fechaalta", new PropertyModel<String>(jugadorModel, "fechaAlta")));
        add(new Label("club", new PropertyModel<String>(jugadorModel, "club")));
        add(new Label("division", new PropertyModel<String>(jugadorModel, "division")));
        add(new Label("sector", new PropertyModel<String>(jugadorModel, "sector")));

        List<IColumn<Tarjeta>> columnasTarjeta = new ArrayList<IColumn<Tarjeta>>();
        columnasTarjeta.add(new PropertyColumn<Tarjeta>(Model.of("Tarjetas"), "tipo"));
        columnasTarjeta.add(new PropertyColumn<Tarjeta>(Model.of("Partido"), "partido"));
        add(new DefaultDataTable<Tarjeta>("tarjetas", columnasTarjeta, new TarjetasProvider(jugadorModel), 100));

        List<IColumn<ISancion>> columnasSancion = new ArrayList<IColumn<ISancion>>();
        columnasSancion.add(new PropertyColumn<ISancion>(Model.of("Sanciones"), "partidosInhabilitados.size"));
        columnasSancion.add(new PropertyColumn<ISancion>(Model.of("Partidos Inhabilitado"), "partidosInhabilitados"));
        add(new DefaultDataTable<ISancion>("sanciones", columnasSancion, new SancionProvider(jugadorModel), 100));
    }
    // @Override
    // public boolean isVisible() {
    // return idJugador.getObject() != null;
    // }
}
