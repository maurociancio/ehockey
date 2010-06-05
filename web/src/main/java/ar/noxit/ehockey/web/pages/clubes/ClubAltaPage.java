package ar.noxit.ehockey.web.pages.clubes;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.service.IClubService;
import ar.noxit.exceptions.NoxitException;

public class ClubAltaPage extends AbstractMainLinkedClubPage {

    private IModel<ClubPlano> clubModel;
    @SpringBean
    private IClubService clubService;

    public ClubAltaPage() {

        clubModel = new Model<ClubPlano>(new ClubPlano());
        add(new ClubFormPanel("altaform", clubModel) {
            @Override
            public void onSubmit(IModel<ClubPlano> clubPlano) {
                try {
                    clubService.save(clubPlano.getObject());
                } catch (NoxitException e) {
                    info("No se ha podido insertarel club");
                }
                setResponsePage(ClubVerPage.class);
            }
        });

    }

}
