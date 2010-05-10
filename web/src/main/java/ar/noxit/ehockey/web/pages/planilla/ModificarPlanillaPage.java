package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.DatosEquipoPlanilla;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaBase;
import ar.noxit.ehockey.model.TarjetasPartido;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.ehockey.web.pages.base.MensajePage;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.model.AdapterModel;
import java.util.Map;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ModificarPlanillaPage extends AbstractContentPage {

    private Integer golesLocal;
    private Integer golesVisitante;
    private IModel<EquipoInfo> infoLocal;
    private IModel<EquipoInfo> infoVisitante;

    @SpringBean
    private IPlanillaService planillaService;

    public ModificarPlanillaPage(final IModel<Partido> partido) {
        infoLocal = new EquipoInfoModel(new PropertyModel<DatosEquipoPlanilla>(partido, "planilla.datosLocal"));
        infoVisitante = new EquipoInfoModel(new PropertyModel<DatosEquipoPlanilla>(partido, "planilla.datosVisitante"));
        golesLocal = partido.getObject().getPlanilla().getGolesLocal();
        golesVisitante = partido.getObject().getPlanilla().getGolesVisitante();

        add(new FeedbackPanel("feedback"));
        Form<Void> form = new Form<Void>("edicion_planilla") {

            @Override
            protected void onSubmit() {
                try {
                    planillaService.updatePlanilla(partido.getObject().getId(), golesLocal, golesVisitante, infoLocal
                            .getObject(), infoVisitante.getObject());
                    setResponsePage(new MensajePage("Planilla de Partido",
                            "Se completó la actualización de la planilla"));
                } catch (NoxitException e) {
                    throw new NoxitRuntimeException(e);
                }
            }
        };
        form.add(new PlanillaGeneralPanel("planillaGeneral", new PropertyModel<PlanillaBase>(partido, "planilla"),
                new PropertyModel<Integer>(this, "golesLocal"), new PropertyModel<Integer>(this, "golesVisitante")));
        form.add(new PlanillaEquipoPanel("planillaLocal", new PropertyModel<Equipo>(partido, "local"), infoLocal));
        form.add(new PlanillaEquipoPanel("planillaVisitante", new PropertyModel<Equipo>(partido, "visitante"),
                infoVisitante));

        this.add(form);
    }

    public class EquipoInfoModel extends AdapterModel<EquipoInfo, DatosEquipoPlanilla> {

        private boolean cargado = false;
        private IModel<EquipoInfo> equipoInfo = new Model<EquipoInfo>();

        public EquipoInfoModel(IModel<DatosEquipoPlanilla> delegate) {
            super(delegate);
        }

        @Override
        protected EquipoInfo getObject(IModel<DatosEquipoPlanilla> datosEquipo) {
            if (!cargado) {
                EquipoInfo temp = new EquipoInfo();

                DatosEquipoPlanilla object = datosEquipo.getObject();
                temp.setArbitro(object.getArbitro());
                temp.setDt(object.getdT());
                temp.setGoleadores(object.getGoleadores());
                temp.setJuezMesa(object.getJuezDeMesa());
                temp.setMedico(object.getMedico());
                temp.setPf(object.getpFisico());
                temp.setCapitan(object.getCapitan());
                for (Jugador j : object.getJugadores()) {
                    temp.getSeleccionados().add(j.getFicha());
                }
                for (Map.Entry<Jugador, TarjetasPartido> entry : object.getTarjetas().entrySet()) {
                    Jugador j = entry.getKey();
                    TarjetasPartido value = entry.getValue();
                    temp.getAmonestaciones().add(new AmonestacionInfo(j.getFicha(),
                                    value.getRojas(),
                                    value.getAmarillas(),
                                    value.getVerdes()));
                }
                this.cargado = true;
                equipoInfo.setObject(temp);
            }
            return equipoInfo.getObject();
        }

        @Override
        protected void setObject(EquipoInfo equipoInfo, IModel<DatosEquipoPlanilla> arg1) {
            this.equipoInfo.setObject(equipoInfo);
            this.cargado = true;
        }
    }
}
