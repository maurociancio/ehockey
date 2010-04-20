package ar.noxit.ehockey.web.pages.torneo;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public class NuevoTorneoPage extends BaseTorneoPage {

    public NuevoTorneoPage() {
        this(null);
    }

    public NuevoTorneoPage(final IModel<String> message) {
        add(new NuevoTorneoWizard("wizard") {

            @Override
            public boolean isVisible() {
                return message == null;
            }
        });
        add(new Label("mensaje", message) {

            @Override
            public boolean isVisible() {
                return message != null;
            }
        });
    }
}
