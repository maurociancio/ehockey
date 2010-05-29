package ar.noxit.ehockey.web.pages.jugadores;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.ehockey.service.IJugadorService;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;
import ar.noxit.ehockey.web.pages.models.ClubListModel;
import ar.noxit.ehockey.web.pages.models.DivisionListModel;
import ar.noxit.ehockey.web.pages.models.ClubModel;
import ar.noxit.ehockey.web.pages.models.DivisionModel;
import ar.noxit.ehockey.web.pages.models.SectorModel;
import ar.noxit.ehockey.web.pages.models.SectorListModel;
import ar.noxit.ehockey.web.pages.providers.JugadorDataProvider;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import ar.noxit.ehockey.web.pages.renderers.DivisionRenderer;
import ar.noxit.ehockey.web.pages.renderers.SectorRenderer;
import ar.noxit.exceptions.NoxitException;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class JugadorVerPage extends AbstractHeaderPage {

    @SpringBean
    private IDivisionService divisionService;
    @SpringBean
    private ISectorService sectorService;
    @SpringBean
    private IClubService clubService;
    @SpringBean
    private IJugadorService jugadorService;
    private IModel<Integer> clubid = new Model<Integer>();
    private IModel<Integer> divisionid = new Model<Integer>();
    private IModel<Integer> sectorid = new Model<Integer>();

    public JugadorVerPage() {
        super();

        JugadorVerPanel jugadorVerPanel = new JugadorVerPanel(jugadorService,
                new JugadorByClubDivisionSectorDataProvider(jugadorService));
        jugadorVerPanel.setOutputMarkupId(true);
        add(new DropDownChoice<Club>("club", new ClubModel(clubid,
                clubService), new ClubListModel(clubService),
                new ClubRenderer()).setNullValid(true).add(
                new AjaxJugadorVerUpdater("onchange")));

        add(new DropDownChoice<Division>("division", new DivisionModel(
                divisionid, divisionService), new DivisionListModel(
                divisionService), new DivisionRenderer()).setNullValid(true)
                .add(new AjaxJugadorVerUpdater("onchange")));

        add(new DropDownChoice<Sector>("sector", new SectorModel(sectorid,
                sectorService), new SectorListModel(sectorService),
                new SectorRenderer()).setNullValid(true).add(
                new AjaxJugadorVerUpdater("onchange")));

        add(jugadorVerPanel);

        add(new BookmarkablePageLink<AbstractJugadorPage>("menujugador", JugadorPage.class));
    }

    private class AjaxJugadorVerUpdater extends
            AjaxFormComponentUpdatingBehavior {

        public AjaxJugadorVerUpdater(String event) {
            super(event);
        }

        @Override
        protected void onUpdate(AjaxRequestTarget target) {
            target.addComponent(getPage().get("jugadorespanel"));
        }
    }

    private class JugadorByClubDivisionSectorDataProvider extends
            JugadorDataProvider {

        public JugadorByClubDivisionSectorDataProvider(
                IJugadorService jugadorService) {
            super(jugadorService);
        }

        @Override
        public List<Jugador> listoToLoad() throws NoxitException {
            return getService().getAllByClubDivisionSector(clubid.getObject(),
                    divisionid.getObject(), sectorid.getObject());
        }
    }
}