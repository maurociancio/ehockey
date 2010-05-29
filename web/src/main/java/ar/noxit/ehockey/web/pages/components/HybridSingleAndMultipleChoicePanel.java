package ar.noxit.ehockey.web.pages.components;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class HybridSingleAndMultipleChoicePanel<T> extends Panel {

    private FormComponent<T> multipleChoice;
    private Component uniqueChoice;
    private static final String noMatches = "No Matches";

    public HybridSingleAndMultipleChoicePanel(String id, IModel<T> model, IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id);

        List<? extends T> object = choices.getObject();
        multipleChoice = createMultivalueComponent("multiple", model, choices, renderer);
        uniqueChoice = createUniqueValueComponent("single", model);
        add(multipleChoice);
        add(uniqueChoice);
        multipleChoice.setVisible(false);
        uniqueChoice.setVisible(false);
        if (object == null || object.size() == 0) {
            this.nullOrEmptyValueAction();
        } else if (object.size() <= 1) {
            model.setObject(object.get(0));
            uniqueChoice.setVisible(true);
        } else {
            multipleChoice.setVisible(true);
        }
    }

    protected void nullOrEmptyValueAction() {
        Label replacement = new Label(uniqueChoice.getId(), noMatches);
        uniqueChoice.replaceWith(replacement);
        uniqueChoice = replacement;
        uniqueChoice.setVisible(true);
    }

    protected Component createUniqueValueComponent(String id, IModel<T> model) {
        return new Label(id, model);
    }

    protected FormComponent<T> createMultivalueComponent(String id, IModel<T> model,
            IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer) {
        return new DropDownChoice<T>(id, model, choices, renderer);
    }

    public void setOutputMarkupId() {
        uniqueChoice.setOutputMarkupId(true);
        multipleChoice.setOutputMarkupId(true);
    }

    public HybridSingleAndMultipleChoicePanel<T> add(IBehavior behavior) {
        multipleChoice.add(behavior);
        return this;
    }

}
