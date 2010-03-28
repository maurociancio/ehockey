package ar.noxit.ehockey.web.pages;

import java.util.Date;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class ExamplePage extends AbstractHeaderPage {

    public ExamplePage() {
        add(new Label("titulo", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                return "Hola, hoy es: " + new Date().toString();
            }
        }));
    }
}
