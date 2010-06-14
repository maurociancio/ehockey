package ar.noxit.ehockey.web.pages.buenafe;

import static ar.noxit.utils.Collections.toList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.noxit.ehockey.model.Equipo;
import ar.noxit.ehockey.model.Jugador;
import ar.noxit.ehockey.model.ListaBuenaFe;
import ar.noxit.ehockey.service.IClubService;
import ar.noxit.ehockey.service.IEquipoService;
import ar.noxit.ehockey.web.pages.components.EquipoSelectorPanel;
import ar.noxit.ehockey.web.pages.models.EquipoModel;
import ar.noxit.ehockey.web.pages.models.JugadoresParaEquipoListModel;
import ar.noxit.ehockey.web.pages.models.JugadoresSeleccionadosListModel;
import ar.noxit.ehockey.web.pages.renderers.JugadorRenderer;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.exceptions.NoxitRuntimeException;
import ar.noxit.web.wicket.provider.DataProvider;

public class VerListaBuenaFePage extends AbstractListaBuenaFePage {

    @SpringBean
    private IEquipoService equipoService;
    @SpringBean
    private IClubService clubService;
    private IModel<Integer> equipoId = new Model<Integer>();
    private List<Integer> seleccionados = new ArrayList<Integer>();

    public VerListaBuenaFePage() {
        add(new FeedbackPanel("feedback"));

        final IModel<Equipo> equipoSeleccionado = new EquipoModel(equipoId, equipoService);
        IModel<Integer> clubId = new PropertyModel<Integer>(equipoSeleccionado, "club.id");
        IModel<List<Integer>> seleccionadosModel = new PropertyModel<List<Integer>>(this, "seleccionados");

        final DataView<Jugador> dataViewTable = new DataView<Jugador>("jugadores", new JugadoresDataProvider(equipoSeleccionado)) {

            @Override
            protected Item<Jugador> newItem(String id, int index, IModel<Jugador> model) {
                return new OddEvenItem<Jugador>(id, index, model);
            }

            @Override
            protected void populateItem(Item<Jugador> item) {
                final IModel<Jugador> model = item.getModel();

                item.add(new Label("ficha", new PropertyModel<String>(model, "ficha")).setRenderBodyOnly(true));
                item.add(new Label("nya", new AbstractReadOnlyModel<String>() {

                    @Override
                    public String getObject() {
                        return model.getObject().getApellido() + " " + model.getObject().getNombre();
                    }
                }).setRenderBodyOnly(true));
            }

            @Override
            public boolean isVisible() {
                return equipoSeleccionado.getObject() != null && super.isVisible();
            }
        };

        // formulario de edición de lista de buena fe
        final Form<Void> formInclusion = new Form<Void>("inclusionForm") {

            @Override
            protected void onSubmit() {
                try {
                    equipoService.asignarListaBuenaFe(equipoId.getObject(), seleccionados);
                    this.setVisible(false);
                    dataViewTable.setVisible(true);
                } catch (NoxitException e) {
                    throw new NoxitRuntimeException(e);
                }
            }
        };

        final WebMarkupContainer tabla = new WebMarkupContainer("tabla");

        tabla.add(dataViewTable);
        tabla.setOutputMarkupId(true);

        formInclusion.add(new Palette<Jugador>("palette",
                new JugadoresSeleccionadosListModel(clubId, clubService, seleccionadosModel),
                new JugadoresParaEquipoListModel(equipoId, clubService),
                JugadorRenderer.get(),
                10,
                false));

        formInclusion.setVisible(false);
        add(formInclusion);

        Form<Void> form = new Form<Void>("equipos") {
            @Override
            protected void onSubmit() {
                dataViewTable.setVisible(false);
                formInclusion.setVisible(true);

                seleccionados.clear();
                ListaBuenaFe listaBuenaFe = equipoSeleccionado.getObject().getListaBuenaFe();
                Iterator<Jugador> it = listaBuenaFe.iterator();
                while (it.hasNext()) {
                    seleccionados.add(it.next().getFicha());
                }
            }
        };

        form.add(new EquipoSelectorPanel("equipo", equipoSeleccionado){
            protected void onUpdate(AjaxRequestTarget target) {
                formInclusion.setVisible(false);
                dataViewTable.setVisible(true);
                target.addComponent(tabla);
                target.addComponent(formInclusion);
            };
        }.setRequired(true));

        add(form);
        add(formInclusion);
        add(tabla);
    }

    private class JugadoresDataProvider extends DataProvider<Jugador> {

        private IModel<Equipo> equipo;

        public JugadoresDataProvider(IModel<Equipo> equipo) {
            this.equipo = equipo;
        }

        @Override
        protected List<Jugador> loadList() {
            Equipo equipoObj = equipo.getObject();
            return toList(equipoObj.getListaBuenaFe().iterator());
        }

        @Override
        public IModel<Jugador> model(Jugador object) {
            final Integer id = object.getFicha();
            return new LoadableDetachableModel<Jugador>(object) {

                @Override
                protected Jugador load() {
                    for (Jugador jugador : loadList()) {
                        if (jugador.getFicha().equals(id)) {
                            return jugador;
                        }
                    }
                    return null;
                }
            };
        }

        @Override
        public void detach() {
            super.detach();
            equipo.detach();
        }
    }
}
