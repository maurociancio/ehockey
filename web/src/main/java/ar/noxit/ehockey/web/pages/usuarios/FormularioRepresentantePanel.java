package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.commons.lang.Validate;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Representante;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.ehockey.web.pages.base.MensajePage;
import ar.noxit.exceptions.NoxitException;

public class FormularioRepresentantePanel extends Panel {
    private IModel<UsuarioDTO> usuario;
    @SpringBean
    private IUsuarioService usuarioService;

    public FormularioRepresentantePanel(String id, final IModel<UsuarioDTO> usuario, Form<Void> form) {
        super(id);
        Validate.notNull(usuario, "El usuario no puede ser null");
        this.usuario = usuario;

        UsuarioBasePanel basePanel = new UsuarioBasePanel("usuarioBasePanel", new PropertyModel<Usuario>(this, "usuario"));
        basePanel.addValidators(form);
        form.add(basePanel);
        form.add(new UsuarioRepresentantePanel("usuarioRepresentantePanel", new PropertyModel<Representante>(this, "usuario")));

        add(form); 
    }

    @Override
    public boolean isVisible() {
        if (usuario.getObject() == null) return false;
        return usuario.getObject().getTipo().equals(Representante.class);
    }
}
