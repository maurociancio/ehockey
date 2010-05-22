package ar.noxit.ehockey.web.pages;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;

import ar.noxit.ehockey.web.pages.base.AbstractContentPage;

@AuthorizeInstantiation( { "USER", "ADMIN" })
public class HomePage extends AbstractContentPage {
}
