package ar.noxit.ehockey.web.pages.base;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

public abstract class AbstractBasePage extends WebPage {

    public AbstractBasePage() {
        add(new WebMarkupContainer("body") {

            @Override
            public boolean isTransparentResolver() {
                return true;
            }

            @Override
            protected void onComponentTag(ComponentTag tag) {
                if (agregarColor()) {
                    tag.put("class", "body_fondo");
                }
            }
        });
    }

    protected boolean agregarColor() {
        return false;
    }
}
