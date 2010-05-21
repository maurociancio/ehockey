package ar.noxit.ehockey.web.pages.jugadores;

import ar.noxit.ehockey.exception.JugadorExistenteException;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.web.pages.base.AbstractColorBasePage;
import ar.noxit.exceptions.NoxitException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class JugadorModalPage extends AbstractColorBasePage {

    @SpringBean
    private IJugadorService jugadorService;

    public JugadorModalPage(final ModalWindow modal,
            IModel<Integer> idClub,
            IModel<Integer> idDivision,
            IModel<Integer> idSector) {

        JugadorPlano jp = new JugadorPlano();
        jp.setClubId(idClub.getObject());
        jp.setSectorId(idSector.getObject());
        jp.setDivisionId(idDivision.getObject());
        IModel<JugadorPlano> jugador = new Model<JugadorPlano>(jp);

        add(new JugadorForm("formulario", jugador) {

            @Override
            protected void onSubmit(IModel<JugadorPlano> jugador) {
                try {
                    jugadorService.add(jugador.getObject());
                } catch (JugadorExistenteException ex) {
                    info("El jugador de "
                            + jugador.getObject().getTipoDocumento()
                            + " "
                            + jugador.getObject().getNumeroDocumento()
                                    .toString() + " ya existe en el sistema.");
                } catch (NoxitException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }.setClubActivo(false).setDivisionActivo(false).setSectorActivo(false));

        add(new AjaxLink<Void>("cerrar") {

            @Override
            public void onClick(AjaxRequestTarget target) {
                modal.close(target);
            }
        });
    }
}
