package ar.noxit.ehockey.web.pages.authentication;

import org.apache.wicket.Component;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.strategies.role.Roles;

public class RolRenderableStrategy implements IAuthorizationStrategy {

    @Override
    public <T extends Component> boolean isInstantiationAuthorized(Class<T> componentClass) {
        return true;
    }

    @Override
    public boolean isActionAuthorized(Component component, Action action) {
        if (component instanceof IRenderable && action.equals(Component.RENDER)) {
            IRenderable renderable = (IRenderable) component;

            AuthSession authSession = AuthSession.get();
            Roles roles = authSession.getRoles();

            return renderable.couldBeRendered(roles);
        }
        return true;
    }
}