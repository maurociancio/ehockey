package ar.noxit.ehockey.web;

import ar.noxit.ehockey.web.pages.ExamplePage;
import org.testng.annotations.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends BaseSpringWicketTest {

    @Test
    public void testRenderMyPage() {
        startPageAndTestRendered(ExamplePage.class);
    }
}
