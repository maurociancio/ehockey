package ar.noxit.ehockey.web.pages.clubes;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

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
                    clubService.update(clubPlano.getObject());
                    setResponsePage(ClubVerPage.class);
                } catch (NoxitException e) {
                    info("No se pudo editar el club.");
                }
            }
        });
    }

}
