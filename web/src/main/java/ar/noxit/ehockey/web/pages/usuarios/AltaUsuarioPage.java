package ar.noxit.ehockey.web.pages.usuarios;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;

public class AltaUsuarioPage extends AbstractContentPage {

    @SpringBean
    private IUsuarioDTOProvider provider;
    private String selected;
    private final Model<UsuarioDTO> usuario = new Model<UsuarioDTO>();
    private static final String titulo = "Alta de usuario";
    private static final String mensaje = "Se ha dado de alta correctamente el usuario";

    public AltaUsuarioPage() {

        // creo los dos paneles. Uno por cada tipo de alta de usuario
        // los paneles solo se muestan si el usuario es del tipo que corresponde
        final Panel adminPanel = new FormularioAdministradorPanel("administradorPanel", usuario, titulo, mensaje);
        final Panel represPanel = new FormularioRepresentantePanel("representantePanel", usuario, titulo, mensaje);
this.setOutputMarkupId(true);
        // panel para devolver errores de validación
        add(new FeedbackPanel("feedback"));

        // selector de tipos de usuario
        add(new DropDownChoice<String>("tipos", new PropertyModel<String>(this, "selected"),
                new UsuarioListTypesModel()).setNullValid(false).add(
                        new AjaxFormComponentUpdatingBehavior("onchange") {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                usuario.setObject(provider.createUsuarioDTO(selected));
                target.addComponent(AltaUsuarioPage.this);
            }
        }));

        add(adminPanel);
        add(represPanel);
    }

    public class UsuarioListTypesModel extends LDM<List<String>> {

        @Override
        protected List<String> doLoad() throws NoxitException {
            return provider.getListaTipos();
        }

    }
}
