package ar.noxit.ehockey.web.app;

import ar.noxit.ehockey.web.pages.HomePage;
import ar.noxit.ehockey.StartJetty;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see StartJetty.myproject.Start#main(String[])
 */
public class EHockeyApplication extends WebApplication {

    /**
     * Constructor
     */
    public EHockeyApplication() {
    }

    /**
     * @see wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }
}
