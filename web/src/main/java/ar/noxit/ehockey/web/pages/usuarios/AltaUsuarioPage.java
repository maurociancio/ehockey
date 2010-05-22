package ar.noxit.ehockey.web.pages.usuarios;

import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.LDM;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AltaUsuarioPage extends AbstractUsuariosPage {

    @SpringBean
    private IUsuarioDTOProvider provider;
    private String selected;
    private final IModel<UsuarioDTO> usuario = new Model<UsuarioDTO>();

    public AltaUsuarioPage() {
        // creo los dos paneles. Uno por cada tipo de alta de usuario
        // los paneles solo se muestan si el usuario es del tipo que corresponde
        final Panel adminPanel = new FormularioAdministradorPanel("administradorPanel", usuario, new AltaUsuarioForm(
                usuario));
        final Panel represPanel = new FormularioRepresentantePanel("representantePanel", usuario, new AltaUsuarioForm(
                usuario));
        this.setOutputMarkupId(true);

        // panel para devolver errores de validación
        add(new FeedbackPanel("feedback"));

        // selector de tipos de usuario
        add(new DropDownChoice<String>("tipos", new PropertyModel<String>(this, "selected"),
                new UsuarioListTypesModel()).setNullValid(false).
                add(new AjaxFormComponentUpdatingBehavior("onchange") {

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
