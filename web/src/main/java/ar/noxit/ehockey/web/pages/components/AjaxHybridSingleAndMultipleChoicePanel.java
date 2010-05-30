package ar.noxit.ehockey.web.pages.components;

import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public abstract class AjaxHybridSingleAndMultipleChoicePanel<T> extends HybridSingleAndMultipleChoicePanel<T> {

    public AjaxHybridSingleAndMultipleChoicePanel(String id,
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

        FormComponent<T> obj = super.createMultivalueComponent(id, model, choices, renderer);

        obj.add(new AjaxFormComponentUpdatingBehavior("onchange") {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                AjaxHybridSingleAndMultipleChoicePanel.this.onUpdate(target);
            }
        });
        return obj;
    }

    protected abstract void onUpdate(AjaxRequestTarget target);
}
