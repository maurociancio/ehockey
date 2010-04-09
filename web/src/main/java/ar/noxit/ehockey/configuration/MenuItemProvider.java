package ar.noxit.ehockey.configuration;

import ar.noxit.ehockey.web.pages.HomePage;
import ar.noxit.ehockey.web.pages.IMenuItem;
import ar.noxit.ehockey.web.pages.IMenuItemProvider;
import ar.noxit.ehockey.web.pages.ListaBuenaFePage;
import ar.noxit.ehockey.web.pages.PartidoPage;
import java.util.ArrayList;
import java.util.List;

public class MenuItemProvider implements IMenuItemProvider {

    @Override
    public List<IMenuItem> getMenuItems() {
        List<IMenuItem> list = new ArrayList<IMenuItem>();
        list.add(new MenuItem("HomePage", HomePage.class));
        list.add(new MenuItem("Lista de Buena Fe", ListaBuenaFePage.class));
        list.add(new MenuItem("Partidos", PartidoPage.class));
        return list;
    }
}
