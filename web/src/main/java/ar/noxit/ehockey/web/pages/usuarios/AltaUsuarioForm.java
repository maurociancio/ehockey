package ar.noxit.ehockey.web.pages.usuarios;

import ar.noxit.ehockey.exception.UsuarioExistenteException;
import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.ehockey.web.pages.base.MensajePage;
import ar.noxit.exceptions.NoxitException;
import org.apache.commons.lang.Validate;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AltaUsuarioForm extends Form<Void> {

    @SpringBean
    private IUsuarioService usuarioService;
    private IModel<UsuarioDTO> usuario;

    public AltaUsuarioForm(IModel<UsuarioDTO> usuario) {
        super("usuarioForm");
        Validate.notNull(usuario, "El usuario no puede ser null");
        this.usuario = usuario;
    }

    @Override
    protected void onSubmit() {
        super.onSubmit();
        try {
            usuarioService.add(usuario.getObject());
            setResponsePage(new MensajePage("Alta de usuario", "Se ha dado de alta el usuario correctamente"));
        } catch (UsuarioExistenteException e) {
            setResponsePage(new MensajePage("Alta de usuario",
                    "El usuario ya existe, intente con otro nombre de usuario"));
        } catch (NoxitException e) {
            setResponsePage(new MensajePage("Alta de usuario", "Error en el alta de usuario, intente nuevamente"));
        }
    }
}
