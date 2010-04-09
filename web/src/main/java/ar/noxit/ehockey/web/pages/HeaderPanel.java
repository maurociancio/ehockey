package ar.noxit.ehockey.web.pages;

import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HeaderPanel extends Panel {

    @SpringBean
    private IMenuItemProvider menuItemProvider;

    public HeaderPanel(String id, final IMenuSelection menuSelection) {
        super(id);

        add(new ListView<IMenuItem>("links", new MenuItemModel()) {

            @Override
            protected void populateItem(ListItem<IMenuItem> item) {
                final IModel<IMenuItem> model = item.getModel();

                BookmarkablePageLink<Void> link = new BookmarkablePageLink<Void>("link", model.getObject()
                        .getPageLink());
                link.add(new Label("titulo", new PropertyModel<String>(model, "titulo")));
                item.add(link);

                item.add(new AttributeModifier("class", true, new AbstractReadOnlyModel<String>() {

                    @Override
                    public String getObject() {
                        boolean shouldBeSelected = menuSelection.shouldBeSelected(model.getObject());
                        if (shouldBeSelected) {
                            return "nav-active";
                        }
                        return "";
                    }
                }));
            }
        });
    }

    public class MenuItemModel extends LoadableDetachableModel<List<IMenuItem>> {

        @Override
        protected List<IMenuItem> load() {
            return menuItemProvider.getMenuItems();
        }
    }
}
