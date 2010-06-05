package ar.noxit.ehockey.web.pages.components;

import java.util.List;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public class RequiredHybridSingleAndMultipleChoicePanel<T> extends HybridSingleAndMultipleChoicePanel<T> {

    public RequiredHybridSingleAndMultipleChoicePanel(String id,
            IModel<T> model,
            IModel<? extends List<? extends T>> choices,
                    IChoiceRenderer<? super T> renderer) {

        super(id, model, choices, renderer);
    }

    @Override
    protected FormComponent<T> createMultivalueComponent(String id,
            IModel<T> model,
            IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer) {

        return super.createMultivalueComponent(id, model, choices, renderer).setRequired(true);
    }
}
