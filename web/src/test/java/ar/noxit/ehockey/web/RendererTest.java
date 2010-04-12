package ar.noxit.ehockey.web;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import ar.noxit.ehockey.configuration.MenuItem;
import ar.noxit.ehockey.web.pages.HomePage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.header.IMenuItemProvider;
import com.ttdev.wicketpagetest.MockableBeanInjector;
import java.util.ArrayList;
import org.apache.wicket.PageParameters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RendererTest extends BaseSpringWicketTest {

    @BeforeMethod
    public void mockHeaderItems() {
        IMenuItemProvider menuItemProvider = createMock(IMenuItemProvider.class);

        ArrayList<IMenuItem> value = new ArrayList<IMenuItem>();
        value.add(new MenuItem("home", HomePage.class));

        expect(menuItemProvider.getMenuItems()).andReturn(value);
        replay(menuItemProvider);

        MockableBeanInjector.mockBean("menuItemProvider", menuItemProvider);
    }

    @Test
    public void testRenderer() {
        HTMLRenderer htmlRenderer = new HTMLRenderer();
        String renderPage = htmlRenderer.renderPage(HomePage.class, new PageParameters());
        assert !renderPage.isEmpty();
    }
}
