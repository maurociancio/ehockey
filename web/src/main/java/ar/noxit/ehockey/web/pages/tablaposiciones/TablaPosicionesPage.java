package ar.noxit.ehockey.web.pages.tablaposiciones;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class TablaPosicionesPage extends AbstractContentPage {

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
