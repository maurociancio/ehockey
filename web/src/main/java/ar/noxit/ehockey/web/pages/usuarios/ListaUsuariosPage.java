package ar.noxit.ehockey.web.pages.usuarios;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Usuario;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IUsuarioService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.models.UsuarioAdapterModel;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.model.IdLDM;
import ar.noxit.web.wicket.provider.DataProvider;

public class ListaUsuariosPage extends AbstractContentPage {

    @SpringBean
    private IUsuarioService usuarioService;
    @SpringBean
    private IClubService clubService;

    public ListaUsuariosPage() {
        DataView<Usuario> tabla = new DataView<Usuario>("usuarios", new UsuariosProvider()) {

            @Override
            public void populateItem(final Item<Usuario> item) {
                item.add(new Link<AbstractContentPage>("editarUsuario") {

                    @Override
                    public void onClick() {
                        setResponsePage(new EditarUsuarioPage(new UsuarioAdapterModel(item.getModel(),clubService)));
                    }
                }.add(new Label("usuario", new PropertyModel<String>(item.getModel(), "user"))));
                item.add(new Label("nombre", new PropertyModel<String>(item.getModel(), "nombre")));
                item.add(new Label("apellido", new PropertyModel<String>(item.getModel(), "apellido")));
            }
        };
        add(tabla);
        add(new Form<Void>("nuevo") {

            @Override
            protected void onSubmit() {
                setResponsePage(new AltaUsuarioPage());
            }
        });
    }

    public class UsuariosProvider extends DataProvider<Usuario> {

        @Override
        protected List<Usuario> loadList() {
            try {
                return usuarioService.getAll();
            } catch (NoxitException e) {
                throw new NoxitRuntimeException(e);
            }
        }

        @Override
        public IModel<Usuario> model(Usuario object) {
            return new UsuarioModel(new Model<String>(object.getUser()));
        }
        
    }

    public class UsuarioModel extends IdLDM<Usuario, String>{

        public UsuarioModel(IModel<String> idModel) {
            super(idModel);
        }

        @Override
        protected Usuario doLoad(String id) throws NoxitException {
            return usuarioService.get(id);
        }

        @Override
        protected String getObjectId(Usuario object) {
            return object.getUser();
        }

    }
}