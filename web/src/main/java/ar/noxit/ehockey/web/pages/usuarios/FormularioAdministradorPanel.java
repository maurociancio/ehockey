package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.commons.lang.Validate;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Administrador;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.ehockey.web.pages.base.MensajePage;
import ar.noxit.exceptions.NoxitException;

public class FormularioAdministradorPanel extends Panel {
    private IModel<UsuarioDTO> usuario;
    @SpringBean
    private IUsuarioService usuarioService;

    public FormularioAdministradorPanel(String id, final IModel<UsuarioDTO> usuario, final String titulo, final String mensaje) {
        super(id);
        Validate.notNull(usuario, "El usuario no puede ser null");
        
        this.usuario = usuario;
        Form<Void> form = new Form<Void>("usuarioForm") {

            @Override
            protected void onSubmit() {
                try {
                    usuarioService.add(usuario.getObject());
                } catch (NoxitException e) {
                    setResponsePage(new MensajePage(titulo, "Error en la operación, intente nuevamente"));
                }
                setResponsePage(new MensajePage(titulo, mensaje));
            }
        };

        form.add(new UsuarioBasePanel("usuarioBasePanel", new PropertyModel<Usuario>(this, "usuario")));
        add(form);
    }

    @Override
    public boolean isVisible() {
        if (usuario.getObject() == null) return false;
        return usuario.getObject().getTipo().equals(Administrador.class);
    }
}
