package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import ar.noxit.ehockey.model.Usuario;

public class UsuarioBasePanel extends Panel {

    private String password;

    public UsuarioBasePanel(String id, IModel<Usuario> usuario) {
        super(id, usuario);

        add(new RequiredTextField<String>("usuario", new PropertyModel<String>(usuario, "user")));
        add(new PasswordTextField("contrasena", new PropertyModel<String>(usuario, "password")).setRequired(true));
        add(new RequiredTextField<String>("nombre", new PropertyModel<String>(usuario, "nombre")));
        add(new RequiredTextField<String>("apellido", new PropertyModel<String>(usuario, "apellido")));
    }
}
