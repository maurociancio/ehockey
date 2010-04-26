package ar.noxit.ehockey.web.pages.tablaposiciones;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.DatosTabla;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.ehockey.service.ITablaPosicionesService;
import ar.noxit.ehockey.service.ITorneoService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.providers.TablaPosicionesDataProvider;

public class TablaPosicionesPage extends AbstractContentPage {

    @SpringBean
    private ITablaPosicionesService tablaService;
    private IModel<TablaTransfer> transferModel;

    public TablaPosicionesPage() {
        transferModel = new Model<TablaTransfer>(new TablaTransfer());
        setUp();
    }

    public TablaPosicionesPage(IModel<TablaTransfer> model) {
        transferModel = model;
        setUp();
    }

    private void setUp() {
        add(new TablaPosicionesPanel(transferModel) {
            @Override
            public void onSubmit(IModel<TablaTransfer> tablaTransferModel) {
                transferModel.setObject(tablaTransferModel.getObject());
                setResponsePage(new TablaPosicionesPage(transferModel));
            }
        });
    }
}
