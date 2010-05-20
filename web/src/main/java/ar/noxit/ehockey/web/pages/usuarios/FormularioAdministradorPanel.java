package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import ar.noxit.ehockey.model.Administrador;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.web.pages.base.MensajePage;

public class FormularioAdministradorPanel extends Panel {
    private IModel<UsuarioDTO> usuario;

    public FormularioAdministradorPanel(String id, IModel<UsuarioDTO> usuario, final String titulo, final String mensaje) {
        super(id);
        
        this.usuario = usuario;
        add(new Form<Void>("usuarioPanel") {

            @Override
            protected void onSubmit() {
                setResponsePage(new MensajePage(titulo, mensaje));
            }
        });

        add(new UsuarioBasePanel("usuarioBasePanel", new PropertyModel<Usuario>(this, "usuario")));
    }

    @Override
    public boolean isVisible() {
        return usuario.getObject().equals(Administrador.class);
    }
}
