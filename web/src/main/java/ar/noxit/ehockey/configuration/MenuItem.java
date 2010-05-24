package ar.noxit.ehockey.configuration;

import ar.noxit.ehockey.web.pages.header.IMenuItem;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.strategies.role.Roles;

public class MenuItem implements IMenuItem {

    private String titulo;
    private Class<? extends Page> pageLink;
    private String[] roles;

    public MenuItem(String titulo, Class<? extends Page> pageLink, String[] roles) {
        this.titulo = titulo;
        this.pageLink = pageLink;
        this.roles = roles;
    }

    @Override
    public Class<? extends Page> getPageLink() {
        return pageLink;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public boolean couldBeRendered(Roles roles) {
        return roles.hasAnyRole(new Roles(this.roles));
    }
}
