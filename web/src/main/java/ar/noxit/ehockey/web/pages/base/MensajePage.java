package ar.noxit.ehockey.web.pages.base;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class MensajePage extends AbstractContentPage {

    public MensajePage(String title, String message) {
        this(Model.of(title), Model.of(message));
    }

    public MensajePage(IModel<String> title, IModel<String> message) {
        add(new Label("title", title).setRenderBodyOnly(true));
        add(new Label("message", message).setRenderBodyOnly(true));
    }
}
