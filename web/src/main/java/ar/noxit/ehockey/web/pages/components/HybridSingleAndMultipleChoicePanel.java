package ar.noxit.ehockey.web.pages.components;

import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class HybridSingleAndMultipleChoicePanel<T> extends FormComponentPanel<T> {

    private static final String SINGLE = "single";
    private FormComponent<T> multipleChoice;
    private Component uniqueChoice;

    private IModel<T> model;
    private IModel<? extends List<? extends T>> choices;
    private IChoiceRenderer<? super T> renderer;

    private IModel<Boolean> multipleChoices;
    private IModel<Boolean> noChoices;

    private static final String noMatches = "Sin opciones.";

    public HybridSingleAndMultipleChoicePanel(String id,
            IModel<T> model,
            final IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer) {

        super(id, model);

        this.model = model;
        this.choices = choices;
        this.renderer = renderer;

        this.multipleChoices = new AbstractReadOnlyModel<Boolean>() {

            @Override
            public Boolean getObject() {
                return choices.getObject().size() > 1;
            }
        };
        this.noChoices = new AbstractReadOnlyModel<Boolean>() {

            @Override
            public Boolean getObject() {
                return choices.getObject().size() == 0;
            }
        };

        multipleChoice = createMultivalueComponent("multiple", model, choices, renderer);
        add(multipleChoice);

        uniqueChoice = createUniqueValueComponent(SINGLE, model);
        add(uniqueChoice);

        add(this.createNullLabel("no_choices"));

        actualizarModelos();
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        actualizarModelos();
    }

    private void actualizarModelos() {
        List<? extends T> object = choices.getObject();
        if (object.size() == 1) {
            model.setObject(object.get(0));
        }
        choices.detach();
    }

    protected Label createNullLabel(String id) {
        return new Label(id, noMatches) {

            @Override
            public boolean isVisible() {
                return noChoices.getObject();
            }
        };
    }

    protected Component createUniqueValueComponent(String id, final IModel<T> model) {
        IModel<Object> labelModel = new AbstractReadOnlyModel<Object>() {

            @Override
            public Object getObject() {
                T object = model.getObject();
                return renderer.getDisplayValue(object);
            }
        };
        return new Label(id, labelModel) {

            @Override
            public boolean isVisible() {
                return !multipleChoices.getObject() && !noChoices.getObject();
            }

            @Override
            public boolean isEnabled() {
                return HybridSingleAndMultipleChoicePanel.this.isEnabled();
            }
        };
    }

    protected FormComponent<T> createMultivalueComponent(String id,
            IModel<T> model,
            IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer) {

        return new DropDownChoice<T>(id, model, choices, renderer) {

            @Override
            public boolean isVisible() {
                return multipleChoices.getObject();
            }

            @Override
            public boolean isEnabled() {
                return HybridSingleAndMultipleChoicePanel.this.isEnabled();
            }
        };
    }

    @Override
    protected void convertInput() {
        T object = null;
        if (uniqueChoice.isVisible()) {
            object = model.getObject();
        } else if (multipleChoice.isVisible()) {
            object = multipleChoice.getConvertedInput();
        }
        setConvertedInput(object);
    }
}
