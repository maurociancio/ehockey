package ar.noxit.ehockey.web.pages.tablaposiciones;

import ar.noxit.ehockey.model.DatosTabla;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.ehockey.service.ITablaPosicionesService;
import ar.noxit.ehockey.service.ITorneoService;
import ar.noxit.ehockey.web.pages.models.DivisionListModel;
import ar.noxit.ehockey.web.pages.models.IdDivisionModel;
import ar.noxit.ehockey.web.pages.models.IdSectorModel;
import ar.noxit.ehockey.web.pages.models.IdTorneoModel;
import ar.noxit.ehockey.web.pages.models.SectorListModel;
import ar.noxit.ehockey.web.pages.models.TorneoListModel;
import ar.noxit.ehockey.web.pages.providers.TablaPosicionesDataProvider;
import ar.noxit.ehockey.web.pages.renderers.DivisionRenderer;
import ar.noxit.ehockey.web.pages.renderers.SectorRenderer;
import ar.noxit.ehockey.web.pages.renderers.TorneoRenderer;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public abstract class TablaPosicionesPanel extends Panel {

    @SpringBean
    private IDivisionService divisionService;
    @SpringBean
    private ISectorService sectorService;
    @SpringBean
    private ITorneoService torneoService;
    @SpringBean
    private ITablaPosicionesService tablaService;

    public TablaPosicionesPanel(final IModel<TablaTransfer> tablaTransferModel) {
        super("formulariopanel");

        Form<DatosTabla> form = new Form<DatosTabla>("formulario") {

            @Override
            protected void onSubmit() {
                TablaPosicionesPanel.this.onSubmit(tablaTransferModel);
            }
        };
        form.add(new DropDownChoice<Torneo>("torneos", new IdTorneoModel(
                new PropertyModel<Integer>(tablaTransferModel, "torneoId"),
                torneoService), new TorneoListModel(torneoService),
                new TorneoRenderer()));

        form.add(new DropDownChoice<Division>("division", new IdDivisionModel(
                new PropertyModel<Integer>(tablaTransferModel, "divisionId"),
                divisionService), new DivisionListModel(divisionService),
                new DivisionRenderer()));

        form.add(new DropDownChoice<Sector>("sector", new IdSectorModel(
                new PropertyModel<Integer>(tablaTransferModel, "sectorId"),
                sectorService), new SectorListModel(sectorService),
                new SectorRenderer()));

        List<IColumn<DatosTabla>> columnas = new ArrayList<IColumn<DatosTabla>>();

        columnas.add(new PropertyColumn<DatosTabla>(Model.of("Nombre"), "nombre"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PTS"), "puntos"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PJ"), "partidosJugados"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PG"), "partidosGanados"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PE"), "partidosEmpatados"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PP"), "partidosPerdidos"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("GF"), "golesFavor"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("GC"), "golesContra"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("DG"), "diferenciaGol"));

        DataTable<DatosTabla> tabla = new DataTable<DatosTabla>(
                "tablaposiciones", columnas.toArray(new IColumn[columnas.size()]),
                new TablaPosicionesDataProvider(tablaService)
                .setTorneoId(tablaTransferModel.getObject().getTorneoId())
                .setDivisionId(tablaTransferModel.getObject().getDivisionId())
                .setSectorId(tablaTransferModel.getObject().getSectorId()),
                10);
        form.add(tabla);
        add(form);
    }

    public abstract void onSubmit(IModel<TablaTransfer> datosTablaModel);
}
