package ar.noxit.ehockey.web.pages;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.service.IEquiposService;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import ar.noxit.web.wicket.model.LDM;
import java.util.List;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePage extends AbstractContentPage {

    @SpringBean
    private IEquiposService equiposService;

    public HomePage() {
        add(new FeedbackPanel("feedback"));
        Form<Void> form = new Form<Void>("equipos");
        form.add(new DropDownChoice<Equipo>("equipos",
                new EquipoModel(new Model<Integer>()),
                new EquiposListModel(),
                new EquipoRenderer())
                .setRequired(true));
        add(form);
    }

    private class EquipoRenderer implements IChoiceRenderer<Equipo> {

        @Override
        public Object getDisplayValue(Equipo object) {
            return object.getNombre();
        }

        @Override
        public String getIdValue(Equipo object, int index) {
            return object.getId().toString();
        }
    }

    private class EquipoModel extends IdLDM<Equipo, Integer> {

        private EquipoModel(IModel<Integer> idModel) {
            super(idModel);
        }

        @Override
        protected Equipo doLoad(Integer id) throws NoxitException {
            return equiposService.get(id);
        }

        @Override
        protected Integer getObjectId(Equipo equipo) {
            return equipo.getId();
        }
    }

    public class EquiposListModel extends LDM<List<Equipo>> {

        @Override
        protected List<Equipo> doLoad() throws NoxitException {
            return equiposService.getAll();
        }
    }
}
