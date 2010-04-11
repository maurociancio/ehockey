package ar.noxit.ehockey.web.pages.base;

import ar.noxit.ehockey.web.pages.sidebar.SidebarPanel;

public abstract class AbstractContentPage extends AbstractHeaderPage {

    public AbstractContentPage() {
        add(new SidebarPanel("sidebar"));
    }
}
