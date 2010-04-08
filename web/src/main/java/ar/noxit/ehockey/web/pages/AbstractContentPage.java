package ar.noxit.ehockey.web.pages;

public abstract class AbstractContentPage extends AbstractHeaderPage {

    public AbstractContentPage() {
        add(new SidebarPanel("sidebar"));
    }
}
