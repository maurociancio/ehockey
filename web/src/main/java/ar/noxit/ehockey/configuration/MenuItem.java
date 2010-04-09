package ar.noxit.ehockey.configuration;

import ar.noxit.ehockey.web.pages.IMenuItem;
import org.apache.wicket.Page;

public class MenuItem implements IMenuItem {

    private String titulo;
    private Class<? extends Page> pageLink;

    public MenuItem(String titulo, Class<? extends Page> pageLink) {
        this.titulo = titulo;
        this.pageLink = pageLink;
    }

    @Override
    public Class<? extends Page> getPageLink() {
        return pageLink;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }
}
