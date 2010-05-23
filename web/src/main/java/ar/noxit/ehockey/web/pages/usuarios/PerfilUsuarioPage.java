package ar.noxit.ehockey.web.pages.usuarios;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.base.MensajePage;
import ar.noxit.exceptions.NoxitException;

public class PerfilUsuarioPage extends AbstractContentPage{
    @SpringBean
    IUsuarioService usuarioService;

    public PerfilUsuarioPage(final IModel<UsuarioDTO> usuario) {
        add(new FeedbackPanel("feedback"));
        UsuarioBasePanel basePanel = new UsuarioBasePanel("basePanel", usuario);
        basePanel.setUsuarioEditable(false);
        Form form = new Form<Void>("perfilForm") {
            @Override
            protected void onSubmit() {
                try {
                    usuarioService.update(usuario.getObject());
                    setResponsePage(new MensajePage("Perfil de usuario", "Se ha actualizado correctamente el perfil"));
                } catch (NoxitException e) {
                    setResponsePage(new MensajePage("Perfil de usuario", "Error en la actualización del perfil, intente nuevamente"));
                }
            }
        }; 
        form.add(basePanel);
        form.add(new InfoRepresentantePanel("representantePanel", usuario));
        add(form);
    }
}
