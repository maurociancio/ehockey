package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.Rol;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.model.IModel;

@AuthorizeAction(action = "RENDER", roles = Rol.REPROGRAMAR_PARTIDO)
public class ReprogramarPartidoLink extends AjaxLink<Void> {

    private final ModalWindow modalWindow;
    private final IModel<Partido> partido;
    private Component dataTable;

    public ReprogramarPartidoLink(String id, ModalWindow modalWindow, IModel<Partido> partido, Component dataTable) {
        super(id);
        this.modalWindow = modalWindow;
        this.partido = partido;
        this.dataTable = dataTable;
    }

    @Override
    public void onClick(AjaxRequestTarget target) {
        modalWindow.setPageCreator(new ModalWindow.PageCreator() {

            @Override
            public Page createPage() {
                return new ReprogramacionPartidoPage(modalWindow, partido);
            }
        });
        modalWindow.setWindowClosedCallback(new WindowClosedCallback() {

            @Override
            public void onClose(AjaxRequestTarget target) {
                target.addComponent(dataTable);
            }
        });
        modalWindow.show(target);
    }

    @Override
    public boolean isVisible() {
        return !partido.getObject().isJugado();
    }
}