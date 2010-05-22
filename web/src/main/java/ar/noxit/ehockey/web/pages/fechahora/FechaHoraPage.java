package ar.noxit.ehockey.web.pages.fechahora;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.web.wicket.model.Date2LocalDateTimeAdapterModel;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.joda.time.LocalDateTime;

public class FechaHoraPage extends AbstractContentPage {

    private LocalDateTime fecha;

    public FechaHoraPage() {
        add(new FeedbackPanel("feedback"));

        Form<Void> form = new Form<Void>("form") {

            @Override
            protected void onSubmit() {
                // TODO
            }
        };

        form.add(new DateTimeField("fechahora", new Date2LocalDateTimeAdapterModel(
                new PropertyModel<LocalDateTime>(this, "fecha"))) {

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
}
