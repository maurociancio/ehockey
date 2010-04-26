package ar.noxit.ehockey.web.pages.torneo;

import ar.noxit.ehockey.service.transfer.PartidoInfo;
import ar.noxit.ehockey.web.pages.base.AbstractColorBasePage;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class AgregarPartidoPage extends AbstractColorBasePage {

    public AgregarPartidoPage(final ModalWindow modalWindow, final IModel<? extends List<PartidoInfo>> partidos) {
        add(new PartidoFormPanel("form", Model.of(new PartidoInfo())) {

            @Override
            protected void onSubmit(AjaxRequestTarget target, IModel<PartidoInfo> partido) {
                partidos.getObject().add(partido.getObject());

                modalWindow.close(target);
            }
        });
    }
}
