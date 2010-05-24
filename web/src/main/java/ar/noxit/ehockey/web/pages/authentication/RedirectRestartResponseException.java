package ar.noxit.ehockey.web.pages.authentication;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.RestartResponseException;

public class RedirectRestartResponseException extends RestartResponseException {

    public <C extends Page> RedirectRestartResponseException(Class<C> pageClass, PageParameters params) {
        super(pageClass, params);
        RequestCycle.get().setRedirect(true);
    }

    public <C extends Page> RedirectRestartResponseException(Class<C> pageClass) {
        super(pageClass);
        RequestCycle.get().setRedirect(true);
    }

    public RedirectRestartResponseException(Page page) {
        super(page);
        RequestCycle.get().setRedirect(true);
    }
}
