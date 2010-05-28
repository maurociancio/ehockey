package ar.noxit.ehockey.web.pages.torneo;

import static java.util.Collections.sort;

import ar.noxit.ehockey.model.Partido;
import ar.noxit.ehockey.model.PartidosComparator;
import ar.noxit.ehockey.model.Torneo;
import ar.noxit.ehockey.service.IDateTimeProvider;
import ar.noxit.ehockey.service.IExceptionConverter;
import ar.noxit.ehockey.service.IPartidoService;
import ar.noxit.ehockey.web.pages.base.AbstractHeaderPage;
import ar.noxit.ehockey.web.pages.header.IMenuItem;
import ar.noxit.exceptions.NoxitException;
import ar.noxit.utils.Collections;
import ar.noxit.web.wicket.column.AbstractLabelColumn;
import ar.noxit.web.wicket.model.LDM;
import ar.noxit.web.wicket.model.LocalDateTimeFormatModel;
import ar.noxit.web.wicket.provider.DataProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.LocalDateTime;

public class VerPartidosPage extends AbstractHeaderPage {

    @SpringBean
    private IPartidoService partidoService;
    private DefaultDataTable<Partido> dataTable;
    @SpringBean
    private IExceptionConverter converter;
    @SpringBean
    private IDateTimeProvider dateTimeProvider;

    public VerPartidosPage(IModel<Torneo> torneo) {
        Validate.notNull(torneo, "torneo no puede ser null");

        List<IColumn<Partido>> columns = new ArrayList<IColumn<Partido>>();
        columns.add(new PropertyColumn<Partido>(Model.of("Rueda"), "rueda"));
        columns.add(new PropertyColumn<Partido>(Model.of("Fecha"), "fechaDelTorneo"));
        columns.add(new PropertyColumn<Partido>(Model.of("Partido"), "partido"));
        columns.add(new PropertyColumn<Partido>(Model.of("Local"), "local.nombre"));
        columns.add(new PropertyColumn<Partido>(Model.of("Visitante"), "visitante.nombre"));
        columns.add(new AbstractLabelColumn<Partido>(Model.of("Inicio")) {

            @Override
            protected IModel<String> createDisplayModel(IModel<Partido> rowModel) {
                return new LocalDateTimeFormatModel(new PropertyModel<LocalDateTime>(rowModel, "inicio"));
            }
        });
        columns.add(new AbstractColumn<Partido>(Model.of("Jugado")) {

            @Override
            public void populateItem(Item<ICellPopulator<Partido>> cellItem, String componentId,
                    IModel<Partido> rowModel) {
                cellItem.add(new PartidoJugadoFragment(componentId, "jugado", getPage(), rowModel));
            }
        });
        columns.add(new AbstractColumn<Partido>(Model.of("Reprogramar")) {

            @Override
            public void populateItem(Item<ICellPopulator<Partido>> cellItem, String componentId,
                    IModel<Partido> rowModel) {
                cellItem.add(new ReprogramarPartidoPanel(componentId, rowModel));
            }
        });
        columns.add(new AbstractColumn<Partido>(Model.of("Planillas")) {

            @Override
            public void populateItem(Item<ICellPopulator<Partido>> cellItem, String componentId,
                    IModel<Partido> rowModel) {
                cellItem.add(new PlanillasPanel(componentId, "planillas", getPage(), rowModel));
            }
        });

        this.dataTable = new DefaultDataTable<Partido>("partidos", columns, new PartidosFromTorneoDataProvider(torneo),
                20) {

            @Override
            protected Item<Partido> newRowItem(String id, int index, final IModel<Partido> model) {
                return new Item<Partido>(id, index, model) {

                    @Override
                    protected void onComponentTag(ComponentTag tag) {
                        super.onComponentTag(tag);
                        Partido partido = model.getObject();
                        tag.put("class", (partido.getRueda() + partido.getFechaDelTorneo()) % 2 == 0 ? "even" : "odd");
                    }
                };
            }
        };
        this.dataTable.setItemReuseStrategy(new ReuseIfModelsEqualStrategy());
        this.dataTable.setOutputMarkupId(true);
        add(dataTable);
    }

    private static final class PartidoModel extends LDM<Partido> {

        private Integer id;
        private IPartidoService partidoService;

        private PartidoModel(IPartidoService partidoService, Partido object) {
            super(object);
            this.id = object.getId();
            this.partidoService = partidoService;
        }

        @Override
        protected Partido doLoad() throws NoxitException {
            return partidoService.get(id);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            PartidoModel other = (PartidoModel) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }
    }

    private final class PartidosFromTorneoDataProvider extends DataProvider<Partido> {

        private IModel<Torneo> torneo;

        public PartidosFromTorneoDataProvider(IModel<Torneo> torneo) {
            Validate.notNull(torneo);
            this.torneo = torneo;
        }

        @Override
        protected List<Partido> loadList() {
            Torneo t = torneo.getObject();
            List<Partido> list = Collections.toList(t.iteradorPartidos());
            sort(list, PartidosComparator.comparatorPorRuedaFechaYPartido());
            return list;
        }

        @Override
        public IModel<Partido> model(Partido object) {
            return new PartidoModel(partidoService, object);
        }
    }

    private final class ReprogramarPartidoPanel extends Panel {

        public ReprogramarPartidoPanel(String id, final IModel<Partido> partido) {
            super(id);

            final ModalWindow modalWindow = new ModalWindow("modal");
            modalWindow.setPageMapName("modal-1");
            modalWindow.setCookieName("modal-1");
            add(modalWindow);

            add(new AjaxLink<Void>("reprogramar") {

                @Override
                public void onClick(AjaxRequestTarget target) {
                    modalWindow.setPageCreator(new ModalWindow.PageCreator() {

                        @Override
                        public Page createPage() {
                            return new ReprogramacionPartidoPage(modalWindow, partido);
                        }
                    });
                    modalWindow.setWindowClosedCallback(new WindowClosedCallback() {

                        @Override
                        public void onClose(AjaxRequestTarget target) {
                            target.addComponent(dataTable);
                        }
                    });
                    modalWindow.show(target);
                }

                @Override
                public boolean isVisible() {
                    return !partido.getObject().isJugado();
                }
            });
            add(new WebMarkupContainer("no_reprogramar") {

                @Override
                public boolean isVisible() {
                    return partido.getObject().isJugado();
                }
            });
        }
    }

    private class PlanillasPanel extends Fragment {

        public PlanillasPanel(String id, String markupId, MarkupContainer markupProvider, final IModel<Partido> rowModel) {
            super(id, markupId, markupProvider);

            add(new PlanillaPrecargadaLink("precargada", rowModel));
            add(new PlanillaFinalLink("final", rowModel));
        }
    }

    private class PartidoJugadoFragment extends Fragment {

        public PartidoJugadoFragment(String id, String markupId, MarkupContainer markupProvider,
                final IModel<Partido> rowModel) {
            super(id, markupId, markupProvider);

            add(new Label("jugado", new AbstractReadOnlyModel<String>() {

                @Override
                public String getObject() {
                    return rowModel.getObject().isJugado() ? "Si" : "No";
                }
            }));
            add(new Link<Void>("terminar") {

                @Override
                public void onClick() {
                    try {
                        partidoService.terminarPartido(rowModel.getObject().getId());
                        rowModel.detach();
                    } catch (NoxitException e) {
                        error(converter.convert(e));
                    }
                }

                @Override
                public boolean isVisible() {
                    return !rowModel.getObject().isJugado()
                            && rowModel.getObject().puedeTerminarPartido(dateTimeProvider.getLocalDateTime());
                }
            });
        }
    }

    @Override
    public boolean shouldBeSelected(IMenuItem menuItem) {
        return menuItem.getPageLink().equals(TorneoPage.class);
    }
}
