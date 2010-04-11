package ar.noxit.ehockey.configuration;

import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.ehockey.web.pages.header.IMenuItemProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.wicket.Page;

public class MenuItemProvider implements IMenuItemProvider {

    private Map<String, Class<? extends Page>> items;

    @Override
    public List<IMenuItem> getMenuItems() {
        List<IMenuItem> list = new ArrayList<IMenuItem>();
        Iterator<Entry<String, Class<? extends Page>>> it = items.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Class<? extends Page>> next = it.next();
            list.add(new MenuItem(next.getKey(), next.getValue()));
        }
        return list;
    }

    public void setItems(Map<String, Class<? extends Page>> items) {
        this.items = items;
    }
}
