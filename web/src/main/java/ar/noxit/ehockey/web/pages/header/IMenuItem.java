package ar.noxit.ehockey.web.pages.header;

import org.apache.wicket.Page;

public interface IMenuItem {

    String getTitulo();

    Class<? extends Page> getPageLink();
}
