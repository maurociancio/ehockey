package ar.noxit.ehockey.web.pages.jugadores;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

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
import ar.noxit.web.wicket.model.AdapterModel;

public abstract class JugadorForm extends Panel {

    @SpringBean
    private IClubService clubService;
    @SpringBean
    private IDivisionService divisionService;
    @SpringBean
    private ISectorService sectorService;

    public JugadorForm(String id, final IModel<JugadorPlano> jugador) {
        super(id);
        Validate.notNull(jugador);

        Form<JugadorPlano> form = new Form<JugadorPlano>("agregar_jugador",
                jugador) {

            @Override
            protected void onSubmit() {
                JugadorForm.this.onSubmit(jugador);
            }
        };

        form.add(new RequiredTextField<String>("nombre",
                new PropertyModel<String>(jugador, "nombre")));
        form.add(new RequiredTextField<String>("apellido",
                new PropertyModel<String>(jugador, "apellido")));
        form.add(new TextField<String>("tipodoc", new PropertyModel<String>(
                jugador, "tipoDocumento")));
        form.add(new TextField<String>("numerodoc", new PropertyModel<String>(
                jugador, "numeroDocumento")));

        PropertyModel<LocalDate> modelFechaNacimiento = new PropertyModel<LocalDate>(
                jugador, "fechaNacimiento");
        DateTextField fechaEvento = new DateTextField("fechanac",
                new DateAdapter(modelFechaNacimiento), "dd/MM/yyyy");
        fechaEvento.add(new DatePicker());
        form.add(fechaEvento);

        form.add(new TextField<String>("telefono", new PropertyModel<String>(
                jugador, "telefono")));
        form.add(new TextField<String>("fechaalta", new Model<String>(
                new LocalDate().toString("dd/MM/yyyy"))).setEnabled(false));
        form.add(new DropDownChoice<ClubPlano>("club",
                new PropertyModel<ClubPlano>(jugador, "club"),
                new ClubPlanoListModel(clubService), new ClubPlanoRenderer())
                .setRequired(true));
        form.add(new DropDownChoice<DivisionPlano>("division",
                new PropertyModel<DivisionPlano>(jugador, "division"),
                new DivisionPlanoListModel(divisionService),
                new DivisionPlanoRenderer()).setRequired(true));
        form.add(new DropDownChoice<SectorPlano>("sector",
                new PropertyModel<SectorPlano>(jugador, "sector"),
                new SectorPlanoListModel(sectorService),
                new SectorPlanoRenderer()).setRequired(true));
        form.add(new TextField<String>("letra", new PropertyModel<String>(
                jugador, "letraJugador")));
        add(form);
        add(new FeedbackPanel("feedback"));
    }

    protected abstract void onSubmit(IModel<JugadorPlano> jugador);

    private class ClubPlanoListModel extends
            LoadableDetachableModel<List<ClubPlano>> {

        private IClubService clubService;

        public ClubPlanoListModel(IClubService jugadorService) {
            Validate.notNull(jugadorService);

            this.clubService = jugadorService;
        }

        @Override
        protected List<ClubPlano> load() {
            try {
                return clubService.getAllPlano();
            } catch (NoxitException ex) {
                // #TODO
                throw new NoxitRuntimeException(ex);
            }
        }
    }

    public class ClubPlanoRenderer implements IChoiceRenderer<ClubPlano> {
        @Override
        public Object getDisplayValue(ClubPlano arg0) {
            return arg0.getNombre();
        }

        @Override
        public String getIdValue(ClubPlano arg0, int arg1) {
            return arg0.getNombre();
        }
    }

    private class DivisionPlanoListModel extends
            LoadableDetachableModel<List<DivisionPlano>> {

        private IDivisionService divisionService;

        public DivisionPlanoListModel(IDivisionService divisionService) {
            Validate.notNull(divisionService);

            this.divisionService = divisionService;
        }

        @Override
        protected List<DivisionPlano> load() {
            try {
                return divisionService.getAllPlano();
            } catch (NoxitException ex) {
                // #TODO
                throw new NoxitRuntimeException(ex);
            }
        }
    }

    private class DivisionPlanoRenderer implements
            IChoiceRenderer<DivisionPlano> {
        @Override
        public Object getDisplayValue(DivisionPlano arg0) {
            return arg0.getDivision();
        }

        @Override
        public String getIdValue(DivisionPlano arg0, int arg1) {
            return arg0.getDivision();
        }
    }

    private class SectorPlanoListModel extends
            LoadableDetachableModel<List<SectorPlano>> {

        private ISectorService sectorService;

        public SectorPlanoListModel(ISectorService sectorService) {
            Validate.notNull(sectorService);

            this.sectorService = sectorService;
        }

        @Override
        protected List<SectorPlano> load() {
            try {
                return sectorService.getAllPlano();
            } catch (NoxitException ex) {
                // #TODO
                throw new NoxitRuntimeException(ex);
            }
        }
    }

    public class SectorPlanoRenderer implements IChoiceRenderer<SectorPlano> {
        @Override
        public Object getDisplayValue(SectorPlano arg0) {
            return arg0.getSector();
        }

        @Override
        public String getIdValue(SectorPlano arg0, int arg1) {
            return arg0.getSector();
        }
    }

    private class DateAdapter extends AdapterModel<Date, LocalDate> {

        public DateAdapter(IModel<LocalDate> delegate) {
            super(delegate);
        }

        @SuppressWarnings("deprecation")
        @Override
        protected Date getObject(IModel<LocalDate> delegate) {
            LocalDate fecha = delegate.getObject();
            if (fecha == null)
                return null;
            return new Date(fecha.getYear(), fecha.getMonthOfYear(), fecha
                    .getDayOfMonth());
        }

        @Override
        protected void setObject(Date object, IModel<LocalDate> delegate) {
            LocalDate temp = delegate.getObject();
            delegate.setObject(new LocalDate(object));
        }
    }
}