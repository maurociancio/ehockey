package ar.noxit.ehockey.web.pages.authentication;

import ar.noxit.ehockey.model.Administrador;
import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.ehockey.web.pages.base.AbstractColorBasePage;
import ar.noxit.ehockey.web.pages.footer.FooterPanel;
import ar.noxit.ehockey.web.pages.usuarios.AltaUsuarioForm;
import ar.noxit.ehockey.web.pages.usuarios.FormularioAdministradorPanel;
import ar.noxit.ehockey.web.pages.usuarios.TipoUsuario;
import ar.noxit.ehockey.web.pages.usuarios.UsuarioDTO;
import ar.noxit.exceptions.NoxitException;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends AbstractColorBasePage {

    @SpringBean
    private IUsuarioService usuarioService;
    private boolean crearUsuario = false;
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage() {
        final IModel<UsuarioDTO> usuario = new Model<UsuarioDTO>();
        // el panel de alta de usuario solo se muestra si no hay administrador
        crearAdministradorSiNoExisteUno(usuario);
        add(new Label("titulo", new AbstractReadOnlyModel<String>() {

            @Override
            public String getObject() {
                return crearUsuario ? "Crear usuario Administrador" : "Login de Usuarios";
            }
        }));

        add(new LoginHeaderPanel("header"));

        add(new FeedbackPanel("feedback") {

            @Override
            public boolean isVisible() {
                return !mostrarPanelLogin(usuario);
            }
        });

        add(new LoginPanel("signInPanel", true) {

            @Override
            public boolean isVisible() {
                return mostrarPanelLogin(usuario);
            }
        });

        add(new FormularioAdministradorPanel("usuario", usuario, new AltaUsuarioForm(usuario) {

            @Override
            protected void onSubmit() {
                try {
                    usuarioService.add(usuario.getObject());
                    crearAdministradorSiNoExisteUno(usuario);
                    crearUsuario = false;
                } catch (NoxitException e) {
                    logger.debug("excepcion creando usuario", e);
                }
            }
        }));

        add(new FooterPanel("footer"));
    }

    private boolean mostrarPanelLogin(IModel<UsuarioDTO> usuario) {
        if (usuario.getObject() == null)
            return true;
        return !usuario.getObject().getTipo().equals(Administrador.class);
    }

    private void crearAdministradorSiNoExisteUno(IModel<UsuarioDTO> usuario) {
        try {
            List<Usuario> lista = usuarioService.getAll();
            Iterator<Usuario> it = lista.iterator();
            boolean encontrado = false;
            while (it.hasNext() && !encontrado) {
                if (it.next() instanceof Administrador)
                    encontrado = true;
            }
            if (!encontrado) {
                usuario.setObject(new UsuarioDTO(new TipoUsuario(Administrador.class)));
                crearUsuario = true;
            } else
                usuario.setObject(null);
        } catch (NoxitException e) {
            logger.debug("excepcion creando usuario", e);
            // no hago nada, muestra solo la pantalla de login si hay error
        }
    }
}
