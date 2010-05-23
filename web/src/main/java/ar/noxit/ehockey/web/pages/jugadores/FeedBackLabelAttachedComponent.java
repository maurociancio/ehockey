package ar.noxit.ehockey.web.pages.jugadores;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Fragment;

public class FeedBackLabelAttachedComponent<T extends FormComponent<?>> extends
        Fragment {

    private FeedbackLabel label;

    public FeedBackLabelAttachedComponent(String fragmentid,
            String fragmentComponentid, String feedbackid, T component,
            MarkupContainer container) {
        this(fragmentid, fragmentComponentid, feedbackid, component, container,
                "onblur");
    }

    public FeedBackLabelAttachedComponent(String fragmentid,
            String fragmentComponentid, String feedbackid, T component,
            MarkupContainer container, String event) {
        super(fragmentid, fragmentComponentid, container);
        label = new FeedbackLabel(feedbackid, component);
        component.add(new ComponentVisualErrorBehavior(event, label))
                .setOutputMarkupId(true);
        label.setOutputMarkupId(true);
        this.add(component);
        this.add(label);
    }

}
