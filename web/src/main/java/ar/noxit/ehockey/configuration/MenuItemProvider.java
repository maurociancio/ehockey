package ar.noxit.ehockey.configuration;

import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.header.IMenuItemProvider;
import java.util.List;

public class MenuItemProvider implements IMenuItemProvider {

    private List<IMenuItem> menuItems;

    @Override
    public List<IMenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<IMenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
