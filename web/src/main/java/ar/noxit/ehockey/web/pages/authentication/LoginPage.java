package ar.noxit.ehockey.web.pages.authentication;

import ar.noxit.ehockey.web.pages.base.AbstractColorBasePage;
import ar.noxit.ehockey.web.pages.footer.FooterPanel;

public class LoginPage extends AbstractColorBasePage {

    public LoginPage() {
        add(new LoginHeaderPanel("header"));
        add(new LoginPanel("signInPanel",true));
        add(new FooterPanel("footer"));
    }
}
