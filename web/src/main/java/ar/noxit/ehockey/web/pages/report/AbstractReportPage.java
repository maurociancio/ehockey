package ar.noxit.ehockey.web.pages.report;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;

public abstract class AbstractReportPage extends AbstractContentPage {

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(ReportPage.class);
    }

}
