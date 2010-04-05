package ar.noxit.ehockey.web;

import com.ttdev.wicketpagetest.MockableSpringBeanInjector;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.util.tester.WicketTester.DummyWebApplication;
import org.testng.annotations.BeforeMethod;

public abstract class BaseSpringWicketTest {

    protected WicketTester tester;

    @BeforeMethod
    public void setUp() {
        tester = new WicketTester(getWebApp());
    }

    protected WebApplication getWebApp() {
        return new DummyWebApplication() {

            @Override
            protected void init() {
                super.init();
                MockableSpringBeanInjector.installInjector(this);
            }
        };
    }

    protected void startPageAndTestRendered(Class<? extends Page> pageClass) {
        // start
        tester.startPage(pageClass);

        // test rendered
        tester.assertRenderedPage(pageClass);
    }
}