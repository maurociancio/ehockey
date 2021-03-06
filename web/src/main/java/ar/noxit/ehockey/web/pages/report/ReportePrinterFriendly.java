package ar.noxit.ehockey.web.pages.report;

import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.web.pages.base.AbstractColorLessBasePage;

public class ReportePrinterFriendly extends AbstractColorLessBasePage {

    public ReportePrinterFriendly(IModel<Jugador> jugadorModel) {
        add(new JugadorReportePanel("paneljugador", jugadorModel));
    }
}
