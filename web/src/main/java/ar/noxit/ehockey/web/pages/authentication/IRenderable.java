package ar.noxit.ehockey.web.pages.authentication;

import org.apache.wicket.authorization.strategies.role.Roles;

public interface IRenderable {

    boolean couldBeRendered(Roles roles);
}
