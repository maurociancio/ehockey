package ar.noxit.ehockey.web.pages.authentication;

import org.apache.wicket.authentication.panel.SignInPanel;

public class LoginPanel extends SignInPanel {

    public LoginPanel(String id) {
        this(id, false);
    }

    public LoginPanel(String id, boolean includeRememberMe) {
        super(id, includeRememberMe);
    }

}
