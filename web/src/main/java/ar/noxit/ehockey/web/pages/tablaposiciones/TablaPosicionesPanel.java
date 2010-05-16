package ar.noxit.ehockey.web.pages.tablaposiciones;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

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

public class TablaPosicionesPanel extends Panel {

    @SpringBean
    private ITorneoService torneoService;
    @SpringBean
    private ITablaPosicionesService tablaService;
    List<IColumn<DatosTabla>> columnas;

    public TablaPosicionesPanel(final IModel<TablaTransfer> tablaTransferModel) {
        super("formulariopanel");
        setOutputMarkupId(true);
        columnas = new ArrayList<IColumn<DatosTabla>>();

        add(new DropDownChoice<Torneo>("torneo", new IdTorneoModel(
                new PropertyModel<Integer>(tablaTransferModel, "torneoId"),
                torneoService), new TorneoListModel(torneoService),
                new TorneoRenderer()).setNullValid(true).add(
                new AjaxFormComponentUpdatingBehavior("onchange") {

                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        MarkupContainer parent = this.getComponent()
                                .getParent();
                        parent
                                .addOrReplace(new AjaxFallbackDefaultDataTable<DatosTabla>(
                                        "tablaposiciones",
                                        columnas.toArray(new IColumn[columnas
                                                .size()]),
                                        new TablaPosicionesDataProvider(
                                                tablaService)
                                                .setTorneoId(
                                                        tablaTransferModel
                                                                .getObject()
                                                                .getTorneoId())
                                                .setDivisionId(
                                                        tablaTransferModel
                                                                .getObject()
                                                                .getDivisionId())
                                                .setSectorId(
                                                        tablaTransferModel
                                                                .getObject()
                                                                .getSectorId()),
                                        10));
                        target.addComponent(parent);
                    }

                }));

        columnas.add(new PropertyColumn<DatosTabla>(Model.of("Nombre"),
                "nombre"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PTS"), "puntos"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PJ"),
                "partidosJugados"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PG"),
                "partidosGanados"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PE"),
                "partidosEmpatados"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("PP"),
                "partidosPerdidos"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("GF"),
                "golesFavor"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("GC"),
                "golesContra"));
        columnas.add(new PropertyColumn<DatosTabla>(Model.of("DG"),
                "diferenciaGol"));

        DataTable<DatosTabla> tabla = new AjaxFallbackDefaultDataTable<DatosTabla>(
                "tablaposiciones", columnas
                        .toArray(new IColumn[columnas.size()]),
                new TablaPosicionesDataProvider(tablaService).setTorneoId(
                        tablaTransferModel.getObject().getTorneoId())
                        .setDivisionId(
                                tablaTransferModel.getObject().getDivisionId())
                        .setSectorId(
                                tablaTransferModel.getObject().getSectorId()),
                10);
        add(tabla);
    }
}
