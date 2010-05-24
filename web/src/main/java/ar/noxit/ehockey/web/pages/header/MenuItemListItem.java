package ar.noxit.ehockey.web.pages.header;

import ar.noxit.ehockey.web.pages.authentication.IRenderable;

import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;

public class MenuItemListItem extends ListItem<IMenuItem> implements IRenderable {

    public MenuItemListItem(int index, IModel<IMenuItem> model) {
        super(index, model);
    }

    @Override
    public boolean couldBeRendered(Roles roles) {
        return getModelObject().couldBeRendered(roles);
    }
}
