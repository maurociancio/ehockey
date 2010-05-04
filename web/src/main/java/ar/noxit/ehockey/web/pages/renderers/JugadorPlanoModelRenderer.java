package ar.noxit.ehockey.web.pages.renderers;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import ar.noxit.ehockey.web.pages.jugadores.JugadorPlano;

public class JugadorPlanoModelRenderer implements
        IChoiceRenderer<IModel<JugadorPlano>> {

    @Override
    public Object getDisplayValue(IModel<JugadorPlano> arg0) {
        return arg0.getObject().getNombre();
    }

    @Override
    public String getIdValue(IModel<JugadorPlano> arg0, int arg1) {
        return arg0.getObject().getFicha().toString();
    }
}