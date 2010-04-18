package ar.noxit.ehockey.web.pages.jugadores;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Club;
import ar.noxit.ehockey.model.Division;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Sector;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IDivisionService;
import ar.noxit.ehockey.service.ISectorService;
import ar.noxit.ehockey.web.pages.renderers.ClubRenderer;
import ar.noxit.ehockey.web.pages.renderers.DivisionRenderer;
import ar.noxit.ehockey.web.pages.renderers.SectorRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;

public abstract class JugadorForm extends Panel {

    @SpringBean
    private IClubService clubService;
    @SpringBean
    private IDivisionService divisionService;
    @SpringBean
    private ISectorService sectorService;

    public JugadorForm(String id, final IModel<Jugador> jugador) {
        super(id);
        Validate.notNull(jugador);

        Form<Jugador> form = new Form<Jugador>("agregar_jugador", jugador) {

            @Override
            protected void onSubmit() {
                JugadorForm.this.onSubmit(jugador);
            }
        };

        form.add(new RequiredTextField<String>("nombre",
                new PropertyModel<String>(jugador, "nombre")));
        form.add(new RequiredTextField<String>("apellido",
                new PropertyModel<String>(jugador, "apellido")));
        form.add(new DropDownChoice<Club>("club", new PropertyModel<Club>(
                jugador, "club"), new ClubListModel(clubService),
                new ClubRenderer()).setRequired(true));
        form.add(new DropDownChoice<Division>("division",
                new PropertyModel<Division>(jugador, "division"),
                new DivisionListModel(divisionService), new DivisionRenderer())
                .setRequired(true));
        form.add(new DropDownChoice<Sector>("sector",
                new PropertyModel<Sector>(jugador, "sector"),
                new SectorListModel(sectorService), new SectorRenderer())
                .setRequired(true));
        add(form);
        add(new FeedbackPanel("feedback"));
    }

    protected abstract void onSubmit(IModel<Jugador> jugador);

    private class ClubListModel extends LoadableDetachableModel<List<Club>> {

        private IClubService clubService;

        public ClubListModel(IClubService jugadorService) {
            Validate.notNull(jugadorService);

            this.clubService = jugadorService;
        }

        @Override
        protected List<Club> load() {
            try {
                return clubService.getAll();
            } catch (NoxitException ex) {
                // @todo
                throw new NoxitRuntimeException(ex);
            }
        }
    }

    private class DivisionListModel extends
            LoadableDetachableModel<List<Division>> {

        private IDivisionService divisionService;

        public DivisionListModel(IDivisionService divisionService) {
            Validate.notNull(divisionService);

            this.divisionService = divisionService;
        }

        @Override
        protected List<Division> load() {
            try {
                return divisionService.getAll();
            } catch (NoxitException ex) {
                // @todo
                throw new NoxitRuntimeException(ex);
            }
        }
    }

    private class SectorListModel extends LoadableDetachableModel<List<Sector>> {

        private ISectorService sectorService;

        public SectorListModel(ISectorService sectorService) {
            Validate.notNull(sectorService);

            this.sectorService = sectorService;
        }

        @Override
        protected List<Sector> load() {
            try {
                return sectorService.getAll();
            } catch (NoxitException ex) {
                // @todo
                throw new NoxitRuntimeException(ex);
            }
        }
    }

}