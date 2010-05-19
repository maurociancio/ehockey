package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;

import ar.noxit.ehockey.model.Representante;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.base.MensajePage;

public class AltaUsuarioRepresentantePage extends AltaUsuarioPage {
    private Representante usuario;

    public AltaUsuarioRepresentantePage() {
        super();
        add(new Form<Void>("usuarioPanel") {

            @Override
            protected void onSubmit() {
                setResponsePage(new MensajePage("Alta de usuario", "Se ha completado el alta de usuario"));
            }
        });

        add(new AltaUsuarioBasePanel("usuarioBasePanel", new PropertyModel<Usuario>(this, "usuario")));
        add(new AltaUsuarioRepresentantePanel("usuarioRepresentantePanel", new PropertyModel<Representante>(this, "usuario")));
    }
}
