package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.commons.lang.Validate;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.ehockey.web.pages.base.MensajePage;
import ar.noxit.exceptions.NoxitException;

public class EditarUsuarioForm extends Form<Void> {

    @SpringBean
    private IUsuarioService usuarioService;
    private IModel<UsuarioDTO> usuario;

    public EditarUsuarioForm(IModel<UsuarioDTO> usuario) {
        super("usuarioForm");
        Validate.notNull(usuario, "El usuario no puede ser null");
        this.usuario = usuario;
    }

    @Override
    protected void onSubmit() {
        super.onSubmit();
        try {
            usuarioService.update(usuario.getObject());
            setResponsePage(new MensajePage("Edición de usuario", "Se ha modificado el usuario correctamente"));
        } catch (NoxitException e) {
            setResponsePage(new MensajePage("Edición de usuario",
                    "Error en la modificación del usuario, intente nuevamente"));
        }
    }
}
