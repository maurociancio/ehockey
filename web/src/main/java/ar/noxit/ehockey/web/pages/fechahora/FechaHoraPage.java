package ar.noxit.ehockey.web.pages.fechahora;

import ar.noxit.ehockey.service.IHorarioService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.model.Date2LocalDateTimeAdapterModel;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.LocalDateTime;

public class FechaHoraPage extends AbstractContentPage {

    @SpringBean
    private IHorarioService horarioService;
    private LocalDateTime fecha;

    public FechaHoraPage() {
        add(new FeedbackPanel("feedback"));

        Form<Void> form = new Form<Void>("form") {

            @Override
            protected void onSubmit() {
                try {
                    horarioService.definirHoraSistema(fecha);
                } catch (NoxitException e) {
                    throw new NoxitRuntimeException(e);
                }
            }
        };

        form.add(new DateTimeField("fechahora", new Date2LocalDateTimeAdapterModel(new FechaSistemaModel())) {

            @SuppressWarnings("unchecked")
            @Override
            protected DateTextField newDateTextField(String id, PropertyModel dateFieldModel) {
                return new DateTextField(id, dateFieldModel, new PatternDateConverter("dd/MM/yyyy", false));
            }
        }.setRequired(true));
        add(form);
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(FechaHoraPage.class);
    }

    public class FechaSistemaModel implements IModel<LocalDateTime> {

        private boolean loaded = false;

        @Override
        public LocalDateTime getObject() {
            try {
                if (!loaded) {
                    fecha = horarioService.getHoraSistema();
                    loaded = true;
                }
                return fecha;
            } catch (NoxitException e) {
                throw new NoxitRuntimeException(e);
            }
        }

        @Override
        public void setObject(LocalDateTime object) {
            this.loaded = true;
            fecha = object;
        }

        @Override
        public void detach() {
        }
    }
}
