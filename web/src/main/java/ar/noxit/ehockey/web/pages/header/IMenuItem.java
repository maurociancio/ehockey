package ar.noxit.ehockey.web.pages.header;

import ar.noxit.ehockey.web.pages.authentication.IRenderable;

import org.apache.wicket.Page;

public interface IMenuItem extends IRenderable {

    String getTitulo();

    Class<? extends Page> getPageLink();
}
