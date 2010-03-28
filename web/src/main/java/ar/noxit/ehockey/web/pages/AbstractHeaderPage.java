package ar.noxit.ehockey.web.pages;

public abstract class AbstractHeaderPage extends AbstractBasePage {

    public AbstractHeaderPage() {
        add(new HeaderPanel("header"));
        add(new FooterPanel("footer"));
    }
}
