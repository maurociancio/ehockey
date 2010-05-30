package ar.noxit.ehockey.web.pages.tablaposiciones;

import ar.noxit.ehockey.model.DatosTabla;
import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.service.ITablaPosicionesService;
import ar.noxit.ehockey.service.ITorneoService;
import ar.noxit.ehockey.web.pages.components.AjaxHybridSingleAndMultipleChoicePanel;
import ar.noxit.ehockey.web.pages.models.TorneoListModel;
import ar.noxit.ehockey.web.pages.models.TorneoModel;
import ar.noxit.ehockey.web.pages.providers.TablaPosicionesDataProvider;
import ar.noxit.ehockey.web.pages.renderers.TorneoRenderer;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TablaPosicionesPanel extends Panel {

    @SpringBean
    private ITorneoService torneoService;
    @SpringBean
    private ITablaPosicionesService tablaService;
    private TablaPosicionesDataProvider tablaPosicionesDataProvider;
    private WebMarkupContainer contenedorTabla;

    public TablaPosicionesPanel() {
        this(Model.of(new TablaTransfer()));
    }

    public TablaPosicionesPanel(final IModel<TablaTransfer> tablaTransferModel) {
        super("formulariopanel");

        List<IColumn<DatosTabla>> columnas = new ArrayList<IColumn<DatosTabla>>();

        IModel<Torneo> torneoModel = new TorneoModel(new PropertyModel<Integer>(tablaTransferModel, "torneoId"),
                torneoService);

        columnas.add(new PropertyColumn<DatosTabla>(Model.of("Nombre"), "nombre"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PTS"), "puntos"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PJ"), "partidosJugados"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PG"), "partidosGanados"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PE"), "partidosEmpatados"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PP"), "partidosPerdidos"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("GF"), "golesFavor"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("GC"), "golesContra"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("DG"), "diferenciaGol"));

        contenedorTabla = new WebMarkupContainer("contenedortabla");
        contenedorTabla.setOutputMarkupId(true);

        tablaPosicionesDataProvider = new TablaPosicionesDataProvider(tablaService, torneoModel);
        DataTable<DatosTabla> tabla = new AjaxFallbackDefaultDataTable<DatosTabla>("tablaposiciones", columnas,
                tablaPosicionesDataProvider, 10) {

            @Override
            public boolean isVisible() {
                return !(tablaPosicionesDataProvider.size() == 0);
            }
        };
        contenedorTabla.add(tabla);
        add(contenedorTabla);

        add(new AjaxHybridSingleAndMultipleChoicePanel<Torneo>("panelhibrido",
                torneoModel,
                new TorneoListModel(torneoService),
                new TorneoRenderer()) {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.addComponent(contenedorTabla);
            }
        });
    }
}
