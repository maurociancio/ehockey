package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.commons.lang.Validate;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import ar.noxit.ehockey.model.Administrador;

public class FormularioAdministradorPanel extends Panel {
    private IModel<UsuarioDTO> usuario;
    private UsuarioBasePanel basePanel;

    public FormularioAdministradorPanel(String id, final IModel<UsuarioDTO> usuario, Form<Void> form) {
        super(id);
        Validate.notNull(usuario, "El usuario no puede ser null");
        this.usuario = usuario;

        basePanel = new UsuarioBasePanel("usuarioBasePanel", new PropertyModel<UsuarioDTO>(this, "usuario"));
        basePanel.addValidators(form);
        form.add(basePanel);

        add(form);
    }

    @Override
    public boolean isVisible() {
        if (usuario.getObject() == null) return false;
        return usuario.getObject().getTipo().equals(Administrador.class);
    }

    public void setUsuarioEditable(boolean estado) {
        basePanel.setUsuarioEditable(estado);
    }
}
