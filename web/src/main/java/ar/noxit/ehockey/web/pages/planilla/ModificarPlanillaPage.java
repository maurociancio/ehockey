package ar.noxit.ehockey.web.pages.planilla;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.DatosEquipoPlanilla;
import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.Planilla;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.ehockey.web.pages.base.AbstractContentPage;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.model.AdapterModel;

public class ModificarPlanillaPage extends AbstractContentPage {

    private Integer golesLocal;
    private Integer golesVisitante;
    private EquipoInfo infoLocal = new EquipoInfo();
    private EquipoInfo infoVisitante = new EquipoInfo();

    @SpringBean
    private IPlanillaService planillaService;

    public ModificarPlanillaPage(final IModel<Partido> partido) {
        add(new FeedbackPanel("feedback"));
        Form<Void> form = new Form<Void>("edicion_planilla") {

            @Override
            protected void onSubmit() {
                try {
                    planillaService.updatePlanilla(partido.getObject().getId(), golesLocal, golesVisitante, infoLocal, infoVisitante);
                } catch (NoxitException e) {
                    throw new NoxitRuntimeException(e);
                }
            }
        };
        form.add(new PlanillaGeneralPanel("planillaGeneral", new PropertyModel<Planilla>(partido, "planilla"), new PropertyModel<Integer>(this, "golesLocal"),
                new PropertyModel<Integer>(this, "golesVisitante")));
        form.add(new PlanillaEquipoPanel("planillaLocal", new PropertyModel<Equipo>(partido, "local"), Model
                .of(infoLocal)));
        form.add(new PlanillaEquipoPanel("planillaVisitante", new PropertyModel<Equipo>(partido, "visitante"), Model
                .of(infoVisitante)));

        this.add(form);
    }

    public class EquipoInfoModel extends AdapterModel<EquipoInfo, DatosEquipoPlanilla> {
        private boolean cargado = false;
        private IModel<EquipoInfo> equipoInfo;

        public EquipoInfoModel(IModel<DatosEquipoPlanilla> delegate) {
            super(delegate);
        }

        @Override
        protected EquipoInfo getObject(IModel<DatosEquipoPlanilla> datosEquipo) {
            EquipoInfo temp = this.equipoInfo.getObject();
            if (!cargado) {
                DatosEquipoPlanilla object = datosEquipo.getObject();
                temp.setArbitro(object.getArbitro());
                temp.setDt(object.getdT());
                temp.setGoleadores(object.getGoleadores());
                temp.setJuezMesa(object.getJuezDeMesa());
                temp.setMedico(object.getMedico());
                temp.setPf(object.getpFisico());
                for (Jugador j : object.getJugadores()) {
                    
                    temp.getSeleccionados().add(j.getFicha());
                }
                this.cargado = true;
            }
            return temp;
        }

        @Override
        protected void setObject(EquipoInfo equipoInfo, IModel<DatosEquipoPlanilla> arg1) {
            this.equipoInfo.setObject(equipoInfo);
            this.cargado = true;
        }

    }
}
