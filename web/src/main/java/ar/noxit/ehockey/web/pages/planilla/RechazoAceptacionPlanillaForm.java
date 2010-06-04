package ar.noxit.ehockey.web.pages.planilla;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PlanillaFinal;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.IPlanillaService;
import ar.noxit.exceptions.NoxitException;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public final class RechazoAceptacionPlanillaForm extends Form<Void> {

    @SpringBean
    private IExceptionConverter exceptionConverter;
    @SpringBean
    private IPlanillaService planillaService;
    private final IModel<String> comentario;
    private final IModel<PlanillaFinal> planillaModel;
    private final IModel<Partido> partido;
    private IModel<String> accion;
    private static final String ACEPTAR_PLANILLA = "Validar Planilla";
    private static final String RECHAZAR_PLANILLA = "Rechazar Planilla";
    private static final List<String> ACCIONES = new ArrayList<String>();
    static {
        ACCIONES.add(ACEPTAR_PLANILLA);
        ACCIONES.add(RECHAZAR_PLANILLA);
    }

    public RechazoAceptacionPlanillaForm(String id,
            IModel<PlanillaFinal> planillaModel,
            IModel<Partido> partido) {

        super(id);

        this.comentario = Model.of("");
        this.planillaModel = planillaModel;
        this.partido = partido;

        this.accion = Model.of((String) null);

        final MarkupContainer comentarioContainer = new WebMarkupContainer("comentario");
        FormComponent<String> textArea = new TextArea<String>("comentario", comentario) {

            @Override
            public boolean isVisible() {
                return RECHAZAR_PLANILLA.equals(accion.getObject());
            }
        };
        textArea.setRequired(true);

        comentarioContainer.add(textArea);
        comentarioContainer.setOutputMarkupId(true);
        add(comentarioContainer);

        add(new RadioChoice<String>("acciones",
                accion, ACCIONES).
                setRequired(true).
                add(new AjaxFormChoiceComponentUpdatingBehavior() {

                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        target.addComponent(comentarioContainer);
                    }
                }).setOutputMarkupId(true));
    }

    @Override
    public boolean isVisible() {
        return planillaModel.getObject().isPublicada();
    }

    @Override
    protected void onSubmit() {
        try {
            Integer id = partido.getObject().getId();
            if (RECHAZAR_PLANILLA.equals(accion.getObject())) {
                planillaService.rechazarPlanilla(id, comentario.getObject());
                comentario.setObject(null);
            }
            if (ACEPTAR_PLANILLA.equals(accion.getObject())) {
                planillaService.validarPlanilla(id);
            }
        } catch (NoxitException e) {
            error(exceptionConverter.convert(e));
        }
    }
}