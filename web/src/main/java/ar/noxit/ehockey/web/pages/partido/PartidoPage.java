package ar.noxit.ehockey.web.pages.partido;

import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.service.IPartidoService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.planilla.PlanillaPanel;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.web.wicket.model.IdLDM;
import ar.noxit.web.wicket.model.LDM;

public class PartidoPage extends AbstractContentPage {

    private final IChoiceRenderer<Partido> PARTIDORENDERER = new PartidoRenderer();
    @SpringBean
    private IPartidoService partidoService;
    
    public PartidoPage() {
        add(new FeedbackPanel("feedback"));

        final Panel panel = new PlanillaPanel();
        panel.setVisible(false);
        
        Form<Void> formPartidos = new Form<Void>("partidos") {
            @Override
            protected void onSubmit() {
                panel.setVisible(true);
            }
        };

        IModel<Partido> partido = new PartidoModel(new Model<Integer>());
        formPartidos.add(new DropDownChoice<Partido>("partidos", partido, new PartidosListModel(), PARTIDORENDERER)
                .setRequired(true));

        add(formPartidos);
        add(panel);
    }

    private class PartidoModel extends IdLDM<Partido, Integer> {

        public PartidoModel(IModel<Integer> idModel) {
            super(idModel);
        }

        @Override
        protected Partido doLoad(Integer partidoId) throws NoxitException {
            return partidoService.get(partidoId);
        }

        @Override
        protected Integer getObjectId(Partido partido) {
            return partido.getId();
        }

    }

    private class PartidosListModel extends LDM<List<Partido>> {

        @Override
        protected List<Partido> doLoad() throws NoxitException {
            return partidoService.getAll();
        }
    }

    private class PartidoRenderer implements IChoiceRenderer<Partido> {
        
        private PartidoRenderer() {
        }

        @Override
        public Object getDisplayValue(Partido object) {
            return object.getLocal().getNombre() + " vs " + object.getVisitante().getNombre() + " (fecha: " + object.getFechaDelTorneo().toString() + ")";
        }

        @Override
        public String getIdValue(Partido object, int index) {
            return object.getId().toString();
        }
    }
}