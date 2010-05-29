package ar.noxit.ehockey.web.pages.clubes;

public class ClubAltaPage extends AbstractMainLinkedClubPage {

    public ClubAltaPage() {

        add(new ClubFormPanel("altaform") {
            @Override
            public void onSubmit() {
                setResponsePage(ClubVerPage.class);
            }
        });

    }

}
