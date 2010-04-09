package ar.noxit.ehockey.web.pages;

import org.apache.wicket.Page;

public interface IMenuItem {

    String getTitulo();

    Class<? extends Page> getPageLink();
}
