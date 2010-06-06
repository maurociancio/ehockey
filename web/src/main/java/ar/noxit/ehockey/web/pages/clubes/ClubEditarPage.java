package ar.noxit.ehockey.web.pages.clubes;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.exception.ClubYaExistenteException;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;

public class ClubEditarPage extends AbstractMainLinkedClubPage {

    @SpringBean
    private IClubService clubService;

    public ClubEditarPage(IModel<ClubPlano> clubModel) {

        add(new ClubFormPanel("editar", clubModel) {
            @Override
            public void onSubmit(IModel<ClubPlano> clubPlano) {
                try {
                    clubService.verificarCambioNombre(clubPlano.getObject());
                    clubService.update(clubPlano.getObject());
                    setResponsePage(ClubVerPage.class);
                } catch (ClubYaExistenteException e) {
                    info("No se puede cambiar el nombre, ya existe otro club con ese nombre.");
                } catch (NoxitException e) {
                    info("No se pudo editar el club.");
                }
            }
        });
    }

}
