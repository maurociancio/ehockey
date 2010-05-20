package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import ar.noxit.ehockey.model.Representante;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.base.MensajePage;

public class FormularioRepresentantePanel extends Panel {
    private IModel<UsuarioDTO> usuario;

    public FormularioRepresentantePanel(String id, IModel<UsuarioDTO> usuario, final String titulo, final String mensaje) {
        super(id);

        this.usuario = usuario;
        add(new Form<Void>("usuarioPanel") {

            @Override
            protected void onSubmit() {
                setResponsePage(new MensajePage(titulo, mensaje));
            }
        });

        add(new UsuarioBasePanel("usuarioBasePanel", new PropertyModel<Usuario>(this, "usuario")));
        add(new UsuarioRepresentantePanel("usuarioRepresentantePanel", new PropertyModel<Representante>(this, "usuario")));
    }

    @Override
    public boolean isVisible() {
        return usuario.getObject().equals(Representante.class);
    }
}
