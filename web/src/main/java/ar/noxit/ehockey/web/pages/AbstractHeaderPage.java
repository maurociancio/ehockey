package ar.noxit.ehockey.web.pages;

public abstract class AbstractHeaderPage extends AbstractBasePage implements IMenuSelection {

    public AbstractHeaderPage() {
        add(new HeaderPanel("header", this));
        add(new FooterPanel("footer"));
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(getClass());
    }
}
